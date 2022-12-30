package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.ActiveTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveTourRepository extends JpaRepository<ActiveTour, Integer> {
    ActiveTour findById(int id);
}
