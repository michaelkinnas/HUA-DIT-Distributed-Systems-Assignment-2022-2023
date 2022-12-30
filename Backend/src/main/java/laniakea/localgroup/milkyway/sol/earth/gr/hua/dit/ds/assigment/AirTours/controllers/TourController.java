package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.dao.TourDAOImpl;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Tour;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    @Autowired
    TourRepository tourRepository;

    @GetMapping("")
    List<Tour> getAll() {
        return tourRepository.findAll();
    }

    @GetMapping("/{id}")
    Tour get(@PathVariable int id) {
        Tour tour = tourRepository.findById(id);
        return tour;
    }

    @PostMapping("")
    Tour save(@RequestBody Tour tour) {
        tour.setId(0);
        tourRepository.save(tour);
        return tour;
    }

    @PutMapping("/{id}")
    Tour updateTour(@PathVariable int id, @RequestBody Tour tour) {
        Tour existingTour = tourRepository.findById(id);
        existingTour.setName(tour.getName());
        existingTour.setLocation(tour.getLocation());
        existingTour.setDuration(tour.getDuration());
        tourRepository.save(existingTour);
        return existingTour;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        tourRepository.deleteById(id);
    }




    // Owned by Repository
//    @Autowired
//    private TourDAOImpl tourDAOImpl;
//
//    // The '' goes after '/tours' because it's method level mapping.
//    @GetMapping("")
//    List<Tour> getTours() {
//        return tourDAOImpl.getAllTours();
//    }
//
//    @GetMapping("/{id}")
//    Tour get(@PathVariable int id) {
//        Tour tour = tourDAOImpl.findById(id);
//        return tour;
//    }
//
//    @PostMapping("")
//    Tour save(@RequestBody Tour tour) {
//        tour.setId(0);
//        tourDAOImpl.save(tour);
//        return tour;
//    }
//
//    @DeleteMapping("/{id}")
//    Tour delete(@PathVariable int id) {
//        Tour tour = tourDAOImpl.findById(id);
//        tourDAOImpl.delete(tour);
//        return tour;
//    }
}
