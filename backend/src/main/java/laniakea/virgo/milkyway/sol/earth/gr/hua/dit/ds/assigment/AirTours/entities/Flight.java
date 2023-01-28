package laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
//    @ColumnDefault("true")
    private boolean open = true;

    @OneToOne
    private User pilot;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<User> passengers;

    @OneToOne(cascade = CascadeType.MERGE)
    private Aircraft aircraft;

    @OneToOne(cascade = CascadeType.MERGE)
    private Tour tour;



    public Flight() {}

    public Flight(String name, User pilot, List<User> passengers, Aircraft aircraft, Tour tour) {
        this.name = name;
        this.pilot = pilot;
        this.passengers = passengers;
        this.aircraft = aircraft;
        this.tour = tour;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public User getPilot() {
        return pilot;
    }

    public void setPilot(User pilot) {
        this.pilot = pilot;
    }

    public List<User> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<User> passengers) {
        this.passengers = passengers;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }


    public String toString() {
        return "Flight [id= " + id + ", flightName= " + name + ", pilot= " + pilot + ", flightAircraft= " + aircraft + "" +
                ", flightTour= " + tour + ", open= " + open + ", passengers= " + passengers + "]";
    }
}
