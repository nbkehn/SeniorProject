package bco.scheduler.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import java.awt.image.BufferedImage;


import javax.validation.Valid;

import com.google.zxing.WriterException;

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
 * The controller for the flooring type. Handles all actions surrounding the
 * flooring type objects and any interaction that can be had with them.
 * 
 * @author Noah Trimble, Will Duke
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
        FlooringType flooring = flooringTypeRepository.findById(flooringTypeId).orElseThrow(
                () -> new ResourceNotFoundException("Flooring type not found for this id :: " + flooringTypeId));
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
     * Uploads a file and attempts to parse it. If successful saves the data inside
     * to the database.
     */
    @PostMapping("/flooringtype/upload")
    public ResponseEntity<FlooringType> createFlooring(@Valid @RequestBody File file) {
        String type = "";
        try {
            Scanner scanner = new Scanner(file);
            FlooringType flooring = null;
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                type = tokens[0];
            } else {
                // failure state
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                if (type.equalsIgnoreCase("carpet")) {
                    flooring = new FlooringType(type, tokens[0], "", "");

                } else {
                    flooring = new FlooringType(type, tokens[0], tokens[1], "");
                }

                if (scanner.hasNextLine()) {
                    flooringTypeRepository.save(flooring);
                } else {
                    scanner.close();
                    return ResponseEntity.ok(flooringTypeRepository.save(flooring));
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        FlooringType flooring = new FlooringType();

        return ResponseEntity.ok(flooringTypeRepository.save(flooring));
    }

    /**
     * Updates the flooring to match the specified fields.
     */
    @PutMapping("/flooringtype/{id}")
    public ResponseEntity<FlooringType> updateFlooring(@PathVariable(value = "id") Long flooringTypeId,
            @Valid @RequestBody FlooringType flooringType) throws ResourceNotFoundException {
        FlooringType flooring = flooringTypeRepository.findById(flooringTypeId).orElseThrow(
                () -> new ResourceNotFoundException("Flooring not found for this id :: " + flooringTypeId));

        flooring.setId(flooringType.getId());
        flooring.setName(flooringType.getName());

        return ResponseEntity.ok(flooringTypeRepository.save(flooring));
    }

    /**
     * Deletes the flooring type specified by the id.
     */
    @DeleteMapping("/flooringtype/{id}")
    public ResponseEntity<FlooringType> deleteFlooring(@PathVariable(value = "id") Long flooringTypeId)
            throws ResourceNotFoundException {
        FlooringType flooring = flooringTypeRepository.findById(flooringTypeId).orElseThrow(
                () -> new ResourceNotFoundException("Flooring not found for this id :: " + flooringTypeId));

        flooringTypeRepository.delete(flooring);
        return ResponseEntity.ok(flooring);
    }

    @GetMapping("/flooringtype/{id}")
    public BufferedImage getQRImg(@PathVariable(value = "id") Long flooringTypeId)
            throws UnsupportedEncodingException, WriterException, ResourceNotFoundException
     {
        FlooringType flooring = flooringTypeRepository.findById(flooringTypeId).orElseThrow(() -> new ResourceNotFoundException("Flooring not found for this id :: " + flooringTypeId));
        return flooring.createQRImg(flooring.hash_code);

        

    }
}