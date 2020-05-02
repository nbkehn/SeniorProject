package bco.scheduler.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.FlooringType;
import bco.scheduler.repository.FlooringTypeRepository;

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

    @GetMapping("/flooringtype/qrcode/{hashCode}")
    public ResponseEntity<FlooringType> getFlooringTypeByHashCode(@PathVariable(value = "hashCode") String flooringTypeHash)
        throws ResourceNotFoundException {
            List<FlooringType> flooring = flooringTypeRepository.findAll();
            FlooringType floor = new FlooringType();
            for (int i = 0; i < flooring.size(); i++) {
                if (flooring.get(i).getHash_code().equals(flooringTypeHash)) {
                    floor = flooring.get(i);
                }
            }
            return ResponseEntity.ok(floor);
        }

    


    /**
     * Creates the flooring type object given a specific flooring
     */
    @PostMapping("/flooringtype")
    public ResponseEntity<FlooringType> createFlooring(@Valid @RequestBody FlooringType flooring) {
        FlooringType tempFloor = new FlooringType(flooring.name, flooring.style, flooring.color, flooring.company);
        return ResponseEntity.ok(flooringTypeRepository.save(tempFloor));
    }

    /**
     * Uploads a file and attempts to parse it. If successful saves the data inside
     * to the database.
     * 
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("/flooringtype/upload")
    public ResponseEntity<FlooringType> createFlooring(@RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        String type = "";
        try {
            Scanner scanner = new Scanner(file.getInputStream()) ;
            FlooringType flooring = null;
            if(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                type = tokens[0];
            } else {
                //failure state
            }
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                if(type.equalsIgnoreCase("carpet")){
                    flooring = new FlooringType(type, tokens[0], "", "BCO");
                
                } else if(type.equalsIgnoreCase("carpet tile")) {
                    flooring = new FlooringType(tokens[0], tokens[1], "", "BCO");
                
                } else {
                    flooring = new FlooringType(type, tokens[0], tokens[1], "BCO");
                }

                if(scanner.hasNextLine()){
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
        FlooringType flooring = flooringTypeRepository.findById(flooringTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Flooring not found for this id :: " + flooringTypeId));

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
        FlooringType flooring = flooringTypeRepository.findById(flooringTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Flooring not found for this id :: " + flooringTypeId));

        flooringTypeRepository.delete(flooring);
        return ResponseEntity.ok(flooring);
    }
    // @GetMapping("/flooringtype/createqr/{id}")
    // public byte[] getQRImg(@PathVariable(value = "id") Long flooringTypeId)
    //         throws WriterException, ResourceNotFoundException, IOException
    //  {
    //     FlooringType flooring = flooringTypeRepository.findById(flooringTypeId).orElseThrow(() -> new ResourceNotFoundException("Flooring not found for this id :: " + flooringTypeId));
    //     FlooringType tempFloor = new FlooringType(flooring.name, flooring.style, flooring.color, flooring.company);
    //     tempFloor.setId(flooring.getId());
    //     return flooring.createQRImg(flooring.getHash_code());

    // }

    // @GetMapping("/flooringtype/createqr")
    // public List<byte[]> getQRImgAll()
    //      throws WriterException, ResourceNotFoundException, IOException
    //      {
    //         List<FlooringType> all = flooringTypeRepository.findAll();
    //         List<byte[]> results = new ArrayList<byte[]>();
    //         for(int i = 0; i < all.size(); i++){
    //             results.add(all.get(i).createQRImg(all.get(i).hash_code));
    //         }
    //         return results;
    // }


}