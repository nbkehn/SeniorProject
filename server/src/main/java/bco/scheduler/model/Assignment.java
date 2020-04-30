package bco.scheduler.model;

import javax.persistence.*;
import java.util.*;
import java.util.Set;

/**
 * An assignment is used to show what installer user is connected to an appointment.
 * There is one assignment per day that the appointment runs.
 * @author Michael "Alex" Gray
 *
 */
@Entity
public class Assignment {
    public static final String CLASS_NAME = "assignment";
/** appointment id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** technicians set */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "assignment")
    private Set<Technician> technicians;

    /* Day of appointment (i.e. 1 to represent 1/2) */
    @Column(name = "dayNumber")
    private int dayNumber;

    public Assignment(){
    }

    public Assignment(int dayNumber){
        this(dayNumber, null);
    } 

    public Assignment(int dayNumber, Set<Technician> technicians ){
        setDayNumber(dayNumber);
        setTechnicians(technicians);
    }

    public long getId() {
        return this.id;
    }

    /**
     * gets the day of the appointment this assignment is associated with
     * @return number of the day of the appointment
     */
    public int getDayNumber() {
        return this.dayNumber;
    }

    /**
     * sets the day of the appointment this assignment is associated with
     * @param dayNumber 
     */
    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
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
    public void setTechnicians(Set<Technician> technicians) {
        this.technicians = technicians;
    
    }

    public void addTechnician(Technician t) {
        if (getTechnicians() == null) {
            setTechnicians(new HashSet<Technician>());
        }
        getTechnicians().add(t);
    }

    
}