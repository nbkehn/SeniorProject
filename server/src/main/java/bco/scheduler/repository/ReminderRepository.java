package bco.scheduler.repository;

import bco.scheduler.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * Reminder repository
 * @author Noah Trimble
 *
 */
@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface ReminderRepository extends JpaRepository<Reminder, Long>{
    /**
     * Get reminder by time to send
     * @param timeToSend time to send
     * @return Reminder with time to send given
     */
    List<Reminder> findByTimeToSend(int timeToSend);
}