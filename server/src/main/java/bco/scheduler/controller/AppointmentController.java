package bco.scheduler.controller;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;

import bco.scheduler.model.Customer;
import bco.scheduler.repository.CustomerRepository;

import bco.scheduler.model.Technician;
import bco.scheduler.repository.TechnicianRepository;

import bco.scheduler.model.RSA;
import bco.scheduler.repository.RSARepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.Appointment;
import bco.scheduler.repository.AppointmentRepository;

/**
 * Appointment Controller
 * @author Connor J. Parke
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
    /** appointment repository */
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    /** customer repository */
    @Autowired
    private CustomerRepository customerRepository;
    
    /** technician repository */
    @Autowired
    private TechnicianRepository technicianRepository;
    
    /** rsa repository */
    @Autowired
    private RSARepository rsaRepository;

    /**
     * returns all appointments
     * @return appointments list
     */
    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentRepository.findAll());
    }

    /**
     * gets an appointment by id
     * @param appointmentId id
     * @return appointment with id
     * @throws ResourceNotFoundException
     */
    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable(value = "id") Long appointmentId)
            throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));
        return ResponseEntity.ok(appointment);
    }

    /**
     * creates an appointment
     * @param appointment to create
     * @return created appointment
     */
    @PostMapping("/appointments")
    public ResponseEntity<Appointment> createAppointment (
            @Valid @RequestBody Appointment appointment
    ) {        
        return ResponseEntity.ok(appointmentRepository.save(appointment));
    }

    /**
     * updates an appointment
     * @param appointmentId appointment to update
     * @param appointmentDetails updated appointment
     * @return updated appointment
     * @throws ResourceNotFoundException
     */
    @PutMapping("/appointments/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable(value = "id") Long appointmentId,
                                                   @Valid @RequestBody Appointment appointmentDetails) throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));

        appointment.setTechnicians(appointmentDetails.getTechnicians());
        appointment.setRSA(appointmentDetails.getRSA());
        appointment.setCustomer(appointmentDetails.getCustomer());
        appointment.setStartDateTime(appointmentDetails.getStartDateTime());
        appointment.setEndDateTime(appointmentDetails.getEndDateTime());
        
        return ResponseEntity.ok(appointmentRepository.save(appointment));
    }

    /**
     * deletes an appointment
     * @param appointmentId appointment to remove
     * @return removed appointment
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable(value = "id") Long appointmentId)
            throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));

        appointmentRepository.delete(appointment);
        return ResponseEntity.ok(appointment);
    }
    
}