package bco.scheduler.model;

import javax.persistence.*;

/**
 * Reminder object, holds information related to reminders
 * @author Noah Trimble, Connor J. Parke
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

    /** chosen text template */
    @ManyToOne
    @JoinColumn(name="text_template_id")
    private Template textTemplate;

    /** chosen email template */
    @ManyToOne
    @JoinColumn(name="email_template_id")
    private Template emailTemplate;

    /** default constructor */
    public Reminder() {}

    /**
     * Reminder constructor
     * @param timeToSend time to send reminder
     * @param textTemplateId text template id to use
     * @param emailTemplateId email template id to use
     */
    public Reminder(TimeToSend timeToSend, Template textTemplate, Template emailTemplate) {
        this.timeToSend = timeToSend;
        this.textTemplate = textTemplate;
        this.emailTemplate = emailTemplate;
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
     * Get text template
     * @return text template
     */
    public Template getTextTemplate() {
        return textTemplate;
    }

    /**
     * Set text template
     * @param textTemplateId text template
     */
    public void setTextTemplate(Template textTemplate) {
        this.textTemplate = textTemplate;
    }

    /**
     * Get email template
     * @return email template
     */
    public Template getEmailTemplate() {
        return emailTemplate;
    }

    /**
     * Set email template
     * @param emailTemplateId email template
     */
    public void setEmailTemplate(Template emailTemplate) {
        this.emailTemplate = emailTemplate;
    }
}