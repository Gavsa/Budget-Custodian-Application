package at.htl.gavric.facades;

import at.htl.gavric.entities.Holiday;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class HolidayDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save (Holiday holiday){
        entityManager.persist(holiday);
    }

    public List<Holiday> getAll() {
        return entityManager.createQuery("select h from Holiday h").getResultList();
    }

    public Holiday getById(Long id) {
        List<Holiday> tmp = entityManager.createQuery("select h from Holiday h where h.id = :ID")
                .setParameter("ID", id).getResultList();

        if (tmp.size() <= 0 || tmp.size() > 1)
            return null;
        return tmp.get(0);
    }

    public void update(Holiday holiday) {
        entityManager.merge(holiday);
    }

    public void delete(Holiday holiday) {
        entityManager.remove(entityManager.contains(holiday) ? holiday : entityManager.merge(holiday));
    }
}
