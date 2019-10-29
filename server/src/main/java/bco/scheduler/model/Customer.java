package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

/**
 * Customer object, holds information related to customers
 * @author Connor J. Parke
 */
@Entity
public class Customer extends Person {

    /** customer communication preference */
    @Enumerated
    @Column(name = "communication_preference", nullable = true)
    private CommunicationType communicationPreference;
    
    /** customer address */
    @Column(name = "address", nullable = true)
    private String address;
    
    /** default constructor */
    public Customer() {}

    /**
     * main constructor
     * 
     * @param firstName customer first name
     * @param lastName customer last name
     * @param email customer email
     * @param phone customer phone
     * @param communication type prefered communication method
     * @param address customer address
     */
    public Customer(String firstName, String lastName, String email, String phone, CommunicationType communicationPreference, String address) {
        super(firstName, lastName, email, phone);
        this.communicationPreference = communicationPreference;
        this.address = address;
    }

    /**
     * gets communication preference
     * @return communication preference
     */
    public CommunicationType getCommunicationPreference() {
        return communicationPreference;
    }

    /**
     * sets communication preference
     * @param communicationPreference 
     */
    public void setCommunicationPreference(CommunicationType communicationPreference) {
        this.communicationPreference =  communicationPreference;
    }
    
    /**
     * gets address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets address
     * @param address address 
     */
    public void setAddress(String address) {
        this.address = address;
    }
}