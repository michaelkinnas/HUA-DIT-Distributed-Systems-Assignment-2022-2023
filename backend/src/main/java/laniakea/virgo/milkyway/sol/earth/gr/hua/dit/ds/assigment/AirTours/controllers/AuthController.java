package laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.controllers;

import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.config.JwtUtils;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.ERole;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Role;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.payload.request.LoginRequest;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.payload.request.RegisterRequest;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.payload.response.JwtResponse;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.payload.response.MessageResponse;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.RoleRepository;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository.UserRepository;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authentication")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                (long) userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                roles,
                jwt));

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(registerRequest.getFirstName(),registerRequest.getLastName(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()));

        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "pilot":
                        Role modRole = roleRepository.findByName(ERole.ROLE_PILOT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}


