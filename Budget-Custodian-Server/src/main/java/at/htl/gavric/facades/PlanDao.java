package at.htl.gavric.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PlanDao {

    @PersistenceContext
    EntityManager entintyManager;
}
