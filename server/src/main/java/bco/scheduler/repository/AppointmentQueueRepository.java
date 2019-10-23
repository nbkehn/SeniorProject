package bco.scheduler.repository;

import bco.scheduler.model.AppointmentQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AppointmentQueueRepository extends JpaRepository<AppointmentQueue, Long>{
    @Query(value =
            "SELECT aq.* FROM appointment_queue aq\n" +
            "LEFT JOIN appointment a ON a.id = aq.appointment_id\n" +
            "LEFT JOIN reminder r ON r.id = aq.reminder_id\n" +
            "WHERE aq.is_sent = FALSE\n" +
            "AND (a.start_time + INTERVAL r.time_to_send DAY) < NOW()", nativeQuery = true)
    Collection<AppointmentQueue> getReadyToSend();
}