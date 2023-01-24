package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name="tour")
public class Tour {

    //define fields
    @Id     //primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Please provide a tour description.")
    @Size(max = 200)
    private String description;

    @NotBlank(message = "Please provide a tour location.")
    @Size(max = 100)
    private String location;

    @NotEmpty(message = "Please provide the duration of the tour.")
    private Integer duration;

    // define constructors
    public Tour() {

    }

    public Tour(String description, String location, Integer duration) {
        this.description = description;
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
        return description;
    }

    public void setName(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }



    // print fields
    public String toString() {
        return "Tour [id= " + id + ", description= " + description + ", " +
                "location= " + location + ", duration= " + duration + "]";
    }
}
