package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.dao;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers();

    public void save(User user);

    public User findById(int id);

    void delete(User user);
}
