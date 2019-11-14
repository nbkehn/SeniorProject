package bco.scheduler.controller;

import javax.validation.Valid;
import bco.scheduler.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.Appointment;

import java.util.List;

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

    /** appointment queue repository */
    @Autowired
    private AppointmentQueueRepository appointmentQueueRepository;

    /**
     * returns all appointments
     * @return appointments list
     */
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /**
     * gets an appointment by id
     * @param appointmentId id
     * @return appointment with id
     * @throws ResourceNotFoundException
     */
    @GetMapping("/appointments/{id}")
    public Appointment getAppointmentById(@PathVariable(value = "id") Long appointmentId)
            throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));
        return appointment;
    }

    /**
     * creates an appointment
     * @param appointment to create
     * @return created appointment
     */
    @PostMapping("/appointments")
    public Appointment createAppointment (
            @Valid @RequestBody Appointment appointment
    ) {
        appointment = appointmentRepository.save(appointment);
        appointmentQueueRepository.addNewAppointment(appointment.getId());
        return appointment;
    }

    /**
     * updates an appointment
     * @param appointmentId appointment to update
     * @param appointmentDetails updated appointment
     * @return updated appointment
     * @throws ResourceNotFoundException
     */
    @PutMapping("/appointments/{id}")
    public Appointment updateAppointment(@PathVariable(value = "id") Long appointmentId,
                                                   @Valid @RequestBody Appointment appointmentDetails) throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));

        appointment.setTechnicians(appointmentDetails.getTechnicians());
        appointment.setRSA(appointmentDetails.getRSA());
        appointment.setCustomer(appointmentDetails.getCustomer());
        appointment.setStartDateTime(appointmentDetails.getStartDateTime());
        appointment.setEndDateTime(appointmentDetails.getEndDateTime());
        appointment.setFlooring(appointmentDetails.getFlooring());
        
        return appointmentRepository.save(appointment);
    }

    /**
     * deletes an appointment
     * @param appointmentId appointment to remove
     * @return removed appointment id
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/appointments/{id}")
    public long deleteAppointment(@PathVariable(value = "id") Long appointmentId)
            throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));

        appointmentRepository.delete(appointment);
        return appointment.getId();
    }
    
}