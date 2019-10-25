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
import java.util.HashMap;
import java.util.Map;


/**
 * Customer object, holds information related to customers
 * @author Connor J. Parke
 */
@Entity
public class Customer extends Person {
    public static final String CLASS_NAME = "customer";

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
     * @param communicationPreference type preferred communication method
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

    /**
     * Get template variable mappings
     * @return template variable
     */
    public Map<String, String> getTemplateVariables() {
        Map<String, String> map = new HashMap<>();
        map.put(CLASS_NAME + ".name", this.getFirstName() + " " + this.getLastName());
        map.put(CLASS_NAME + ".first_name", this.getFirstName());
        map.put(CLASS_NAME + ".last_name", this.getLastName());
        return map;
    }

    /**
     * Get template variable descriptions
     * @return template variable descriptions
     */
    public static Map<String, String> getTemplateVariableDescriptions() {
        Map<String, String> map = new HashMap<>();
        map.put("${" + CLASS_NAME + ".name" + "}", "Customer's Full Name");
        map.put("${" + CLASS_NAME + ".first_name" + "}", "Customer's First Name");
        map.put("${" + CLASS_NAME + ".last_name" + "}", "Customer's Last Name");
        return map;
    }
}