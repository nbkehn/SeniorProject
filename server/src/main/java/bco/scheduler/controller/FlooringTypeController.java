package bco.scheduler.controller;

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
import bco.scheduler.model.FlooringType;
import bco.scheduler.repository.FlooringTypeRepository;
import bco.scheduler.model.CommunicationType;

/**
 * The controller for the flooring type. Handles all actions surrounding the flooring type objects and any interaction
 * that can be had with them.
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class FlooringTypeController {

    /** A flooring type repository object is used for easy referencing. */
    @Autowired
    private FlooringTypeRepository flooringTypeRepository;

    /** Gets all of the flooring types. */
    @GetMapping("/flooringtype")
    public ResponseEntity<List<FlooringType>> getAllFlooringTypes() {
        return ResponseEntity.ok(flooringTypeRepository.findAll());
    }

    /**
     * Gets the flooring type by a specific id. 
     */
    @GetMapping("/flooringtype/{id}")
    public ResponseEntity<FlooringType> getFlooringTypeById(@PathVariable(value = "id") Long flooringTypeId)
            throws ResourceNotFoundException {
        FlooringType flooring = flooringTypeRepository.findById(flooringTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Flooring type not found for this id :: " + flooringTypeId));
        return ResponseEntity.ok(flooring);
    }

    /**
     * Creates the flooring type object given a specific flooring
     */
    @PostMapping("/flooringtype")
    public ResponseEntity<FlooringType> createFlooring(@Valid @RequestBody FlooringType flooring) {
        return ResponseEntity.ok(flooringTypeRepository.save(flooring));
    }

    /**
     * Updates the flooring to match the specified fields. 
     */
    @PutMapping("/flooringtype/{id}")
    public ResponseEntity<FlooringType> updateFlooring(@PathVariable(value = "id") Long flooringTypeId,
                                                   @Valid @RequestBody FlooringType flooringType) throws ResourceNotFoundException {
        FlooringType flooring = flooringTypeRepository.findById(flooringTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Flooring not found for this id :: " + flooringTypeId));

        flooring.setId(flooringType.getId());
        flooring.setTypeName(flooringType.getTypeName());
        
        return ResponseEntity.ok(flooringTypeRepository.save(flooring));
    }

    /**
     * Deletes the flooring type specified by the id. 
     */
    @DeleteMapping("/flooringtype/{id}")
    public ResponseEntity<FlooringType> deleteFlooring(@PathVariable(value = "id") Long flooringTypeId)
            throws ResourceNotFoundException {
        FlooringType flooring = flooringTypeRepository.findById(flooringTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Flooring not found for this id :: " + flooringTypeId));

        flooringTypeRepository.delete(flooring);
        return ResponseEntity.ok(flooring);
    }
}