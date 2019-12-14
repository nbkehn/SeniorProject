package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author Noah Trimble
 * @editedBy Connor J. Parke
 *
 */
@Entity
public class Technician extends Person {

    /** is in house technician */
    @Column(name = "in_house", nullable = true)
    private boolean inHouse;
    
    /** default constructor */
    public Technician() {}

    /**
     * main constructor
     * 
     * @param firstName technician first name
     * @param lastName technician last name
     * @param email technician email
     * @param phone technician phone
     * @param inHouse technician in house
     */
    public Technician(String firstName, String lastName, String email, String phone, boolean inHouse) {
        super(firstName, lastName, email, phone);
        this.inHouse = inHouse;
    }

    /**
     * gets is in house
     * @return in house
     */
    public boolean isInHouse() {
        return inHouse;
    }

    /**
     * sets in house
     * @param inHouse
     */
    public void setInHouse(boolean inHouse) {
        this.inHouse = inHouse;
    }
}