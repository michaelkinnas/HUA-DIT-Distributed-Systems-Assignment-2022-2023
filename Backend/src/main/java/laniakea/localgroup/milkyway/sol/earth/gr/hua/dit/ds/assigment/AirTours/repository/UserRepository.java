package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.repository;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository includes generic code with abstract classes <entityClass, primaryKey>
// It builds automatically methods like findAll, findById, deleteById, save etc.
@Repository
public interface UserRepository extends JpaRepository <User, Integer> {
    User findById(int id);
}
