package bco.scheduler.model;

import javax.persistence.*;

/**
 * Reminder object, holds information related to reminders
 * @author Noah Trimble, Connor J. Parke
 */
@Table(name = "reminder",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "user_type", "time_to_send" }) })
@Entity
public class Reminder {

    /** reminder id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    /** time to send reminder */
    @Column(name = "time_to_send", nullable = false)
    private int timeToSend;

    /** chosen text template */
    @ManyToOne
    @JoinColumn(name="text_template_id", nullable = false)
    private Template textTemplate;

    /** user to send reminder to */
    @Enumerated
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    /** chosen email template */
    @ManyToOne
    @JoinColumn(name="email_template_id", nullable = false)
    private Template emailTemplate;

    /** default constructor */
    public Reminder() {}

    /**
     * Reminder constructor
     * @param timeToSend time to send reminder
     * @param textTemplateId text template id to use
     * @param emailTemplateId email template id to use
     * @param userType user type attached to reminder
     */
    public Reminder(int timeToSend, Template textTemplate, Template emailTemplate, UserType userType) {
        this.timeToSend = timeToSend;
        this.textTemplate = textTemplate;
        this.emailTemplate = emailTemplate;
        this.userType = userType;
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
    public int getTimeToSend() {
        return timeToSend;
    }

    /**
     * Set time to send
     * @param timeToSend time to send
     */
    public void setTimeToSend(int timeToSend) {
        this.timeToSend = timeToSend;
    }

    /**
     * Get user type attached to reminder
     * @return user type
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Set user type on reminder
     * @param userType user type to put on reminder
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
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