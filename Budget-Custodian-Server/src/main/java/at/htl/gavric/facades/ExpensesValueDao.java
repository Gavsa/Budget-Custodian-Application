package at.htl.gavric.facades;

import at.htl.gavric.entities.ExpensesValue;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ExpensesValueDao {

    @PersistenceContext
    EntityManager entityManager;

    public void save (ExpensesValue expensesValue){
        entityManager.persist(expensesValue);
    }

    public List<ExpensesValue> getAll() {
        return entityManager.createQuery("select h from ExpensesValue h").getResultList();
    }

    public ExpensesValue getById(Long id) {
        List<ExpensesValue> tmp = entityManager.createQuery("select h from ExpensesValue h where h.id = :ID")
                .setParameter("ID", id).getResultList();

        if (tmp.size() <= 0 || tmp.size() > 1)
            return null;
        return tmp.get(0);
    }

    public void update(ExpensesValue expensesValue) {
        entityManager.merge(expensesValue);
    }

    public void delete(ExpensesValue expensesValue) {
        entityManager.remove(entityManager.contains(expensesValue) ? expensesValue : entityManager.merge(expensesValue));
    }
}