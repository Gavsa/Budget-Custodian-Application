package at.htl.budgetcustodianapplication.facades.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import at.htl.budgetcustodianapplication.facades.entities.ExpensesCategory;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;

/**
 * Created by Sasa on 17.01.2018.
 */
@Dao
public interface ExpensesCategoryDao {

    //@Query("Select * from expensescategory where id == id")
    //Holiday getExpensesCategory(long id);

    @Query("Select * from expensescategory")
    List<ExpensesCategory> getAllExpensesCategories();

    @Query("select count(*) from expensescategory")
    int expensesCategoriesCount();

    @Insert
    void insertAllExpensesCategories(ExpensesCategory... expensesCategories);

    @Insert
    void insertOneExpenseCategory(ExpensesCategory expensesCategory1);

}
