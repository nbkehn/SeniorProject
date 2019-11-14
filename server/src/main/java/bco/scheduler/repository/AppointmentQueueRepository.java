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

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AppointmentQueueRepository extends JpaRepository<AppointmentQueue, Long>{
    /**
     * Get all appointment reminders that are ready to be sent
     * @return appointment queue items
     */
    @Query(value =
            "SELECT aq.* FROM appointment_queue aq\n" +
            "LEFT JOIN appointment a ON a.id = aq.appointment_id\n" +
            "LEFT JOIN reminder r ON r.id = aq.reminder_id\n" +
            "WHERE aq.is_sent = FALSE\n" +
            "AND (a.start_date_time + INTERVAL r.time_to_send DAY) <= NOW()", nativeQuery = true)
    Collection<AppointmentQueue> getReadyToSend();

    /**
     * Add new reminders to the appointment queue that wouldn't have to be sent yet
     * @param reminderId newly created reminder id
     * @return number of rows updated
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO appointment_queue (appointment_id, reminder_id, is_sent)\n" +
            "SELECT a.id, r.id, FALSE FROM appointment a\n" +
            "  LEFT JOIN reminder r ON r.id = :reminder_id\n" +
            "  WHERE (a.start_date_time + INTERVAL r.time_to_send DAY) > NOW();", nativeQuery = true)
    int addNewReminder(@Param("reminder_id") long reminderId);

    /**
     * Add new appointment to the appointment queue
     * @param appointmentId newly created appointment
     * @return number of rows updated
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO appointment_queue (appointment_id, reminder_id, is_sent)\n" +
            "SELECT a.id, r.id, FALSE FROM reminder r\n" +
            "  LEFT JOIN appointment a ON a.id = :appointment_id\n" +
            "  WHERE (a.start_date_time + INTERVAL r.time_to_send DAY) > NOW();", nativeQuery = true)
    int addNewAppointment(@Param("appointment_id") long appointmentId);
}