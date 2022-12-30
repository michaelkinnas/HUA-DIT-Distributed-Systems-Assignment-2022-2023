package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="active_tour")
public class ActiveTour {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="active_tour_id")
    private int id;

    @Column(name="name")
    @Size(max = 30)
    private String name;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.MERGE, CascadeType.PERSIST})
    private List<User> users;

    @OneToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @OneToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    //define constructors
    public ActiveTour() {}

    public ActiveTour(String name) {
        this.name = name;
    }

    // define getters-setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String type) { this.name = name; }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    // print fields
    public String toString() {
        return "Active Tour [id= " + id + ", name= " + name + "]";
    }
}
