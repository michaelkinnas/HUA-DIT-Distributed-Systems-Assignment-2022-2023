package laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository;

import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.ERole;
import laniakea.virgo.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer > {
    Optional<Role> findByName(ERole name);


}
