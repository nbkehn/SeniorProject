package bco.scheduler.model;

import javax.persistence.*;


/**
 * Carrier object, holds information related to carriers
 * @author Noah Trimble
 */
@Entity
public class Carrier {

    /** carrier id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** carrier name */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /** carrier email domain */
    @Column(name = "email_domain", nullable = false)
    private String emailDomain;
    
    /** default constructor */
    public Carrier() {}

    /**
     * Carrier constructor
     * @param name name of carrie
     * @param emailDomain email domain of carrier
     */
    public Carrier(String name, String emailDomain) {
        this.name = name;
        this.emailDomain = emailDomain;
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
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name carrier name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get email domain
     * @return email domain
     */
    public String getEmailDomain() {
        return emailDomain;
    }

    /**
     * Set email domain
     * @param emailDomain email domain of carrier
     */
    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }
}