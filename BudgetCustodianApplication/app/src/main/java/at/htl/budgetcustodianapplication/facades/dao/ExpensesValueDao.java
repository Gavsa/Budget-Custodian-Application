package at.htl.budgetcustodianapplication.facades.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import at.htl.budgetcustodianapplication.facades.entities.ExpensesValue;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;

/**
 * Created by Sasa on 17.01.2018.
 */

@Dao
public interface ExpensesValueDao {
    //@Query("Select * from EXPENSESVALUE where id == id")
    //Holiday getExpansesValues(long id);

    @Query("Select * from expensesvalue")
    List<Holiday> getAllExpensesValue();

    @Query("select count(*) from expensesvalue")
    int expensesValueCount();

    @Insert
    void insertAllExpensesValues(ExpensesValue... expensesValues);

    @Insert
    void insertOneExpensesValue(ExpensesValue expensesValue1);
}
