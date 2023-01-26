package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Flight;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Tour;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.FlightRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.TourRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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


    @GetMapping("flights")
    List<Flight> getAllFlights() {

        List<Flight> allFlights =  flightRepository.findAll();

        //List<Flight> openFlights = Collections.emptyList();
        ArrayList<Flight> openFlights = new ArrayList<>();

        for (Flight tempFlight : allFlights) {
            if (tempFlight.getOpen()) {
                openFlights.add(tempFlight);
            }
        }

        if (openFlights.size() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No open flights available");
        }else {
            return openFlights;
        }
    }

    //USER REGISTER TOUR SEAT
    @PostMapping("flight-register/{flightId}")
    List<Flight> registerFlight(@PathVariable int flightId, @RequestBody User user) {

        User passenger = userRepository.findById(user.getId()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such user exists"
        ));

        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such flight exists"
        ));

        List<User> passengers = flight.getPassengers();

        List<Flight> flights = flightRepository.findAll();

        for (Flight tempFlight : flights) {
            if ((tempFlight.getPassengers().contains(passenger)) && (tempFlight.getOpen())) {
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

    @PostMapping("flight-unregister/{flightId}")
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