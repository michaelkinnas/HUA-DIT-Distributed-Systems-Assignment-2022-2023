package laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.DBTesting;

import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.*;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DBInit {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostConstruct
    private void postConstruct() {

        //Add roles (necessary)
        if (roleRepository.count() == 0) {

            Role admin = new Role();
            admin.setName(ERole.ROLE_ADMIN);

            Role pilot = new Role();
            pilot.setName(ERole.ROLE_PILOT);

            Role user = new Role();
            user.setName(ERole.ROLE_USER);

            roleRepository.save(admin);
            roleRepository.save(pilot);
            roleRepository.save(user);
        }

        //Add test users
        if (userRepository.count() == 0) {
            Role adminRole = roleRepository.findById(1).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No such role exists"
            ));
            Role pilotRole = roleRepository.findById(2).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No such role exists"
            ));
            Role userRole = roleRepository.findById(3).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No such role exists"
            ));


            //Admin
            User admin = new User();
            admin.setEmail("tom@gmail.com");
            admin.setFirstName("Tom");
            admin.setLastName("Araya");
            admin.setPassword(encoder.encode("pass123"));

            userRepository.save(admin);

            Set<Role> adminRoles = admin.getRoles();
            adminRoles.add(adminRole);
            adminRoles.add(userRole);
            admin.setRoles(adminRoles);

            userRepository.save(admin);

            //Pilot
            User pilot = new User();
            pilot.setEmail("james@gmail.com");
            pilot.setFirstName("James");
            pilot.setLastName("Hetfield");
            pilot.setPassword(encoder.encode("pass123"));

            userRepository.save(pilot);

            Set<Role> pilotRoles = pilot.getRoles();

            pilotRoles.add(userRole);
            pilotRoles.add(pilotRole);
            pilot.setRoles(pilotRoles);

            userRepository.save(pilot);

            //User
            User user = new User();
            user.setEmail("john@gmail.com");
            user.setFirstName("John");
            user.setLastName("Rambo");
            user.setPassword(encoder.encode("pass123"));

            userRepository.save(user);

            Set<Role> userRoles = user.getRoles();

            userRoles.add(userRole);
            user.setRoles(pilotRoles);

            userRepository.save(user);
        }

        //Aircraft
        if (aircraftRepository.count() == 0) {
            Aircraft plane = new Aircraft();
            plane.setRegistration("SX-JHG");
            plane.setType("Cessna Skyhawk 172");
            plane.setNumberOfSeats(3);

            aircraftRepository.save(plane);
        }

        //Tours
        if (tourRepository.count() == 0) {
            Tour tour = new Tour();
            tour.setDuration(2);
            tour.setDescription("Acropolis circle then over Athens");
            tour.setLocation("Athens");

            tourRepository.save(tour);
        }

        //Flight
        if (flightRepository.count() == 0) {
            Flight flight = new Flight();
            flight.setName("Acropolis from above!");
            flight.setAircraft(aircraftRepository.findById(4).orElseThrow());
//            flight.setOpen(true);
            flight.setPilot(userRepository.findById(2).orElseThrow());
            flight.setTour(tourRepository.findById(5).orElseThrow());
            List<User> passengers = new ArrayList<>();
            passengers.add(userRepository.findById(1).orElseThrow());
            passengers.add(userRepository.findById(3).orElseThrow());
            flight.setPassengers(passengers);
            flightRepository.save(flight);
        }

    }
}
