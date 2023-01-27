package laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="aircraft")
public class Aircraft {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String type;

    private int numberOfSeats;

    private String registration;

    //define constructors
    public Aircraft() {}

    public Aircraft(String type, int numberOfSeats, String registration) {
        this.type = type;
        this.numberOfSeats = numberOfSeats;
        this.registration = registration;

    }

    // define getters-setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    // print fields
    public String toString() {
        return "Aircraft [id= " + id + ", type= " + type + ", numberOfSeats= " + numberOfSeats + ", " +
                "registration= " + registration + "]";
    }
}

