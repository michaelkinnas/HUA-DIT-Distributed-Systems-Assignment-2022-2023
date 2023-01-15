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
import org.springframework.web.bind.annotation.*;

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
    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    @PutMapping("/aircraft-add")
    public Aircraft addAircraft(@RequestBody Aircraft aircraft) {
        aircraftRepository.save(aircraft);
        return aircraft;
    }

    @PutMapping("/aircraft-remove")
    public Aircraft removeAircraft(@RequestBody Aircraft aircraft) {
        aircraftRepository.deleteById(aircraft.getId());
        return  aircraft;
    }

    @GetMapping("/tours")
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @PostMapping("/tour-add")
    public Tour addTour(@RequestBody Tour tour) {
        tourRepository.save(tour);
        return  tour;
    }

    @PostMapping("/tour-remove")
    public Tour removeTour(@RequestBody Tour tour) {
        tourRepository.deleteById(tour.getId());
        return tour;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/role-add/{userId}")
    public List<User> addRole(@PathVariable Long userId, @RequestBody User user) {

        Set<Role> roles = user.getRoles();
        User tempUser = userRepository.findById(userId);
        tempUser.setRoles(roles);
        userRepository.save(tempUser);
        return userRepository.findAll();
    }

    @PostMapping("/role-remove/{userId}")
    public List<User> removeRole(@PathVariable Long userId, @RequestBody User user) {

        Set<Role> roles = user.getRoles();
        User tempUser = userRepository.findById(userId);
        tempUser.setRoles(roles);
        userRepository.save(tempUser);
        return userRepository.findAll();
    }



}

