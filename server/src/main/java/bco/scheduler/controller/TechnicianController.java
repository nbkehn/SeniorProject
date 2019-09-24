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

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.Technician;
import bco.scheduler.repository.TechnicianRepository;

@RestController
@RequestMapping("/api/v1")
public class TechnicianController {
    @Autowired
    private TechnicianRepository technicianRepository;

    @GetMapping("/technicians")
    public List<Technician> getAllTechnicians() {
        return technicianRepository.findAll();
    }

    @GetMapping("/technicians/{id}")
    public ResponseEntity<Technician> getTechnicianById(@PathVariable(value = "id") Long technicianId)
            throws ResourceNotFoundException {
        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found for this id :: " + technicianId));
        return ResponseEntity.ok().body(technician);
    }

    @PostMapping("/technicians")
    public Technician createTechnician(@Valid @RequestBody Technician technician) {
        return technicianRepository.save(technician);
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
        final Technician updatedTechnician = technicianRepository.save(technician);
        return ResponseEntity.ok(updatedTechnician);
    }

    @DeleteMapping("/technicians/{id}")
    public Map<String, Boolean> deleteTechnician(@PathVariable(value = "id") Long technicianId)
            throws ResourceNotFoundException {
        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found for this id :: " + technicianId));

        technicianRepository.delete(technician);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}