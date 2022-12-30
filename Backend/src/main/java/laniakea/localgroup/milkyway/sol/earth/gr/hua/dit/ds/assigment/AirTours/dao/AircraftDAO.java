package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.dao;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Aircraft;

import javax.transaction.Transactional;
import java.util.List;

public interface AircraftDAO {

    public List<Aircraft> getAllAircrafts();

    public void save(Aircraft aircraft);

    public Aircraft findById(int id);

    void delete(Aircraft aircraft);
}
