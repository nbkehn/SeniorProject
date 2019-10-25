package bco.scheduler.model;

import bco.scheduler.repository.CustomerRepository;
import bco.scheduler.repository.RSARepository;
import bco.scheduler.repository.TechnicianRepository;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDateTime;

/**
 * Appointment class, stitches together the person components and timeslots
 * 
 * @author Connor J. Parke
 *
 */
@Entity
public class Appointment {
    public static final String CLASS_NAME = "appointment";

    /** appointment id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    /** technician */
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "appointment")
    private List<Technician> technicians;
    
    /** RSA */
    @ManyToOne
    @JoinColumn(name = "rsa_id")
    private RSA rsa;
    
    /** Customer */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    /** start date time */
    @Column(name = "startDateTime", nullable = true)
    private LocalDateTime startDateTime;
    
    /** end date time */
    @Column(name = "endDateTime", nullable = true)
    private LocalDateTime endDateTime;
    
    /** default constructor */
    public Appointment() {}

    /**
     * main constructor
     * 
     * @param technicians appointment technicians
     * @param rsa appointment rsa
     * @param customer appointment customer
     * @param timeslots appointment timeslots
     */
    public Appointment(LocalDateTime startDateTime, LocalDateTime endDateTime, Customer customer, List<Technician> technicians, RSA rsa) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customer = customer;
        this.technicians = technicians;
        this.rsa = rsa;
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
     * gets technicians
     * @return technicans list
     */
    public List<Technician> getTechnicians() {
        return technicians;
    }

    /**
     * sets technicians
     * @param technicians technician list
     */
    public void setTechnicians(List<Technician> technicians) {
        this.technicians = technicians;
    }
    
    /**
     * gets the rsa
     * @return rsa
     */
    public RSA getRSA() {
        return rsa;
    }

    /**
     * sets rsa
     * @param rsa rsa
     */
    public void setRSA(RSA rsa) {
        this.rsa = rsa;
    }
    
    /**
     * gets the customer
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * sets customer
     * @param customer customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    /**
     * gets start date time
     * @return start date time
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * sets start date time
     * @param startDateTime 
     */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
    
    /**
     * gets end date time
     * @return end date time
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * sets end date time
     * @param endDateTime endDateTime 
     */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * Get template variable mappings
     * @return template variable
     */
    public Map<String, String> getTemplateVariables() {
        Map<String, String> map = new HashMap<>();
        map.put(CLASS_NAME + ".start_date_time", this.getStartDateTime().format(DateTimeFormatter.ofPattern("EEEE MMMM dd K:m a")));
        map.put(CLASS_NAME + ".start_date", this.getStartDateTime().format(DateTimeFormatter.ofPattern("EEEE MMMM dd")));
        map.put(CLASS_NAME + ".start_time", this.getStartDateTime().format(DateTimeFormatter.ofPattern("K:m a")));
        return map;
    }

    /**
     * Get template variable descriptions
     * @return template variable descriptions
     */
    public static Map<String, String> getTemplateVariableDescriptions() {
        Map<String, String> map = new HashMap<>();
        map.put("${" + CLASS_NAME + ".start_date_time" + "}", "Appointment Start Date and Time");
        map.put("${" + CLASS_NAME + ".start_date" + "}", "Appointment Start Date");
        map.put("${" + CLASS_NAME + ".start_time" + "}", "Appointment Start Time");
        return map;
    }
}