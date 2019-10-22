package bco.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import bco.scheduler.model.FlooringType;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface FlooringTypeRepository extends JpaRepository<FlooringType, Long>{

}