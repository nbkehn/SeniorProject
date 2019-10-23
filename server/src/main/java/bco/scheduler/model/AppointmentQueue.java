package bco.scheduler.model;

import javax.persistence.*;


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

    /** appointment's ID */
    @Column(name="appointment_id", nullable = false)
    private long appointmentId;

    /** reminder's ID */
    @Column(name="reminder_id", nullable = false)
    private long reminderId;

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
     * @param appointmentId connected appointment ID
     * @param reminderId connected reminder ID
     */
    public AppointmentQueue(long appointmentId, long reminderId) {
        this.appointmentId = appointmentId;
        this.reminderId = reminderId;
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
     * Get appointment id
     * @return appointment id
     */
    public long getAppointmentId() {
        return appointmentId;
    }

    /**
     * Set appointment id
     * @param appointmentId connected appointment id
     */
    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Get reminder id
     * @return reminder id
     */
    public long getReminderId() {
        return reminderId;
    }

    /**
     * Set reminder id
     * @param reminderId connected reminder id
     */
    public void setReminderId(long reminderId) {
        this.reminderId = reminderId;
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