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



/**
 * Customer object, holds information related to customers
 * @author Connor J. Parke
 */
@Entity
public class Customer extends Person {

    /** customer communication preference */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "comPref", nullable = true)
    private CommunicationType comPref;
    
    /** customer address */
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address")
    private Address address;
    
    /** default constructor */
    public Customer() {}

    /**
     * main constructor
     * 
     * @param firstName customer first name
     * @param lastName customer last name
     * @param email customer email
     * @param phone customer phone
     * @param inHouse customer in house
     */
    public Customer(String firstName, String lastName, String email, String phone, CommunicationType comPref, Address address) {
        super(firstName, lastName, email, phone);
        this.comPref = comPref;
        this.address = address;
    }

    /**
     * gets communication preference
     * @return communication preference
     */
    public CommunicationType getComPref() {
        return comPref;
    }

    /**
     * sets communication preference
     * @param comPref 
     */
    public void setComPref(CommunicationType comPref) {
        this.comPref = comPref;
    }
    
    /**
     * gets address
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * sets address
     * @param address address 
     */
    public void setAddress(Address address) {
        this.address = address;
    }
}