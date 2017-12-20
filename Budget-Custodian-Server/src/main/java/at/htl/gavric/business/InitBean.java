package at.htl.gavric.business;

import at.htl.gavric.entities.Holiday;
import at.htl.gavric.facades.HolidayDao;

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

    @PostConstruct
    private void save() {
        holidayDao.save(new Holiday(LocalDate.of(2017, 12, 1), LocalDate.of(2017,12,2),
                100, "holiday" ));
    }

}
