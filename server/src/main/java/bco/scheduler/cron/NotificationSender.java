/**
 * Sends notifications on a cron
 * @author Noah Trimble
 */
package bco.scheduler.cron;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import bco.scheduler.model.AppointmentQueue;
import bco.scheduler.repository.AppointmentQueueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationSender {
    @Autowired
    private AppointmentQueueRepository appointmentQueueRepository;

    private static final Logger log = LoggerFactory.getLogger(NotificationSender.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "*/10 * * * *")
    public void sendNotifications() {
        Collection<AppointmentQueue> toSend = appointmentQueueRepository.getReadyToSend();
    }
}