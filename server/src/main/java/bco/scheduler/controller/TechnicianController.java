package bco.scheduler.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.Technician;
import bco.scheduler.repository.TechnicianRepository;

/**
 * Technician controller
 * @author Noah Trimble
 * @editedBy Connor J. Parke
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TechnicianController {
    /** technician repository */
    @Autowired
    private TechnicianRepository technicianRepository;

    /**
     * gets all technicians
     * @return all technicians
     */
    @GetMapping("/technicians")
    public ResponseEntity<List<Technician>> getAllTechnicians() {
        return ResponseEntity.ok(technicianRepository.findAll());
    }

    /**
     * gets technician with given id
     * @param technicianId technician to get
     * @return technician with given id
     * @throws ResourceNotFoundException
     */
    @GetMapping("/technicians/{id}")
    public ResponseEntity<Technician> getTechnicianById(@PathVariable(value = "id") Long technicianId)
            throws ResourceNotFoundException {
        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found for this id :: " + technicianId));
        return ResponseEntity.ok(technician);
    }

    /**
     * creates a technician 
     * @param technician to create
     * @return created technician
     */
    @PostMapping("/technicians")
    public ResponseEntity<Technician> createTechnician(@Valid @RequestBody Technician technician) {
        return ResponseEntity.ok(technicianRepository.save(technician));

    }

    /**
     * updates a technician 
     * @param technicianId technician to update
     * @param technicianDetails updated technician
     * @return updated technician 
     * @throws ResourceNotFoundException
     */
    @PutMapping("/technicians/{id}")
    public ResponseEntity<Technician> updateTechnician(@PathVariable(value = "id") Long technicianId,
                                                   @Valid @RequestBody Technician technicianDetails) throws ResourceNotFoundException {
        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found for this id :: " + technicianId));

        technician.setEmail(technicianDetails.getEmail());
        technician.setLastName(technicianDetails.getLastName());
        technician.setFirstName(technicianDetails.getFirstName());
        technician.setPhone(technicianDetails.getPhone());
        technician.setInHouse(technicianDetails.isInHouse());
        
        return ResponseEntity.ok(technicianRepository.save(technician));
    }

    /**
     * deletes a technician 
     * @param technicianId technicina to delete
     * @return deleted technician 
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/technicians/{id}")
    public ResponseEntity<Technician> deleteTechnician(@PathVariable(value = "id") Long technicianId)
            throws ResourceNotFoundException {
        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found for this id :: " + technicianId));

        technicianRepository.delete(technician);
        return ResponseEntity.ok(technician);
    }
}