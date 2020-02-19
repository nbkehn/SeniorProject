package bco.scheduler.repository;

import bco.scheduler.model.AppointmentQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AppointmentQueueRepository extends JpaRepository<AppointmentQueue, Long>{
    /**
     * Get all appointment reminders that are ready to be sent
     * Do this by adding the time to send (number of days after the appointment's start date)
     * to the start date of the appointment and comparing against now.
     * If the value is less than now and has not been sent, it needs to be sent.
     * @return appointment queue items
     */
    @Query(value =
            "SELECT aq.* FROM appointment_queue aq\n" +
            "LEFT JOIN appointment a ON a.id = aq.appointment_id\n" +
            "LEFT JOIN reminder r ON r.id = aq.reminder_id\n" +
            "WHERE aq.is_sent = FALSE\n" +
            "AND (a.start_date + INTERVAL r.time_to_send DAY) <= NOW()", nativeQuery = true)
    Collection<AppointmentQueue> getReadyToSend();

    /**
     * Add new reminders to the appointment queue that wouldn't have to be sent yet
     * I.e. an entry in appointment queue will be made for the given reminder for each existing appointment
     * unless the entry should have already been sent.
     * This was done because we don't want users to be bombarded with old messages every time a new reminder
     * is created.
     * @param reminderId newly created reminder id
     * @return number of rows updated
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO appointment_queue (appointment_id, reminder_id, is_sent)\n" +
            "SELECT a.id, r.id, FALSE FROM appointment a\n" +
            "  LEFT JOIN reminder r ON r.id = :reminder_id\n" +
            "  WHERE (a.start_date + INTERVAL r.time_to_send DAY) > NOW();", nativeQuery = true)
    int addNewReminder(@Param("reminder_id") long reminderId);

    /**
     * Add new appointment to the appointment queue
     * I.e. an entry in appointment queue will be made for the given appointment for each existing reminder
     * unless the entry should have already been sent.
     * This was done because we don't want users to be bombarded with old messages every time a new appointment
     * is created.
     * @param appointmentId newly created appointment
     * @return number of rows updated
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO appointment_queue (appointment_id, reminder_id, is_sent)\n" +
            "SELECT a.id, r.id, FALSE FROM reminder r\n" +
            "  LEFT JOIN appointment a ON a.id = :appointment_id\n" +
            "  WHERE (a.start_date + INTERVAL r.time_to_send DAY) > NOW();", nativeQuery = true)
    int addNewAppointment(@Param("appointment_id") long appointmentId);

    /**
     * Get all appointment queue items that have not been sent yet and have error messages
     * @return appointment queue items that have not been sent yet and have error messages
     */
    @Query(value = "SELECT * FROM appointment_queue\n" +
            "  WHERE is_sent = FALSE\n" +
            "  AND error_message IS NOT NULL;", nativeQuery = true)
    List<AppointmentQueue> findAllWithErrorsAndNotSent();
}