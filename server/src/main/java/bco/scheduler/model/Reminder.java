package bco.scheduler.model;

import javax.persistence.*;

/**
 * Reminder object, holds information related to reminders
 * @author Noah Trimble, Connor J. Parke
 */
@Table(name = "reminder",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "user", "time_to_send" }) })
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

    /** user to send reminder to */
    @Enumerated
    @Column(name = "user", nullable = false)
    private UserType user;

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
     * @param user user attached to reminder
     */
    public Reminder(TimeToSend timeToSend, Template textTemplate, Template emailTemplate, UserType user) {
        this.timeToSend = timeToSend;
        this.textTemplate = textTemplate;
        this.emailTemplate = emailTemplate;
        this.user = user;
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
     * Get user attached to reminder
     * @return user
     */
    public UserType getUser() {
        return user;
    }

    /**
     * Set user on reminder
     * @param user user to put on reminder
     */
    public void setUser(UserType user) {
        this.user = user;
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