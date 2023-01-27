package laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Flight;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.FlightRepository;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.TourRepository;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

// RestController manages JSONs
@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    FlightRepository flightRepository;


    @GetMapping("/flights")
    List<Flight> getAllOpenFlights() {

        ArrayList<Flight> openFlights = new ArrayList<>();
        for (Flight flight : flightRepository.findAll()) {
            if (flight.getOpen()) {
                openFlights.add(flight);
            }
        }

        if (openFlights.size() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No open flights available");
        }

        return openFlights;
    }

    //USER REGISTER TOUR SEAT
    @PostMapping("/flight-register/{flightId}")
    List<Flight> registerToFlight(@PathVariable int flightId, @RequestBody User user) {

        User passenger = userRepository.findById(user.getId()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such user exists"
        ));

        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such flight exists"
        ));

        List<User> passengers = flight.getPassengers();

        for (Flight flightIt : flightRepository.findAll()) {
            if (flightIt.getPassengers().contains(passenger) && flightIt.getOpen()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is already registered in a flight");
            }
        }

        if ((passengers.size() >= flight.getAircraft().getNumberOfSeats())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No empty seats");

        }
        passengers.add(passenger);
        flight.setPassengers(passengers);
        flightRepository.save(flight);
        return flightRepository.findAll();
    }

    @PostMapping("/flight-unregister/{flightId}")
    List<Flight> unregisterFlight(@PathVariable int flightId, @RequestBody User user) {

        User passenger = userRepository.findById(user.getId()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such user exists"
        ));

        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such flight exists"
        ));

        List<User> passengers = flight.getPassengers();

        if ((passengers.contains(passenger)) && (flight.getOpen())) {
            passengers.remove(passenger);
            flight.setPassengers(passengers);
            flightRepository.save(flight);

        }
        return flightRepository.findAll();
    }
}