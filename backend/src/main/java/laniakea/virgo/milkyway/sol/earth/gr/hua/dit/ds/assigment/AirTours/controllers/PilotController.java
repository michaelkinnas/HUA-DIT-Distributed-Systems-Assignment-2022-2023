package laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Aircraft;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Flight;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Tour;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.payload.response.MessageResponse;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.AircraftRepository;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.FlightRepository;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pilot")
public class PilotController {
    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @GetMapping("/active-flight/{pilotId}")
    public Flight getOpenFlight(@PathVariable int pilotId) {

        for (Flight flight : flightRepository.findAll()) {
            if (flight.getOpen() && flight.getPilot().getId() == pilotId) {
                return flight;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No active flight found");
    }


    @PostMapping("/create-flight/{pilotId}")
    public Flight createFlight(@PathVariable int pilotId, @RequestBody Flight flight) {

        for (Flight flightIt : flightRepository.findAll()) {
            if (flightIt.getOpen() && flightIt.getPilot().getId() == pilotId) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pilot already has active flight");
            }
        }
        int id = flightRepository.save(flight).getId();

        return flightRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such flight exists"
        ));
    }


    @PostMapping("/close-flight/{flightId}")
    public ResponseEntity<MessageResponse> closeFlight(@PathVariable int flightId) {

        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such flight exists"
        ));

        if (flight.getOpen()) {
            flight.setOpen(false);
            flightRepository.save(flight);
            return ResponseEntity.ok(new MessageResponse("Flight closed"));
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight is already closed");
    }

    @GetMapping("/aircraft")
    public List<Aircraft> getAllAircraft() {

        return aircraftRepository.findAll();
    }

    @GetMapping("/tours")
    public List<Tour> getAllTours() {

        return tourRepository.findAll();
    }

}
