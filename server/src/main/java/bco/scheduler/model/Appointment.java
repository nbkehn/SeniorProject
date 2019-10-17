package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Appointment class, stitches together the person components and timeslots
 * 
 * @author Connor J. Parke
 *
 */
@Entity
public class Appointment {

    /** appointment id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    /** technicians */
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "technicians")
    private List<Technician> technicians;
    
    /** RSA */
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "rsas")
    private RSA rsa;
    
    /** Customer */
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customers")
    private Customer customer;
    
    /** Time slots */
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "timeslots")
    private List<Timeslot> timeslots;
    
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
    public Appointment(List<Technician> technicians, RSA rsa, Customer customer, List<Timeslot> timeslots) {
        this.technicians = technicians;
        this.rsa = rsa;
        this.customer = customer;
        this.timeslots = timeslots;
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
    public void setInHouse(List<Technicians> technicians) {
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
     * gets the time slots
     * @return list of time slots
     */
    public List<Timeslot> getTimeslots() {
        return timeslots;
    }

    /**
     * sets time slots
     * @param timeslots timeslots
     */
    public void setCustomer(List<Timeslot> timeslot) {
        this.timeslots = timeslots;
    }
}