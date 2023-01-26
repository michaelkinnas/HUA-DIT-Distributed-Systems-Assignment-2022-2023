package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Aircraft;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Flight;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Tour;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.AircraftRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.FlightRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.TourRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

//@Transactional
//@EnableTransactionManagement
//@Scope(proxyMode = ScopedProxyMode.INTERFACES)
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
    Flight get(@PathVariable int pilotId) {

        List<Flight> flights = flightRepository.findAll();

        Flight openFlight = null;

        for (Flight tempFlight : flights) {
            if ((tempFlight.getPilot().getId() == pilotId) && tempFlight.getOpen() == true) {
                openFlight = tempFlight;
            }
        }
        if (openFlight != null) {
            return openFlight;
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No active flight found");
        }
    }


    @PostMapping("/create-flight/{pilotId}")
    Flight createFlight(@PathVariable int pilotId, @RequestBody Flight flight) {

        List<Flight> flights = flightRepository.findAll();

        boolean openFlightExists = false;

        for (Flight tempFlight : flights) {
            if ((tempFlight.getPilot().getId() == pilotId) && tempFlight.getOpen()) {
                openFlightExists = true;
            }
        }
        if (openFlightExists == false) {
            flightRepository.save(flight);
            return flight;
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is already an active flight");
        }
    }


    @PostMapping("/close-flight/{flightId}")
    void closeFlight(@PathVariable int flightId, @RequestBody Flight flight) {

        Flight closeFlight = flightRepository.findById(flightId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such flight exists"
        ));

        if (closeFlight.getOpen()) {
            closeFlight.setOpen(false);
            flightRepository.save(closeFlight);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight is already closed");
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
