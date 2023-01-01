package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.dao;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

// For class level methods that communicate with the db
// Repository is sublass of Component annotation
// @Repository is for the controller to be able to find it.
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private EntityManager entityManager;

    // for method level, to begin and commit a transaction automatically
    @Override
    @Transactional
    public List<User> getAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from User", User.class);
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    @Transactional
    public void save(User user) {
        User auser = entityManager.merge(user);
    }

    @Override
    @Transactional
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
    }
}
