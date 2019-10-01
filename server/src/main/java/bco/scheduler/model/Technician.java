package bco.scheduler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "technician")
public class Technician extends AbstractPerson {

    private boolean inHouse;

    public Technician(String firstName, String lastName, String email, String phone, boolean inHouse) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
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