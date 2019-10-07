package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Technician extends Person {

    private boolean inHouse;
    
    public Technician() {
        super();
    }

    public Technician(String firstName, String lastName, String email, String phone, boolean inHouse) {
        super(firstName, lastName, email, phone);
        this.inHouse = inHouse;
    }

    @Column(name = "in_house", nullable = true)
    public boolean isInHouse() {
        return inHouse;
    }

    public void setInHouse(boolean inHouse) {
        this.inHouse = inHouse;
    }
}