package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Aircraft;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Role;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Tour;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.AircraftRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.RoleRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.TourRepository;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AircraftRepository aircraftRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/aircraft")
    public List<Aircraft> getAllAircraft() {

        return aircraftRepository.findAll();
    }

    @PostMapping("/aircraft-add")
    public List<Aircraft> addAircraft(@RequestBody Aircraft aircraft) {

        aircraftRepository.save(aircraft);
        return aircraftRepository.findAll();
    }

    @PostMapping("/aircraft-remove")
    public List<Aircraft> removeAircraft(@RequestBody Aircraft aircraft) {
        
        aircraftRepository.deleteById(aircraft.getId());
        return aircraftRepository.findAll();
    }

    @GetMapping("/tours")
    public List<Tour> getAllTours() {

        return tourRepository.findAll();
    }

    @PostMapping("/tour-add")
    public List<Tour> addTour(@RequestBody Tour tour) {

        tourRepository.save(tour);
        return  tourRepository.findAll();
    }

    @PostMapping("/tour-remove")
    public List<Tour> removeTour(@RequestBody Tour tour) {

        tourRepository.deleteById(tour.getId());
        return  tourRepository.findAll();
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @PostMapping("/role-add/{userId}")
    public List<User> addRole(@PathVariable int userId, @RequestBody Role role) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such user exists"
        ));

        Role newRole = roleRepository.findById(role.getId()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such role exists"
        ));

        Set<Role> newRoles = user.getRoles();
        newRoles.add(newRole);
        user.setRoles(newRoles);
        userRepository.save(user);
        return userRepository.findAll();
    }

    @PostMapping("/role-remove/{userId}")
    public List<User> removeRole(@PathVariable int userId, @RequestBody Role role) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such user exists"
        ));;

        Role newRole = roleRepository.findById(role.getId()).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No such role exists"
        ));

        Set<Role> newRoles = user.getRoles();
        newRoles.remove(newRole);
        user.setRoles(newRoles);
        userRepository.save(user);
        return userRepository.findAll();
    }



}

