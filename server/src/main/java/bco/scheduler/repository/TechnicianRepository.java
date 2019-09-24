package bco.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bco.scheduler.model.Technician;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long>{

}