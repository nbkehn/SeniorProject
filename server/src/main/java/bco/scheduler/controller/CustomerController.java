package bco.scheduler.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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
import bco.scheduler.model.Customer;
import bco.scheduler.repository.CustomerRepository;
import bco.scheduler.model.CommunicationType;

/**
 * Controller for customers
 * @author Connor J. Parke
 * @editedBy Noah Trimble
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
    /** customer repository */
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * returns all customers
     * @return all customers
     */
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    /**
     * returns a customer from given id
     * @param customerId id to use
     * @return customer with id
     * @throws ResourceNotFoundException
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
        return ResponseEntity.ok(customer);
    }

    /**
     * creates a customer
     * @param customer to create
     * @return the created customer
     */
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    /**
     * updates a customer
     * @param customerId customer to update
     * @param customerDetails updated customer
     * @return updated customer
     * @throws ResourceNotFoundException
     */
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
                                                   @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customer.setEmail(customerDetails.getEmail());
        customer.setLastName(customerDetails.getLastName());
        customer.setFirstName(customerDetails.getFirstName());
        customer.setPhone(customerDetails.getPhone());
        customer.setCommunicationPreference(customerDetails.getCommunicationPreference());
        customer.setAddress(customerDetails.getAddress());
        
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    /**
     * deletes a customer
     * @param customerId customer to be deleted
     * @return the deleted customer
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "id") Long customerId)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

        customerRepository.delete(customer);
        return ResponseEntity.ok(customer);
    }

    /**
     * Get communication types
     * @return array of communication types
     */
    @GetMapping("/customers/communicationType")
    public ResponseEntity<Map<CommunicationType, String>> getCommunicationTypes() {
        Map<CommunicationType, String> map = new HashMap<CommunicationType, String>();
        for (CommunicationType communicationType : CommunicationType.values()) {
            map.put(communicationType, communicationType.getName());
        }
        return ResponseEntity.ok(map);
    }
}