package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.dao;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Tour;

import javax.transaction.Transactional;
import java.util.List;

public interface TourDAO {

    public List<Tour> getAllTours();

    public void save(Tour tour);

    public Tour findById(int id);

    void delete(Tour tour);
}