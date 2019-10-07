package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
     * @return username username
     */
    @Column(name = "username", nullable = true)
    public String getUsername() {
        return username;
    }

    /**
     * sets password hash
     * @param passwordHash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}