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
}
