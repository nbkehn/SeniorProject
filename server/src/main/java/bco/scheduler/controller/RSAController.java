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
import bco.scheduler.model.RSA;
import bco.scheduler.repository.RSARepository;

/**
 * RSA controller
 * @author Connor
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class RSAController {
    /** RSA repository **/
    @Autowired
    private RSARepository rsaRepository;

    /**
     * gets all RSAs
     * @return all RSAs
     */
    @GetMapping("/rsas")
    public ResponseEntity<List<RSA>> getAllRSAs() {
        return ResponseEntity.ok(rsaRepository.findAll());
    }

    /**
     * returns RSA with given id
     * @param rsaId rsa to remove
     * @return removed rsa
     * @throws ResourceNotFoundException
     */
    @GetMapping("/rsas/{id}")
    public ResponseEntity<RSA> getRSAById(@PathVariable(value = "id") Long rsaId)
            throws ResourceNotFoundException {
        RSA rsa = rsaRepository.findById(rsaId)
                .orElseThrow(() -> new ResourceNotFoundException("RSA not found for this id :: " + rsaId));
        return ResponseEntity.ok(rsa);
    }

    /**
     * creates an rsa
     * @param rsa to create
     * @return created rsa
     */
    @PostMapping("/rsas")
    public ResponseEntity<RSA> createRSA(@Valid @RequestBody RSA rsa) {
        return ResponseEntity.ok(rsaRepository.save(rsa));

    }

    /**
     * updates an RSA
     * @param rsaId RSA to update
     * @param rsaDetails updated RSA
     * @return updated RSA
     * @throws ResourceNotFoundException
     */
    @PutMapping("/rsas/{id}")
    public ResponseEntity<RSA> updateRSA(@PathVariable(value = "id") Long rsaId,
                                                   @Valid @RequestBody RSA rsaDetails) throws ResourceNotFoundException {
        RSA rsa = rsaRepository.findById(rsaId)
                .orElseThrow(() -> new ResourceNotFoundException("RSA not found for this id :: " + rsaId));

        rsa.setEmail(rsaDetails.getEmail());
        rsa.setLastName(rsaDetails.getLastName());
        rsa.setFirstName(rsaDetails.getFirstName());
        rsa.setPhone(rsaDetails.getPhone());
        
        return ResponseEntity.ok(rsaRepository.save(rsa));
    }

    /**
     * deletes an rsa
     * @param rsaId rsa to delete
     * @return deleted rsa
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/rsas/{id}")
    public ResponseEntity<RSA> deleteRSA(@PathVariable(value = "id") Long rsaId)
            throws ResourceNotFoundException {
        RSA rsa = rsaRepository.findById(rsaId)
                .orElseThrow(() -> new ResourceNotFoundException("RSA not found for this id :: " + rsaId));

        rsaRepository.delete(rsa);
        return ResponseEntity.ok(rsa);
    }
}