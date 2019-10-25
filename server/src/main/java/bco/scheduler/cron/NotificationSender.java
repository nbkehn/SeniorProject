/**
 * Sends notifications on a cron
 * @author Noah Trimble
 */
package bco.scheduler.cron;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.*;
import bco.scheduler.repository.*;
import com.twilio.rest.lookups.v1.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.twilio.Twilio;

@Component
public class NotificationSender {
    // Repository classes
    @Autowired
    private AppointmentQueueRepository appointmentQueueRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private CarrierRepository carrierRepository;

    // Email sender
    @Autowired
    private JavaMailSender javaMailSender;

    // Logging
    private static final Logger log = LoggerFactory.getLogger(NotificationSender.class);

    // TODO: Store these somewhere else
    // Twilio variables
    public static final String ACCOUNT_SID = "AC197c8ea70d7980b88abeefbf3a03a8ea";
    public static final String AUTH_TOKEN = "f00b57fccf6cf5e5c762d1b16061f912";

    /**
     * Send appointment notifications
     */
    @Scheduled(cron = "0 */10 * * * *")
    public void sendNotifications() {
        Collection<AppointmentQueue> appointmentQueueItems = appointmentQueueRepository.getReadyToSend();

        for (AppointmentQueue appointmentQueueItem : appointmentQueueItems) {
            try {
                // Get necessary attached object to appointment queue item
                Appointment appointment = appointmentRepository.findById(appointmentQueueItem.getAppointmentId())
                        .orElseThrow(() -> new ResourceNotFoundException("Appointment not found."));
                Reminder reminder = reminderRepository.findById(appointmentQueueItem.getReminderId())
                        .orElseThrow(() -> new ResourceNotFoundException("Reminder not found."));

                Template emailTemplate = templateRepository.findById(reminder.getEmailTemplateId())
                        .orElseThrow(() -> new ResourceNotFoundException("Email template not found."));
                Template textTemplate = templateRepository.findById(reminder.getTextTemplateId())
                        .orElseThrow(() -> new ResourceNotFoundException("Text template not found."));
                Customer customer = appointment.getCustomer();

                // Send email, text, or both depending on customer's communication preference
                switch(customer.getCommunicationPreference()) {
                    case EMAIL:
                        this.sendEmail(emailTemplate, customer, appointment);
                        break;
                    case TEXT:
                        this.sendText(textTemplate, customer, appointment);
                        break;
                    case EMAIL_AND_TEXT:
                        this.sendEmail(emailTemplate, customer, appointment);
                        this.sendText(textTemplate, customer, appointment);
                        break;
                }

                // Set that the appointment reminder has been sent in the appointment queue
                // TODO: Remove the item from the appointment queue altogether?
                //appointmentQueueItem.setSent(true);
                //appointmentQueueRepository.save(appointmentQueueItem);
            }
            catch(Exception e) {
                appointmentQueueItem.setErrorMessage(e.getMessage());
                appointmentQueueRepository.save(appointmentQueueItem);
                log.error(e.getMessage());
            }
        }
    }

    /**
     * Send appointment reminder text
     * @param template template to use for text
     * @param customer customer to send text to
     * @param appointment appointment data to use
     */
    private void sendText(Template template, Customer customer, Appointment appointment) {
        SimpleMailMessage msg = new SimpleMailMessage();

        // Get address to send text to
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        PhoneNumber phoneNumber = PhoneNumber.fetcher(
                new com.twilio.type.PhoneNumber(customer.getPhone()))
                .setType(Arrays.asList("carrier")).fetch();
        Carrier carrier = carrierRepository.findByName(phoneNumber.getCarrier().get("name")).get(0);

        msg.setTo(customer.getPhone() + "@" + carrier.getEmailDomain());
        msg.setSubject(template.getSubject());
        String content = parseContent(template.getContent(), customer, appointment);
        msg.setText(content);

        javaMailSender.send(msg);
    }

    /**
     * Send appointment reminder email
     * @param template template to use for email
     * @param customer customer to send email to
     * @param appointment appointment data to use
     */
    private void sendEmail(Template template, Customer customer, Appointment appointment) {
        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(customer.getEmail());
        msg.setSubject(template.getSubject());
        String content = parseContent(template.getContent(), customer, appointment);
        msg.setText(content);

        javaMailSender.send(msg);
    }

    /**
     * Replace content variables with actual values
     * @param content Original content with variables
     * @param customer Customer attached to appointment
     * @param appointment Appointment that the reminder is for
     * @return parsed content string
     */
    private String parseContent(String content, Customer customer, Appointment appointment) {
        Map<String, String> customerTemplateVariables = customer.getTemplateVariables();
        Map<String, String> appointmentTemplateVariables = appointment.getTemplateVariables();
        Map<String, String> templateVariables = new HashMap<>();
        templateVariables.putAll(customerTemplateVariables);
        templateVariables.putAll(appointmentTemplateVariables);

        for (Map.Entry<String, String> templateVariable : templateVariables.entrySet()) {
            content = content.replace("${" + templateVariable.getKey() + "}", templateVariable.getValue());
        }

        return content;
    }
}