package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.dao;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Aircraft;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

// For class level methods that communicate with the db
@Repository
public class AircraftDAOImpl implements AircraftDAO {

    @Autowired
    private EntityManager entityManager;

    // for method level, to begin and commit a transaction automatically
    @Override
    @Transactional
    public List<Aircraft> getAllAircrafts() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Aircraft", Aircraft.class);
        List<Aircraft> aircrafts = query.getResultList();
        return aircrafts;
    }

    @Override
    @Transactional
    public void save(Aircraft aircraft) {
        Aircraft anaircraft = entityManager.merge(aircraft);
    }

    @Override
    @Transactional
    public Aircraft findById(int id) {
        return entityManager.find(Aircraft.class, id);
    }

    @Override
    @Transactional
    public void delete(Aircraft aircraft) {
        entityManager.remove(aircraft);

    }
}
