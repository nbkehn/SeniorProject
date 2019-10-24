/**
 * Sends notifications on a cron
 * @author Noah Trimble
 */
package bco.scheduler.cron;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.*;
import bco.scheduler.repository.AppointmentQueueRepository;
import bco.scheduler.repository.AppointmentRepository;
import bco.scheduler.repository.ReminderRepository;
import bco.scheduler.repository.TemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Component
public class NotificationSender {
    @Autowired
    private AppointmentQueueRepository appointmentQueueRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger log = LoggerFactory.getLogger(NotificationSender.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * Send appointment notifications
     */
    @Scheduled(cron = "0 */10 * * * *")
    public void sendNotifications() {
        Collection<AppointmentQueue> appointmentQueueItems = appointmentQueueRepository.getReadyToSend();

        for (AppointmentQueue appointmentQueueItem : appointmentQueueItems) {
            try {
                Appointment appointment = appointmentRepository.findById(appointmentQueueItem.getAppointmentId())
                        .orElseThrow(() -> new ResourceNotFoundException("Appointment not found."));
                Reminder reminder = reminderRepository.findById(appointmentQueueItem.getReminderId())
                        .orElseThrow(() -> new ResourceNotFoundException("Reminder not found."));

                Template emailTemplate = templateRepository.findById(reminder.getEmailTemplateId())
                        .orElseThrow(() -> new ResourceNotFoundException("Template not found."));
                Customer customer = appointment.getCustomer();

                this.sendEmail(emailTemplate, customer, appointment);
            }
            catch(Exception e) {
                log.error(e.getMessage());
            }
        }
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