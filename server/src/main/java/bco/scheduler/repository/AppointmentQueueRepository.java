package bco.scheduler.repository;

import bco.scheduler.model.AppointmentQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AppointmentQueueRepository extends JpaRepository<AppointmentQueue, Long>{

}