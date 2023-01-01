package laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.dao;

import laniakea.localgroup.milkyway.sol.earth.gr.hua.dit.ds.assigment.AirTours.entities.Tour;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

// For class level methods that communicate with the db
@Repository
public class TourDAOImpl implements TourDAO {
    @Autowired
    private EntityManager entityManager;

    // for method level, to begin and commit a transaction automatically
    @Override
    @Transactional
    public List<Tour> getAllTours() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Tour", Tour.class);
        List<Tour> tours = query.getResultList();
        return tours;
    }

    @Override
    @Transactional
    public void save(Tour tour) {
        Tour atour = entityManager.merge(tour);
    }

    @Override
    @Transactional
    public Tour findById(int id) {
        return entityManager.find(Tour.class, id);
    }

    @Override
    @Transactional
    public void delete(Tour tour) {
        entityManager.remove(tour);
    }
}
