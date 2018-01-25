package at.htl.budgetcustodianapplication.facades.entities;

import android.arch.persistence.room.TypeConverter;
import android.provider.SyncStateContract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sasa on 12.01.2018.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }

}
