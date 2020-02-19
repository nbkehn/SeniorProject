package bco.scheduler.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Set;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Appointment class, stitches together the person components and timeslots, and flooring type
 * 
 * @author Connor J. Parke
 *
 */
@Entity
@JsonInclude(Include.NON_EMPTY)
public class Appointment {
    public static final String CLASS_NAME = "appointment";

    /** appointment id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    /** technicians set */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment")
    private Set<Technician> technicians;
    
    /** RSA */
    @ManyToOne
    @JoinColumn(name = "rsa_id")
    private RSA rsa;
    
    /** Customer */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    /** start date time */
    @Column(name = "startDate")
    private LocalDate startDate;
    
    /** end date time */
    @Column(name = "endDate")
    private LocalDate endDate;
    
    /** flooring category */
    @ManyToOne
    @JoinColumn(name = "flooring_id")   
    private FlooringType flooring;
    
    /** default constructor */
    public Appointment() {}

    /**
     * main constructor
     * 
     * @param startDateTime starting time of the 
     */
    public Appointment(final RSA rsa, final Customer customer, final Set<Technician> technicians, final FlooringType flooringtype, final Date startDateTime, final Date endDateTime) {
        this.rsa = rsa;
        this.customer = customer;
        this.technicians = technicians;
        this.flooring = flooring; 
        this.startDate = startDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.endDate = endDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * gets technicians set
     * @return technicans set
     */
    public Set<Technician> getTechnicians() {
        return technicians;
    }

    /**
     * sets technicians
     * @param technicians technician list
     */
    public void setTechnicians(final Set<Technician> technicians) {
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
    public void setRSA(final RSA rsa) {
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
    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }
    
    /**
     * gets start date time
     * @return start date time
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * sets start date time
     * @param startDateTime 
     */
    public void setStartDateTime(final LocalDate startDateTime) {
        this.startDate = startDateTime;
    }
    
    /**
     * gets end date time
     * @return end date time
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * sets end date time
     * @param endDateTime endDateTime 
     */
    public void setEndDateTime(final LocalDate endDateTime) {
        this.endDate = endDateTime;
    }

    /**
     * gets flooring type
     * @return flooring type
     */
    public FlooringType getFlooring() {
        return flooring;
    }

    /**
     * sets flooring type
     * @param flooringType flooring type 
     */
    public void setFlooring(final FlooringType flooring) {
        this.flooring = flooring;
    }

    /**
     * Get template variable mappings
     * @return template variable
     */
    public Map<String, String> getTemplateVariables() {
        final Map<String, String> map = new HashMap<String, String>();
        map.put(CLASS_NAME + ".start_date", this.getStartDate().toString());
        map.put(CLASS_NAME + ".end_date", this.getEndDate().toString());
        map.put(CLASS_NAME + ".customer_name", this.getCustomer().getFirstName() + " " + this.getCustomer().getLastName());
        map.put(CLASS_NAME + ".rsa_name", this.getRSA().getFirstName() + " " + this.getRSA().getLastName());
        map.put(CLASS_NAME + ".tech_names", this.getTechnicianNames());
        map.put(CLASS_NAME + ".flooring", this.getFlooring().getName());
        return map;
    }

    /**
     * Get comma-separated list of technician names on the appointment
     * @return technician names
     */
    private String getTechnicianNames() {
        final List<String> technicianNames = new ArrayList<>();
        for (final Technician technician : this.getTechnicians()) {
            technicianNames.add(technician.getFirstName() + " " + technician.getLastName());
        }

        return String.join(", ", technicianNames);
    }

    /**
     * Get template variable descriptions
     * @return template variable descriptions
     */
    public static Map<String, String> getTemplateVariableDescriptions() {
        final Map<String, String> map = new HashMap<>();
        map.put("${" + CLASS_NAME + ".start_date" + "}", "Appointment Start Date");
        map.put("${" + CLASS_NAME + ".end_date" + "}", "Appointment End Date");
        map.put("${" + CLASS_NAME + ".customer_name" + "}", "Appointment Customer Name");
        map.put("${" + CLASS_NAME + ".rsa_name" + "}", "Appointment RSA Name");
        map.put("${" + CLASS_NAME + ".tech_names" + "}", "Appointment Technician Names");
        map.put("${" + CLASS_NAME + ".flooring" + "}", "Appointment Flooring Type");
        return map;
    }
}