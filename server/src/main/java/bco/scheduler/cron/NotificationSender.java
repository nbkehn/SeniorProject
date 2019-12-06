/**
 * Sends notifications on a cron
 * @author Noah Trimble
 */
package bco.scheduler.cron;

import java.util.*;

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

    // Twilio variables
    private static final String ACCOUNT_SID = "AC197c8ea70d7980b88abeefbf3a03a8ea";
    private static final String AUTH_TOKEN = "f00b57fccf6cf5e5c762d1b16061f912";

    /**
     * Send appointment notifications
     */
    @Scheduled(cron = "0 */10 * * * *")
    public void sendNotifications() {
        Collection<AppointmentQueue> appointmentQueueItems = appointmentQueueRepository.getReadyToSend();

        for (AppointmentQueue appointmentQueueItem : appointmentQueueItems) {
            try {
                // Get necessary attached object to appointment queue item
                Appointment appointment = appointmentQueueItem.getAppointment();
                Reminder reminder = appointmentQueueItem.getReminder();
                this.sendNotification(reminder, appointment);

                // Set that the appointment reminder has been sent in the appointment queue
                appointmentQueueItem.setSent(true);
                appointmentQueueRepository.save(appointmentQueueItem);
            }
            catch(Exception e) {
                appointmentQueueItem.setErrorMessage(e.getMessage());
                appointmentQueueRepository.save(appointmentQueueItem);
                log.error(e.getMessage());
            }
        }
    }

    /**
     * Send notification for a provided offset
     * @param appointment appointment to send notification for
     * @param offset Offset to use for notification
     * @return whether message was sent of not (error) and what went wrong if there was an error
     */
    public Map<String, String> sendNotificationsForOffset(Appointment appointment, int offset) {
        Map<String, String> response = new HashMap<>();
        try {
            List<Reminder> reminders = reminderRepository.findByTimeToSend(offset);
            if (reminders.size() > 0) {
                for(Reminder reminder : reminders) {
                    this.sendNotification(reminder, appointment);
                }
                response.put("error", "0");
                return response;
            }
            else {
                response.put("error", "1");
                response.put("message", "No reminders have been configured for this time to send.");
            }
        }
        catch(ResourceNotFoundException e) {
            log.error(e.getMessage());
            response.put("error", "1");
            response.put("message", e.getMessage());
        }

        return response;
    }

    /**
     * Send a single notification
     * @param reminder reminder to use
     * @param appointment appointment attached to reminder
     * @throws ResourceNotFoundException template not found
     */
    private void sendNotification(Reminder reminder, Appointment appointment) throws ResourceNotFoundException {
        // Only send warranty notification if flooring type is carpet
        if (reminder.getTimeToSend() == TimeToSend.ONE_YEAR_AFTER.getOffset() &&
            !appointment.getFlooring().getName().toLowerCase().equals("carpet")) {
            return;
        }

        Template emailTemplate = reminder.getEmailTemplate();
        Template textTemplate = reminder.getTextTemplate();

        // Determine which user to send message to
        switch(reminder.getUserType()) {
            case CUSTOMER:
                Customer customer = appointment.getCustomer();

                if (reminder.getTimeToSend() == TimeToSend.OTW.getOffset()) {
                    this.sendMessage(textTemplate, customer, appointment, false);
                    break;
                }

                // Send email, text, or both depending on customer's communication preference
                switch(customer.getCommunicationPreference()) {
                    case EMAIL:
                        this.sendMessage(emailTemplate, customer, appointment, true);
                        break;
                    case TEXT:
                        this.sendMessage(textTemplate, customer, appointment, false);
                        break;
                    case EMAIL_AND_TEXT:
                        this.sendMessage(emailTemplate, customer, appointment, true);
                        this.sendMessage(textTemplate, customer, appointment, false);
                        break;
                }
                break;

            case RSA:
                this.sendMessage(emailTemplate, appointment.getRSA(), appointment, true);
                break;

            case TECHNICIAN:
                for(Technician technician : appointment.getTechnicians()) {
                    this.sendMessage(emailTemplate, technician, appointment, true);
                }
                break;
        }
    }

    /**
     * Send message over email
     * @param template Template to use
     * @param appointment Attached appointment
     * @param person person to send message to
     * @param isEmail Email or text
     */
    private void sendMessage(Template template, Person person, Appointment appointment, boolean isEmail) throws ResourceNotFoundException {
        String target;
        if (isEmail) {
            target = person.getEmail();
        }
        else {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            PhoneNumber phoneNumber = PhoneNumber.fetcher(
                    new com.twilio.type.PhoneNumber(person.getPhone()))
                    .setType(Arrays.asList("carrier")).fetch();
            String carrierName = phoneNumber.getCarrier().get("name").split(" ")[0];
            List<Carrier> carriers = carrierRepository.findByNameLike("%" + carrierName + "%");
            if (carriers.size() <= 0) {
                throw new ResourceNotFoundException(carrierName + " is not a supported carrier.");
            }
            Carrier carrier = carriers.get(0);
            target = person.getPhone() + "@" + carrier.getEmailDomain();
        }

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(target);
        msg.setSubject(parseTemplateVariables(template.getSubject(), appointment, person));
        msg.setText(parseTemplateVariables(template.getContent(), appointment, person));

        javaMailSender.send(msg);
    }

    /**
     * Replace content variables with actual values
     * @param content Original content with variables
     * @param appointment Appointment that the reminder is for
     * @param person Recipient of message
     * @return parsed content string
     */
    private String parseTemplateVariables(String content, Appointment appointment, Person person) {
        List<Map<String, String>> templateVariableList = new ArrayList<>();
        templateVariableList.add(appointment.getTemplateVariables());
        templateVariableList.add(person.getTemplateVariables());

        Map<String, String> templateVariables = new HashMap<>();
        for (Map<String, String> item : templateVariableList) {
            templateVariables.putAll(item);
        }

        for (Map.Entry<String, String> templateVariable : templateVariables.entrySet()) {
            content = content.replace("${" + templateVariable.getKey() + "}", templateVariable.getValue());
        }

        return content;
    }
}