package at.htl.gavric.facades;

import at.htl.gavric.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save (User user){
        entityManager.persist(user);
    }

    public List<User> getAll() {
        return entityManager.createQuery("select h from User h").getResultList();
    }

    public User getById(Long id) {
        List<User> tmp = entityManager.createQuery("select h from User h where h.id = :ID")
                .setParameter("ID", id).getResultList();

        if (tmp.size() <= 0 || tmp.size() > 1)
            return null;
        return tmp.get(0);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }
}
