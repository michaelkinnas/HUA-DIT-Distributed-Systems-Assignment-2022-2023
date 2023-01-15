package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Flight;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.FlightRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.TourRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pilot")
public class PilotController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    FlightRepository flightRepository;

    @GetMapping("/active-tour/{pilotId}")
    Flight get(@PathVariable Long pilotId) {
        return flightRepository.findFlightByPilotId(pilotId);
    }

    @PostMapping("/create-flight/{pilotId}")
    List<Flight> createFlight(@PathVariable Long pilotId, @RequestBody Flight flight) {
        flightRepository.save(flight);
        return flightRepository.findAll();
    }

    @PostMapping("/close-tour/{pilotId}")
    public void closeFlight(@PathVariable Long pilotId) {
        flightRepository.deleteFlightByPilotId(pilotId);
    }

}
