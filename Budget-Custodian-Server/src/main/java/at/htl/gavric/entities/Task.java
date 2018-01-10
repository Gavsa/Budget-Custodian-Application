package at.htl.gavric.entities;

import javax.json.JsonObject;
import javax.persistence.*;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private boolean checked;

    public Task() {
    }

    public Task(String text, boolean checked) {
        this.text = text;
        this.checked = checked;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public static Task fromJSON(JsonObject json) {

        Task h = new Task(json.getString("text"),json.getBoolean(String.valueOf(false)));
        if (json.containsKey("id"))
            h.setId(Long.valueOf(json.getInt("id")));
        return h;
    }
}
