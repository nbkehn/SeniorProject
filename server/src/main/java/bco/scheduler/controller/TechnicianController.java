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

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TechnicianController {
    @Autowired
    private TechnicianRepository technicianRepository;

    @GetMapping("/technicians")
    public ResponseEntity<List<Technician>> getAllTechnicians() {
        return ResponseEntity.ok(technicianRepository.findAll());
    }

    @GetMapping("/technicians/{id}")
    public ResponseEntity<Technician> getTechnicianById(@PathVariable(value = "id") Long technicianId)
            throws ResourceNotFoundException {
        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found for this id :: " + technicianId));
        return ResponseEntity.ok(technician);
    }

    @PostMapping("/technicians")
    public ResponseEntity<Technician> createTechnician(@Valid @RequestBody Technician technician) {
        return ResponseEntity.ok(technicianRepository.save(technician));

    }

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

    @DeleteMapping("/technicians/{id}")
    public ResponseEntity<Technician> deleteTechnician(@PathVariable(value = "id") Long technicianId)
            throws ResourceNotFoundException {
        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found for this id :: " + technicianId));

        technicianRepository.delete(technician);
        return ResponseEntity.ok(technician);
    }
}