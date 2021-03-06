package at.htl.gavric.entities;

import javax.json.JsonObject;
import javax.persistence.*;

@Entity
@Table(name = "EXPENSESVALUE")
public class ExpensesValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int value;

    public ExpensesValue() {
    }

    public ExpensesValue(String name, int value) {
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

    public static ExpensesValue fromJSON(JsonObject json) {

        ExpensesValue h = new ExpensesValue(json.getString("name"),
                json.getInt("value"));
        if (json.containsKey("id"))
            h.setId(Long.valueOf(json.getInt("id")));
        return h;
    }
}
