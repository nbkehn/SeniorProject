package bco.scheduler.controller;

import javax.validation.Valid;

import bco.scheduler.cron.NotificationSender;
import bco.scheduler.model.TimeToSend;
import bco.scheduler.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import bco.scheduler.exception.ResourceNotFoundException;
import bco.scheduler.model.Appointment;
import bco.scheduler.model.Assignment;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Autowired
    private AssignmentController assignmentController;

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

    @GetMapping("/appointments/assignments/{id}")
    public List<Assignment> getAssignmentsForAppointment(@PathVariable(value = "id") Long appointmentId) throws ResourceNotFoundException {
        Appointment appointment = getAppointmentById(appointmentId);
        Set<Assignment> assignments = appointment.getAssignments();
        List<Assignment> returnList = new ArrayList<Assignment>();
        returnList.addAll(assignments);
        returnList.sort((a,b) -> {
            if (a.getDayNumber() < b.getDayNumber()) {
                return -1;
            }
            if (a.getDayNumber() > b.getDayNumber()) {
                return 1;
            }
            return 0;
        });
        return returnList;
    }

    /**
     * creates an appointment
     * 
     * @param appointment to create
     * @return created appointment
     */
    @PostMapping("/appointments")
    public Appointment createAppointment(@Valid @RequestBody Appointment appointment) {
        int length = Appointment.getExtraDays(appointment.getStartDate(), appointment.getEndDate());
        for (int i=0; i <= length; i++) {
            Assignment a = new Assignment(i + 1);
            Assignment newAssignment = assignmentController.createAssignment(a);
            appointment.addEmptyAssignment(newAssignment);
        }
        final Appointment newAppointment = appointmentRepository.saveAndFlush(appointment)
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
        Set<Assignment> assignments = appointment.getAssignments();
        ArrayList<Assignment> assignmentsList = new ArrayList<Assignment>();
        assignmentsList.addAll(assignments);
        assignmentsList.sort((a,b) -> {
            if (a.getDayNumber() < b.getDayNumber()) {
                return -1;
            }
            if (a.getDayNumber() > b.getDayNumber()) {
                return 1;
            }
            return 0;
        });
        int numDays = Appointment.getExtraDays(appointmentDetails.getStartDate(), appointmentDetails.getEndDate()) + 1;
        int numMissingAppointments = numDays - assignments.size();
        if (numMissingAppointments > 0) {
            int nextDayNumber = assignmentsList.get(assignmentsList.size() - 1).getDayNumber() + 1;
            for (int i = 0; i < numMissingAppointments; i++) {
                Assignment a = new Assignment(nextDayNumber);
                Assignment newAssignment = assignmentController.createAssignment(a);
                appointment.addEmptyAssignment(newAssignment);
                nextDayNumber++;
            }
        } else if (numMissingAppointments < 0) {
            long lastId = assignmentsList.get(assignmentsList.size() - 1).getId();
            int index = assignmentsList.size() - 1;
            for (int i = 0; i > numMissingAppointments; i--) {
                appointment.getAssignments().remove(assignmentsList.get(index));
                assignmentController.deleteAssignment(lastId);
                assignmentsList.remove(index);
                index--;
            }
        }
        Appointment appt = appointmentRepository.saveAndFlush(appointment);
        return appt;
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