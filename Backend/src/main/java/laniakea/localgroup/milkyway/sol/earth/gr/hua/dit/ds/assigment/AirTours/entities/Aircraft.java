package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

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

    @NotBlank(message = "Please provide the aircraft type.")
    @Size(max = 30)
    private String type;

    //@NotEmpty(message = "Please provide the aircraft's number of seats.")
    private int noSeats;

    @NotBlank(message = "Please provide the aircraft's registration.")
    @Column(unique = true)
    @Size(max = 50)
    private String registration;

    //define constructors
    public Aircraft() {}

    public Aircraft(String type, String registration, int noSeats) {
        this.type = type;
        this.registration = registration;
        this.noSeats = noSeats;
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

