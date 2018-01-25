package at.htl.budgetcustodianapplication.facades;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import at.htl.budgetcustodianapplication.facades.dao.ExpensesCategoryDao;
import at.htl.budgetcustodianapplication.facades.dao.HolidayDao;
import at.htl.budgetcustodianapplication.facades.entities.DateConverter;
import at.htl.budgetcustodianapplication.facades.entities.ExpensesCategory;
import at.htl.budgetcustodianapplication.facades.entities.ExpensesValue;
import at.htl.budgetcustodianapplication.facades.entities.Holiday;

/**
 * Created by Sasa on 12.01.2018.
 */

@Database(entities = {Holiday.class, ExpensesCategory.class, ExpensesValue.class},version = 2)
public abstract class ApplicationDatabase extends RoomDatabase {
    public abstract HolidayDao holidayDao();
    public abstract ExpensesCategoryDao expensesCategoryDao();

    private static final Object lock = new Object();
    private static ApplicationDatabase INSTANCE;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };

    public static ApplicationDatabase getInstance(Context context){
        synchronized (lock){
            if(INSTANCE ==null){
                INSTANCE= Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, "budgetDb")
                        .allowMainThreadQueries()
                        .addMigrations(MIGRATION_1_2)
                        .build();
            }
            return INSTANCE;
        }
    }
}
