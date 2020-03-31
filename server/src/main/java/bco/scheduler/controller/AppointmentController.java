package bco.scheduler.controller;

import javax.validation.Valid;

import bco.scheduler.cron.NotificationSender;
import bco.scheduler.model.TimeToSend;
import bco.scheduler.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.Appointment;

import java.util.List;
import java.util.Map;

/**
 * Appointment Controller
 * 
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

    /** notification sender */
    @Autowired
    private NotificationSender notificationSender;

    /**
     * returns all appointments
     * 
     * @return appointments list
     */
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        List<Appointment> aps = appointmentRepository.findAll();
        for (Appointment a : aps) {
            a.setStartDateTime(a.getStartDate().plusDays(1));
            a.setEndDateTime(a.getEndDate().plusDays(1));
        }
        return aps;
    }

    /**
     * gets an appointment by id
     * 
     * @param appointmentId id
     * @return appointment with id
     * @throws ResourceNotFoundException
     */
    @GetMapping("/appointments/{id}")
    public Appointment getAppointmentById(@PathVariable(value = "id") Long appointmentId)
            throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                () -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));
        appointment.setStartDateTime(appointment.getStartDate().plusDays(1));
        appointment.setEndDateTime(appointment.getEndDate().plusDays(1));
        return appointment;
    }

    /**
     * creates an appointment
     * 
     * @param appointment to create
     * @return created appointment
     */
    @PostMapping("/appointments")
    public Appointment createAppointment(@Valid @RequestBody Appointment appointment) {
        final Appointment newAppointment = appointmentRepository.saveAndFlush(appointment);
        System.out.println(appointment.getStartDate());
        System.out.println(appointment.getStartDate());
        appointmentRepository.refresh(newAppointment);
        appointmentQueueRepository.addNewAppointment(newAppointment.getId());

        Runnable r = () -> notificationSender.sendNotificationsForOffset(newAppointment,
                TimeToSend.APPOINTMENT_CREATION.getOffset());
        new Thread(r).start();

        return newAppointment;
    }

    /**
     * updates an appointment
     * 
     * @param appointmentId      appointment to update
     * @param appointmentDetails updated appointment
     * @return updated appointment
     * @throws ResourceNotFoundException
     */
    @PutMapping("/appointments/{id}")
    public Appointment updateAppointment(@PathVariable(value = "id") Long appointmentId,
            @Valid @RequestBody Appointment appointmentDetails) throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                () -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));

        appointment.setTechnicians(appointmentDetails.getTechnicians());
        appointment.setRSA(appointmentDetails.getRSA());
        appointment.setCustomer(appointmentDetails.getCustomer());
        appointment.setStartDateTime(appointmentDetails.getStartDate());
        appointment.setEndDateTime(appointmentDetails.getEndDate());
        appointment.setFlooring(appointmentDetails.getFlooring());

        return appointmentRepository.save(appointment);
    }

    /**
     * deletes an appointment
     * 
     * @param appointmentId appointment to remove
     * @return removed appointment id
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/appointments/{id}")
    public long deleteAppointment(@PathVariable(value = "id") Long appointmentId) throws ResourceNotFoundException {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                () -> new ResourceNotFoundException("Appointment not found for this id :: " + appointmentId));

        appointmentRepository.delete(appointment);
        return appointment.getId();
    }

    /**
     * sends OTW message for appointment
     * 
     * @param appointment appointment to send OTW message for
     * @return whether message was set of not
     */
    @PostMapping("/appointments/sendOTW")
    public Map<String, String> sendOTWMessage(@Valid @RequestBody Appointment appointment) {
        return notificationSender.sendNotificationsForOffset(appointment, TimeToSend.OTW.getOffset());
    }

}