package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.HashMap;
import java.util.Map;

/**
 * Parent class for all person subtypes, e.g. rsa, technician, user
 * @author Connor J. Parke
 */
@MappedSuperclass
public class Person {
    public static final String CLASS_NAME = "person";

    /** person id */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    /** person first name */
    @Column(name = "first_name", nullable = true)
    private String firstName;
    
    /** person last name */
    @Column(name = "last_name", nullable = true)
    private String lastName;
    
    /** person phone */
    @Column(name = "phone", nullable = true)
    private String phone;
    
    /** person email */
    @Column(name = "email", nullable = true)
    private String email;
    
    public Person() {}
    
    /**
     * primary constructor
     * 
     * @param firstName person first name
     * @param lastName person last name
     * @param email person email
     * @param phone person phone number
     */
    protected Person(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    /**
     * gets id
     * @return id
     */
    public long getId() {
        return id;
    }
    
    /**
     * sets id
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * gets first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * sets first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /** 
     * gets last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /** 
     * sets last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * gets phone number
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * sets phone number
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * gets email address
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets email address
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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
        map.put("${" + CLASS_NAME + ".name" + "}", "Email/Text Recipient's Full Name");
        map.put("${" + CLASS_NAME + ".first_name" + "}", "Email/Text Recipient's First Name");
        map.put("${" + CLASS_NAME + ".last_name" + "}", "Email/Text Recipient's Last Name");
        return map;
    }
}