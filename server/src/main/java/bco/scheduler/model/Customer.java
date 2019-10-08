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
    @Enumerated
    @Column(name = "communication_preference", nullable = true)
    private CommunicationType communicationPreference;
    
    /** customer address */
//    @OneToOne(cascade = {CascadeType.ALL})
//    @JoinColumn(name = "address")
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
    public int getCommunicationPreference() {
        if (communicationPreference == null) {
            return -1;
        }
        return communicationPreference.ordinal();
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