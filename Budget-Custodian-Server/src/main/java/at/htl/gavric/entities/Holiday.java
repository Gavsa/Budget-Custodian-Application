package at.htl.gavric.entities;

import javax.json.JsonObject;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "HOLIDAY")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int budget;
    private String title;


    public Holiday() {
    }

    public Holiday(LocalDate fromDate, LocalDate toDate, int budget, String title) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.budget = budget;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Holiday fromJSON(JsonObject json) {

        Holiday h = new Holiday(LocalDate.parse(json.getString("fromDate")), LocalDate.parse(json.getString("toDate")),
                json.getInt("budget"), json.getString("title"));
        if (json.containsKey("id"))
            h.setId(Long.valueOf(json.getInt("id")));
        return h;
    }
}
