package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * @author Connor J. Parke
 *
 */
@Entity
public class Address {
    
    /** address id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    /** line one */
    @Column(name = "line_1", nullable = true)
    private String line1;
    
//    /** line two */
//    @Column(name = "line_2", nullable = true)
//    private String line2;
//    
//    /** state */
//    @Column(name = "state", nullable = true)
//    private String state;
//    
//    /** city */
//    @Column(name = "city", nullable = true)
//    private String city;
//    
//    /* zip code */
//    @Column(name = "zip_code", nullable = true)
//    private String zipCode;
    
    /** default constructor */
    public Address() {}
    
//    /**
//     * main constructor
//     * 
//     * @param line1 line 1 of address
//     * @param line2 line 2 oi address
//     * @param state state
//     * @param city city
//     * @param zipCode zip code
//     */
//    public Address(String line1, String line2, String state, String city, String zipCode) {
//        this.line1 = line1;
//        this.line2 = line2;
//        this.state = state;
//        this.city = city;
//        this.zipCode = zipCode;
//    }
    /**
     * main constructor
     * 
     * @param line1 line 1 of address
     * @param line2 line 2 oi address
     * @param state state
     * @param city city
     * @param zipCode zip code
     */
    public Address(String line1) {
        this.line1 = line1;
    }  
  
    
    /**
     * gets id
     * @return id id
     */
    public long getId() {
        return id;
    }
    
    /**
     * sets id
     * @param id id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * gets line one
     * @return line one
     */
    public String getLine1() {
        return line1;
    }

    /**
     * sets line one
     * @param line1
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

//    /**
//     * gets line two
//     * @return line two 
//     */
//    public String getLine2() {
//        return line2;
//    }
//
//    /**
//     * sets line two
//     * @param line2
//     */
//    public void setLine2(String line2) {
//        this.line2 = line2;
//    }
//
//    /**
//     * gets state
//     * @return state
//     */
//    public String getState() {
//        return state;
//    }
//
//    /**
//     * sets state
//     * @param state
//     */
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    /**
//     * gets city
//     * @return city
//     */
//    public String getCity() {
//        return city;
//    }
//
//    /** 
//     * sets city
//     * @param city
//     */
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    /**
//     * gets zip code
//     * @return zip code
//     */
//    public String getZipCode() {
//        return zipCode;
//    }
//
//    /**
//     * sets zip code
//     * @param zipCode
//     */
//    public void setZipCode(String zipCode) {
//        this.zipCode = zipCode;
//    }
}