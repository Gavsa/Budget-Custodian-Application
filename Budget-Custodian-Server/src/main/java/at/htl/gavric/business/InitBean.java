package at.htl.gavric.business;

import at.htl.gavric.entities.*;
import at.htl.gavric.facades.*;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.time.LocalDate;

@Startup
@Singleton
public class InitBean {
    @Inject
    HolidayDao holidayDao;

    @Inject
    ExpensesValueDao expensesValueDao;

    @Inject
    ExpensesCategoryDao expensesCategoryDao;

    @Inject
    PlanDao planDao;

    @Inject
    TaskDao taskDao;

    @Inject
    UserDao userDao;

    @PostConstruct
    private void save() {
        holidayDao.save(new Holiday(LocalDate.of(2017, 12, 1), LocalDate.of(2017,12,2),
                100, "holiday" ));
        expensesValueDao.save(new ExpensesValue("Test",12));
        expensesCategoryDao.save(new ExpensesCategory("ja ","neon","wieso"));
        planDao.save(new Plan("Dobar Plan",300,LocalDate.of(2018,1,8),LocalDate.of(2018,1,10)));
        taskDao.save(new Task("Reisepass",false));
        taskDao.save(new Task("Buskarten",false));
        taskDao.save(new Task("Geld",true));
        userDao.save(new User("Petar","Treskanje"));
    }



}
