package laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository;

import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {

}
