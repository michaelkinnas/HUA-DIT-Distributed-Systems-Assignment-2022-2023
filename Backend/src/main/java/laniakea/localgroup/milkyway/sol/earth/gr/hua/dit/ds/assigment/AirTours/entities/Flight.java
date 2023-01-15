package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="Flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(max = 100)
    private String activeTourName;

    @Column(name = "pilotId")
    private int pilotId;

    @OneToMany(cascade=CascadeType.ALL)
    public List<User> passengers;

    public Flight() {}

    public Flight(String activeTourName, int pilotId) {
        this.activeTourName = activeTourName;
        this.pilotId = pilotId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActiveTourName() {
        return activeTourName;
    }

    public void setActiveTourName(String activeTourName) {
        this.activeTourName = activeTourName;
    }

    public int getPilotId() {
        return pilotId;
    }

    public void setPilotId(int pilotId) {
        this.pilotId = pilotId;
    }
    public String toString() {
        return "Active Tour [id= " + id + ", activeTourName= " + activeTourName + ", pilotId= " + pilotId + "]";
    }
}
