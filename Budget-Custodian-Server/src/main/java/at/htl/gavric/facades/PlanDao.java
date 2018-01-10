package at.htl.gavric.facades;

import at.htl.gavric.entities.Plan;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PlanDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save (Plan plan){
        entityManager.persist(plan);
    }

    public List<Plan> getAll() {
        return entityManager.createQuery("select h from Plan h").getResultList();
    }

    public Plan getById(Long id) {
        List<Plan> tmp = entityManager.createQuery("select h from Plan h where h.id = :ID")
                .setParameter("ID", id).getResultList();

        if (tmp.size() <= 0 || tmp.size() > 1)
            return null;
        return tmp.get(0);
    }

    public void update(Plan plan) {
        entityManager.merge(plan);
    }

    public void delete(Plan plan) {
        entityManager.remove(entityManager.contains(plan) ? plan : entityManager.merge(plan));
    }
}
