package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestController manages JSONs
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    User get(@PathVariable int id) {
        return userRepository.findById(id);
    }

    @PostMapping("")
    User save(@RequestBody User user) {
        user.setId(0);
        userRepository.save(user);
        return user;
    }

    @PutMapping("/{id}")
    User updateUser(@PathVariable int id, @RequestBody User user) {
        User existingUser = userRepository.findById(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        userRepository.save(existingUser);
        return existingUser;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userRepository.deleteById(id);
    }

}
