package at.htl.budgetcustodianapplication.facades.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Sasa on 12.01.2018.
 */
@Entity(tableName = "expensesvalue")
public class ExpensesValue {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String name;
    private int value;

    public ExpensesValue() {
    }

    public ExpensesValue(Long id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
