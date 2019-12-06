package bco.scheduler.model;

import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


/**
 * Appointment queue object, holds information related to appointment queues
 * @author Noah Trimble
 */
@Entity
public class AppointmentQueue {

    /** appointment queue id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** appointment */
    @ManyToOne
    @JoinColumn(name="appointment_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Appointment appointment;

    /** reminder */
    @ManyToOne
    @JoinColumn(name="reminder_id", nullable = false)
    private Reminder reminder;

    /** has the reminder been sent */
    @Column(name = "is_sent", nullable = false, columnDefinition = "int default 0")
    private boolean isSent;

    /** error message if reminder send fails */
    @Column(name = "error_message", columnDefinition="TEXT")
    private String errorMessage;

    /** default constructor */
    public AppointmentQueue() {}

    /**
     * Appointment queue constructor
     * @param appointment connected appointment
     * @param reminder connected reminder
     */
    public AppointmentQueue(Appointment appointment, Reminder reminder) {
        this.appointment = appointment;
        this.reminder = reminder;
        this.isSent = false;
    }

    /**
     * gets id
     * @return id
     */
    public long getId() {
        return id;
    }
    
    /**
     * sets id
     * @param id ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get appointment 
     * @return appointment 
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Set appointment
     * @param appointment connected appointment
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    /**
     * Get reminder
     * @return reminder
     */
    public Reminder getReminder() {
        return reminder;
    }

    /**
     * Set reminder 
     * @param reminder connected reminder
     */
    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    /**
     * Get whether the reminder has been sent for an appointment
     * @return is sent
     */
    public boolean isSent() {
        return isSent;
    }

    /**
     * Mark the reminder as sent
     * @param sent has the reminder been sent
     */
    public void setSent(boolean sent) {
        isSent = sent;
    }

    /**
     * Get error message
     * @return error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set error message
     * @param errorMessage error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}