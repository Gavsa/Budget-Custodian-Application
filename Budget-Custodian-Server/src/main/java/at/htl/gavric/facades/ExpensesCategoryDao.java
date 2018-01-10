package at.htl.gavric.facades;

import at.htl.gavric.entities.ExpensesCategory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ExpensesCategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save (ExpensesCategory expensesCategory){
        entityManager.persist(expensesCategory);
    }

    public List<ExpensesCategory> getAll() {
        return entityManager.createQuery("select h from ExpensesCategory h").getResultList();
    }

    public ExpensesCategory getById(Long id) {
        List<ExpensesCategory> tmp = entityManager.createQuery("select h from ExpensesCategory h where h.id = :ID")
                .setParameter("ID", id).getResultList();

        if (tmp.size() <= 0 || tmp.size() > 1)
            return null;
        return tmp.get(0);
    }

    public void update(ExpensesCategory expensesCategory) {
        entityManager.merge(expensesCategory);
    }

    public void delete(ExpensesCategory expensesCategory) {
        entityManager.remove(entityManager.contains(expensesCategory) ? expensesCategory : entityManager.merge(expensesCategory));
    }


}
