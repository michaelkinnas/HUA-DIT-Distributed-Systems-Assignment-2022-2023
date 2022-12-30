package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="aircraft")
public class Aircraft {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="aircraft_id")
    private int id;

    @Column(name="type")
    @Size(max = 30)
    private String type;

    @Column(name="no_seats")
    private int noSeats;

    @Column(name="registration", unique = true)
    @Size(max = 30)
    private String registration;

    //define constructors
    public Aircraft() {}

    public Aircraft(String type, int noSeats, String registration) {
        this.type = type;
        this.noSeats = noSeats;
        this.registration = registration;
    }

    // define getters-setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public int getNoSeats() {
        return noSeats;
    }

    public void setNoSeats(int noSeats) {
        this.noSeats = noSeats;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    // print fields
    public String toString() {
        return "Aircraft [id= " + id + ", noSeats= " + noSeats + ", " +
                "registration= " + registration + "]";
    }
}

