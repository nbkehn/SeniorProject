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

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class RSAController {
    @Autowired
    private RSARepository rsaRepository;

    @GetMapping("/rsas")
    public ResponseEntity<List<RSA>> getAllRSAs() {
        return ResponseEntity.ok(rsaRepository.findAll());
    }

    @GetMapping("/rsas/{id}")
    public ResponseEntity<RSA> getRSAById(@PathVariable(value = "id") Long rsaId)
            throws ResourceNotFoundException {
        RSA rsa = rsaRepository.findById(rsaId)
                .orElseThrow(() -> new ResourceNotFoundException("RSA not found for this id :: " + rsaId));
        return ResponseEntity.ok(rsa);
    }

    @PostMapping("/rsas")
    public ResponseEntity<RSA> createRSA(@Valid @RequestBody RSA rsa) {
        rsaRepository.save(rsa);
        return ResponseEntity.ok(rsa);

    }

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

    @DeleteMapping("/rsas/{id}")
    public ResponseEntity<RSA> deleteRSA(@PathVariable(value = "id") Long rsaId)
            throws ResourceNotFoundException {
        RSA rsa = rsaRepository.findById(rsaId)
                .orElseThrow(() -> new ResourceNotFoundException("RSA not found for this id :: " + rsaId));

        rsaRepository.delete(rsa);
        return ResponseEntity.ok(rsa);
    }
}