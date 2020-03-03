package bco.scheduler.model;

import bco.scheduler.repository.TechnicianRepository;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

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

    /** start date time */
    @Column(name = "startDateTime")
    private LocalDate startDate;

    public Assignment(){
    }

    public Assignment(LocalDate date){
        setStartDate(date);
    } 

    public Assignment(LocalDate date, Set<Technician> technicians ){
        setStartDate(date);
        setTechnicians(technicians);
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
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    
}