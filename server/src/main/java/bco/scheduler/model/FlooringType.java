package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The flooring type class used to assign different types of flooring used for 
 * appointments. 
 * @author Noah Trimble, Will Duke
 */
@Entity
@Table(name = "flooringtype")
public class FlooringType {

    /**
     * An id generated for the flooring type.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The actual string representation of the flooring type.
     */
    @Column(name = "name", nullable = false)
    public String name;


    /**
     * The blank, unused constructor for flooring type.
     */
    public FlooringType() {

    }

    /**
     * The actual flooring type constructor with a passed floor type to set to. 
     * @param name the type to set the floor object to.
     */
    public FlooringType(String name) {
        this.name = name;
    }

    /**
     * Gets the id of the flooring type as a long 
     * @return id a long representation of the id 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of the flooring type to the passed long param. 
     * @param id a long representation of the id to set the flooring type object to.
     */
    public void setId(long id) {
        this.id = id;
    }
  
    /**
     * Gets the flooring type of the object represented as a string
     * @return name string representation of the flooring type
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the flooring type name parameter to the passed string name
     * @param name string representation of the flooring type to set to
     */
    public void setName(String name) {
        this.name = name;
    }
}