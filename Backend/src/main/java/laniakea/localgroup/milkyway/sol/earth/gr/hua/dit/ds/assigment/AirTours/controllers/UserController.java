package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Flight;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Tour;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.FlightRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.TourRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //RETURN ALL TOURS
    @GetMapping("flights")
     List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    //USER REGISTER TOUR SEAT
    @PostMapping("flight-register/{flightId}")
     Flight registerFlight(@PathVariable Long id, @RequestBody User user) throws Exception {

        Long userId = user.getId();//   find  user id
        User passenger = userRepository.findById(userId);//    get passenger object

        int flightId = flightRepository.findFlightById(id).getId();//        get flight id
        Flight flight = flightRepository.findFlightById((long) flightId);//     get flight

        List<User> passengers = flight.getPassengers();

        boolean passengerHasNoFlight = true;
        List<Flight> flights = flightRepository.findAll();
        for (Flight tempFlight : flights) {

            if ((tempFlight.getPassengers().contains(passenger)) && (tempFlight.getOpen() == true)) {
                passengerHasNoFlight = false;
            }
        }

        if ((flight.getPassengers().size() < flight.getFlightAircraft().getNoSeats()) && (passengerHasNoFlight == true)
        && (Collections.frequency(flight.getPassengers(), passenger) != 0)) {
            passengers.add(passenger);
            flight.setPassengers(passengers);
            flightRepository.save(flight);
            return flight;
        }else {
            return null;
        }
    }

    @PostMapping("flight-unregister/{flightId}")
    Flight unregisterFlight(@PathVariable Long id, @RequestBody User user) {

        Long userId = user.getId();//     user id
        User passenger = userRepository.findById(userId);// passenger

        int flightId = flightRepository.findFlightById(id).getId();//        get flight id
        Flight flight = flightRepository.findFlightById((long) flightId);//     get flight

        List<User> passengers = flight.getPassengers();

        if ((flight.getPassengers().contains(passenger)) && (flight.getOpen() == true)) {
            passengers.remove(passenger);
            flight.setPassengers(passengers);
            flightRepository.save(flight);
            return flight;
        } else {
            return null;
        }
    }
}