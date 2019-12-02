package bco.scheduler.controller;

import bco.scheduler.cron.NotificationSender;
import bco.scheduler.model.AppointmentQueue;
import bco.scheduler.repository.AppointmentQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Reminder Error Controller
 * @author Noah Trimble
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ReminderErrorController {
    
    /** appointment queue repository */
    @Autowired
    private AppointmentQueueRepository appointmentQueueRepository;

    /** notification sender */
    @Autowired
    private NotificationSender notificationSender;

    /**
     * Get all appointment queue items that have not been sent yet and have error messages
     * @return appointment queue items that have not been sent yet and have error messages
     */
    @GetMapping("/reminderErrors")
    public List<AppointmentQueue> getReminderErrors() {
        return appointmentQueueRepository.findAllWithErrorsAndNotSent();
    }

    /**
     * Sends notifications that need to be sent
     */
    @PostMapping("/reminderErrors/resend")
    public void resendNotifications() {
        Runnable r = () -> notificationSender.sendNotifications();
        new Thread(r).start();
    }
}