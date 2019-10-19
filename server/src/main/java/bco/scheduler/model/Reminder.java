package bco.scheduler.model;

import javax.persistence.*;


/**
 * Reminder object, holds information related to reminders
 * @author Noah Trimble
 */
@Entity
public class Reminder {

    /** reminder id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** time to send reminder */
    @Enumerated
    @Column(name = "time_to_send", nullable = false, unique = true)
    private TimeToSend timeToSend;

    /** chosen text template's ID */
    @Column(name="text_template_id", nullable = false)
    private long textTemplateId;

    /** chosen email template's ID */
    @Column(name="email_template_id", nullable = false)
    private long emailTemplateId;

    /** default constructor */
    public Reminder() {}

    /**
     * Reminder constructor
     * @param timeToSend time to send reminder
     * @param textTemplateId text template id to use
     * @param emailTemplateId email template id to use
     */
    public Reminder(TimeToSend timeToSend, long textTemplateId, long emailTemplateId) {
        this.timeToSend = timeToSend;
        this.textTemplateId = textTemplateId;
        this.emailTemplateId = emailTemplateId;
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
     * Get time to send
     * @return time to send
     */
    public TimeToSend getTimeToSend() {
        return timeToSend;
    }

    /**
     * Set time to send
     * @param timeToSend time to send
     */
    public void setTimeToSend(TimeToSend timeToSend) {
        this.timeToSend = timeToSend;
    }

    /**
     * Get text template id
     * @return text template id
     */
    public long getTextTemplateId() {
        return textTemplateId;
    }

    /**
     * Set text template id
     * @param textTemplateId text template id
     */
    public void setTextTemplateId(long textTemplateId) {
        this.textTemplateId = textTemplateId;
    }

    /**
     * Get email template id
     * @return email template id
     */
    public long getEmailTemplateId() {
        return emailTemplateId;
    }

    /**
     * Set email template id
     * @param emailTemplateId email template id
     */
    public void setEmailTemplateId(long emailTemplateId) {
        this.emailTemplateId = emailTemplateId;
    }
}