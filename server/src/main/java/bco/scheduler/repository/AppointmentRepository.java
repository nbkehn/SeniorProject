package bco.scheduler.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import bco.scheduler.model.Appointment;

/**
 * Appointment repository
 * @author Connor
 *
 */
@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AppointmentRepository extends CustomRepository<Appointment, Long>{

}