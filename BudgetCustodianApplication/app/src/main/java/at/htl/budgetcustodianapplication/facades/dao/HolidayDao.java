package at.htl.budgetcustodianapplication.facades.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import at.htl.budgetcustodianapplication.facades.entities.Holiday;

/**
 * Created by Sasa on 12.01.2018.
 */

@Dao
public interface HolidayDao {

    //@Query("Select * from holiday where id == id")
    //Holiday getHoliday(long id);

    @Query("Select * from HOLIDAY")
    List<Holiday> getAllHolidays();

    @Query("select count(*) from holiday")
    int holidayCount();

    @Insert
    void insertAllHoliday(Holiday... holidays);

    @Insert
    void insertOneHoliday(Holiday holiday1);
}
