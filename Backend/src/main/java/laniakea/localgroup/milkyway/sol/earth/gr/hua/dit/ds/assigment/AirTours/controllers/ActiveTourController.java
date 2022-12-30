package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.ActiveTour;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.ActiveTourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/active_tours")
public class ActiveTourController {

    @Autowired
    ActiveTourRepository activeTourRepository;

    @GetMapping("")
    List<ActiveTour> getAll() {
        return activeTourRepository.findAll();
    }

    @GetMapping("/{id}")
    ActiveTour get(@PathVariable int id) {
        return activeTourRepository.findById(id);
    }

    @PostMapping("")
    ActiveTour save(@RequestBody ActiveTour activeTour) {
        activeTour.setId(0);
        activeTourRepository.save(activeTour);
        return activeTour;
    }

    @PutMapping("/{id}")
    ActiveTour updateActiveTour(@PathVariable int id, @RequestBody ActiveTour activeTour) {
        ActiveTour existingActiveTour = activeTourRepository.findById(id);
        existingActiveTour.setName(activeTour.getName());
        activeTourRepository.save(existingActiveTour);
        return existingActiveTour;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        activeTourRepository.deleteById(id);
    }
}


