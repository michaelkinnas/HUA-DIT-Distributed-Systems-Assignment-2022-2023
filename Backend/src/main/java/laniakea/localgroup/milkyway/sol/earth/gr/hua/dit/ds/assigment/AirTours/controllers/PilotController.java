package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Aircraft;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Flight;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Tour;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.AircraftRepository;
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

    @Autowired
    AircraftRepository aircraftRepository;

    @GetMapping("/active-flight/{pilotId}")
    Flight get(@PathVariable Long pilotId) {
        Long pId = userRepository.findById(pilotId).getId();//find user (pilot) object and get id from the object
        List<Flight> flights = flightRepository.findByPilot_Id(pId);
        Flight openFlight = null;
        for (Flight tempFlight : flights) {
            if (tempFlight.getOpen() == true) {
                openFlight = tempFlight;
            }
        }
        return openFlight;
    }


    @PostMapping("/create-flight/{pilotId}")
    Flight createFlight(@PathVariable Long pilotId, @RequestBody Flight flight) {
        Long pId = userRepository.findById(pilotId).getId();
        List<Flight> flights = flightRepository.findByPilot_Id(pId);
        Flight openFlight = null;
        for (Flight tempFlight : flights) {
            if (tempFlight.getOpen() == true) {
                openFlight = tempFlight;
            }
        }
        if (openFlight == null) {
            flightRepository.save(flight);
            return flight;
        }else {
            return  null;
        }
    }

    @PostMapping("/close-tour/{pilotId}")
    void closeFlight(@PathVariable Long pilotId) throws Exception {
        Long pId = userRepository.findById(pilotId).getId();
        List<Flight> flights = flightRepository.findByPilot_Id(pId);
        Flight openFlight = null;
        for (Flight tempFlight : flights) {
            if (tempFlight.getOpen() == true) {
                openFlight = tempFlight;
            }
        }
        if (openFlight == null) {
            throw new Exception("No open flight exists.");
        } else {
            flightRepository.deleteByPilot_Id(pId);
        }
    }

    @GetMapping("/aircraft")
    List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    @GetMapping("/tours")
    List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

}
