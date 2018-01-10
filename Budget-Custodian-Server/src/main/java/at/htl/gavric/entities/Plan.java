package at.htl.gavric.entities;

import javax.json.JsonObject;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "PLAN")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private int estimatedExpense;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Plan(){}

    public Plan(String text, int estimatedExpense, LocalDate fromDate, LocalDate toDate) {
        this.text = text;
        this.estimatedExpense = estimatedExpense;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getEstimatedExpense() {
        return estimatedExpense;
    }

    public void setEstimatedExpense(int estimatedExpense) {
        this.estimatedExpense = estimatedExpense;
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

    public static Plan fromJSON(JsonObject json) {

        Plan h = new Plan(json.getString("text"),
                json.getInt("estimatedExpense"),LocalDate.parse(json.getString("fromDate")),LocalDate.parse(json.getString("toDate")));
        if (json.containsKey("id"))
            h.setId(Long.valueOf(json.getInt("id")));
        return h;
    }
}
