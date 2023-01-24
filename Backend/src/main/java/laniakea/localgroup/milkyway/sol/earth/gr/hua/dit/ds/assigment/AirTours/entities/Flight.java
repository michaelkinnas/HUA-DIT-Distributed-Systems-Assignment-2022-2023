package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="Flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max = 100)
    @NotBlank(message = "Please provide a flight name.")
    private String flightName;

    @OneToOne
    private User pilot;

    @OneToMany(cascade = CascadeType.ALL)
    private List<User> passengers;

    @OneToOne(cascade = CascadeType.ALL)
    private Aircraft flightAircraft;

    @OneToOne
    private Tour flightTour;

    private  boolean open;

    public Flight() {}

    public Flight(String flightName, User pilot, Aircraft flightAircraft, Tour flightTour, boolean open) {
        this.flightName = flightName;
        this.pilot = pilot;
        this.flightAircraft = flightAircraft;
        this.flightTour = flightTour;
        this.open = open;
    }

    public List<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<User> passengers) {
        passengers.addAll(passengers);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public User getPilot() {
        return pilot;
    }

    public void setPilot(User pilot) {
        this.pilot = pilot;
    }

    public Aircraft getFlightAircraft() {
        return flightAircraft;
    }

    public void setFlightAircraft(Aircraft flightAircraft) {
        this.flightAircraft = flightAircraft;
    }

    public Tour getFlightTour() {
        return flightTour;
    }

    public void setFlightTour(Tour flightTour) {
        this.flightTour = flightTour;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
    public String toString() {
        return "Flight [id= " + id + ", flightName= " + flightName + ", pilot= " + pilot + ", flightAircraft= " + flightAircraft + "" +
                ", flightTour= " + flightTour + ", open= " + open + ", passengers= " + passengers + "]";
    }
}
