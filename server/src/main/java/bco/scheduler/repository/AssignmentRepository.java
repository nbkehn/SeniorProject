package bco.scheduler.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import bco.scheduler.model.Assignment;

/**
 * Appointment repository
 * @author Connor
 *
 */
@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface AssignmentRepository extends CustomRepository<Assignment, Long>{

}