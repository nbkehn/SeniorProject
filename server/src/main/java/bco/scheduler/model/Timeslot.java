package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.time.ZonedDateTime;



/**
 * Customer object, holds information related to customers
 * @author Connor J. Parke
 */
@Entity
public class Timeslot {

    /** timeslot id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    /** start date time */
    @Column(name = "startDateTime", nullable = true)
    private ZonedDateTime startDateTime;
    
    /** end date time */
    @Column(name = "endDateTime", nullable = true)
    private ZonedDateTime endDateTime;
    
    /** default constructor */
    public Timeslot() {}

    /**
     * main constructor
     * 
     * @param firstName customer first name
     * @param lastName customer last name
     */
    public Timeslot(ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * gets start date time
     * @return start date time
     */
    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * sets start date time
     * @param startDateTime 
     */
    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
    
    /**
     * gets end date time
     * @return end date time
     */
    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * sets end date time
     * @param endDateTime endDateTime 
     */
    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}