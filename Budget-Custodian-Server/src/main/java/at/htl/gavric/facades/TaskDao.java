package at.htl.gavric.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import at.htl.gavric.entities.Task;


@Stateless
public class TaskDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save (Task task){
        entityManager.persist(task);
    }

    public List<Task> getAll() {
        return entityManager.createQuery("select h from Task h").getResultList();
    }

    public Task getById(Long id) {
        List<Task> tmp = entityManager.createQuery("select h from Task h where h.id = :ID")
                .setParameter("ID", id).getResultList();

        if (tmp.size() <= 0 || tmp.size() > 1)
            return null;
        return tmp.get(0);
    }

    public void update(Task task) {
        entityManager.merge(task);
    }

    public void delete(Task task) {
        entityManager.remove(entityManager.contains(task) ? task : entityManager.merge(task));
    }
}
