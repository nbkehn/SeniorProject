/**
 * Sends notifications on a cron
 * @author Noah Trimble
 */
package bco.scheduler.cron;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.Appointment;
import bco.scheduler.model.AppointmentQueue;
import bco.scheduler.model.Reminder;
import bco.scheduler.model.Template;
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
    @Scheduled(cron = "*/10 * * * *")
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

                this.sendEmail(emailTemplate);
            }
            catch(Exception e) {
                log.error(e.getMessage());
            }

        }
    }

    /**
     * Send appointment reminder email
     * @param Template template to use for email
     */
    private void sendEmail(Template template) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("noahtrimble@yahoo.com");

        msg.setSubject(template.getContent());
        msg.setText(template.getContent());

        javaMailSender.send(msg);
    }
}