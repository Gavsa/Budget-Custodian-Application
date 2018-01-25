package at.htl.budgetcustodianapplication.facades.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Sasa on 12.01.2018.
 */
@Entity(tableName = "expensescategory")
public class ExpensesCategory implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String category;

    public ExpensesCategory() {
    }

    public ExpensesCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
