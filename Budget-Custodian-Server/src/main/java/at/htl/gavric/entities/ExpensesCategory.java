package at.htl.gavric.entities;

import javax.json.JsonObject;
import javax.persistence.*;

@Entity
@Table(name = "EXPENSESCATEGORY")
public class ExpensesCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String icon;
    private String color;

    public ExpensesCategory() {
    }

    public ExpensesCategory(String category, String icon, String color) {
        this.category = category;
        this.icon = icon;
        this.color = color;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static ExpensesCategory fromJSON(JsonObject json) {

        ExpensesCategory h = new ExpensesCategory(json.getString("category"), json.getString("icon"),
                json.getString("color"));
        if (json.containsKey("id"))
            h.setId(Long.valueOf(json.getInt("id")));
        return h;
    }
}
