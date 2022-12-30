package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.dao.AircraftDAO;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.dao.AircraftDAOImpl;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Aircraft;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {

    @Autowired
    AircraftRepository aircraftRepository;

    @GetMapping("")
    List<Aircraft> getAll() {
        return aircraftRepository.findAll();
    }

    @GetMapping("/{id}")
    Aircraft get(@PathVariable int id) {
        Aircraft aircraft = aircraftRepository.findById(id);
        return aircraft;
    }

    @PostMapping("")
    Aircraft save(@RequestBody Aircraft aircraft) {
        aircraft.setId(0);
        aircraftRepository.save(aircraft);
        return aircraft;
    }

    @PutMapping("/{id}")
    Aircraft updateAircraft(@PathVariable int id, @RequestBody Aircraft aircraft) {
        Aircraft existingAircraft = aircraftRepository.findById(id);
        existingAircraft.setType(aircraft.getType());
        existingAircraft.setNoSeats(aircraft.getNoSeats());
        existingAircraft.setRegistration(aircraft.getRegistration());
        aircraftRepository.save(existingAircraft);
        return existingAircraft;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        aircraftRepository.deleteById(id);
    }


    // Owned by Repository
//    @Autowired
//    private AircraftDAOImpl aircraftDAOImpl;
//
//    // The '' goes after '/aircraft' because it's method level mapping.
//    @GetMapping("")
//    List<Aircraft> getAircrafts() {
//        return aircraftDAOImpl.getAllAircrafts();
//    }
//
//    @GetMapping("/{id}")
//    Aircraft get(@PathVariable int id) {
//        Aircraft aircraft = aircraftDAOImpl.findById(id);
//        return aircraft;
//    }
//
//    @PostMapping("")
//    Aircraft save(@RequestBody Aircraft aircraft) {
//        aircraft.setId(0);
//        aircraftDAOImpl.save(aircraft);
//        return aircraft;
//    }
//
//    @DeleteMapping("/{id}")
//    Aircraft delete(@PathVariable int id) {
//        Aircraft aircraft = aircraftDAOImpl.findById(id);
//        aircraftDAOImpl.delete(aircraft);
//        return aircraft;
//    }
}