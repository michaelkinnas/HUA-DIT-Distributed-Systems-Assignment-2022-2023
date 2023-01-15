package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Flight;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Tour;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.FlightRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.TourRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //RETURN ALL TOURS
    @GetMapping("tours")
    public List<Tour> getAll() {
        return tourRepository.findAll();
    }

    //USER REGISTER TOUR SEAT
    @PostMapping("flight-register/{flightId}")
    public List<Flight> registerFlight(@PathVariable Long id, @RequestBody User user) {
        Long userId = user.getId();//get id from user json
        int flightId = flightRepository.findFlightById(id).getId();//get flight id from path v.
        User searchUser = userRepository.findById(userId);//find by id from user table
        Flight searchFlight = flightRepository.findFlightById((long) flightId);//find by id flight table
        searchFlight.passengers.add(searchUser);//flight1.passenger.add(user1)
        flightRepository.save(searchFlight);//rep.saveflight(flight1)
        return flightRepository.findAll();
    }

    @PostMapping("flight-unregister/{flightId}")
    public List<Flight> unregisterFlight(@PathVariable Long id, @RequestBody User user) {
        Long userId = user.getId();//get id from user json
        int flightId = flightRepository.findFlightById(id).getId();//get flight id from path v.
        User searchUser = userRepository.findById(userId);//find by id from user table
        Flight searchFlight = flightRepository.findFlightById((long) flightId);//find by id flight table
        searchFlight.passengers.remove(searchUser);
        flightRepository.save(searchFlight);
        return flightRepository.findAll();
    }


}