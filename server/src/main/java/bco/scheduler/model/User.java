package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * User object, holds information related to users
 * @author Connor J. Parke
 */
@Entity
public class User extends Person {

    /** user username */
    private String username;
    
    /** user password hash NOT SECURE */
    private String passwordHash;
    
    /** default constructor */
    public User() {}

    /**
     * main constructor
     * 
     * @param firstName user first name
     * @param lastName user last name
     * @param email user email
     * @param phone user phone
     * @param inHouse user in house
     */
    public User(String firstName, String lastName, String email, String phone, String username, String passwordHash) {
        super(firstName, lastName, email, phone);
        this.username = username;
        this.passwordHash = passwordHash;
    }

    /**
     * gets username
     * @return username
     */
    @Column(name = "username", nullable = true)
    public String getUsername() {
        return username;
    }

    /**
     * sets username
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * gets password hash
     * @return password hash
     */
    @Column(name = "passwordHash", nullable = true)
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * sets password hash
     * @param passwordHash passwordHash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    
}