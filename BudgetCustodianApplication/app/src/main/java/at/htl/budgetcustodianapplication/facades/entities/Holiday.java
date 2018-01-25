package at.htl.budgetcustodianapplication.facades.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by juliakajic on 07.01.18.
 */
@Entity(tableName = "holiday")
public class Holiday implements Serializable{

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String title;
    @TypeConverters({DateConverter.class})
    private Date dateFrom;
    @TypeConverters({DateConverter.class})
    private Date dateTo;
    private double budget;

    public Holiday(String title, Date dateFrom, Date dateTo, double budget) {
        this.id = id;
        this.title = title;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.budget = budget;
    }

    public Holiday() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    //region Getter and Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
    //endregion
}
