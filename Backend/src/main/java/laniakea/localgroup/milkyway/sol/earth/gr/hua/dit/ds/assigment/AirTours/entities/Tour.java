package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="tour")
public class Tour {

    //define fields
    @Id     //primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tour_id")
    private int id;

    @Column(name="name")
    @Size(max = 30)
    private String name;

    @Column(name="location")
    @Size(max = 50)
    private String location;

    @Column(name="duration")
    private float duration;

    // define constructors
    public Tour() {}

    public Tour(String name, String location, float duration) {
        this.name = name;
        this.location = location;
        this.duration = duration;
    }

    // define getters-setters
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) { this.location = location; }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    // print fields
    public String toString() {
        return "Tour [id= " + id + ", name= " + name + ", " +
                "location= " + location + ", duration= " + duration + "]";
    }
}
