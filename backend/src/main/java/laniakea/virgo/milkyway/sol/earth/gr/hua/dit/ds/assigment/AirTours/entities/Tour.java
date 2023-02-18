package laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

import javax.persistence.*;

@Entity
@Table(name="tours")
public class Tour {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private int id;

    private String description;

    private String location;

    private int duration;

    public Tour() {
    }

    public Tour(String description, String location, int duration) {
        this.description = description;
        this.location = location;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }



    // print fields
    public String toString() {
        return "Tour [id= " + id + ", description= " + description + ", " +
                "location= " + location + ", duration= " + duration + "]";
    }
}
