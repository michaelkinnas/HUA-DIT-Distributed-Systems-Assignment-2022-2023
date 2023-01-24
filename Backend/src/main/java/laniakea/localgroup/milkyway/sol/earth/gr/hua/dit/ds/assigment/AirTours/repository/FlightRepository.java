package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    //List

    Flight findFlightById(Long id);

    List<Flight> findByPilot_Id(Long pId);

    void deleteByPilot_Id(Long pId);
}
