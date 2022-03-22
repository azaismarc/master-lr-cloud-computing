package models;

import play.db.jpa.Model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Rappel")
public class Rappel extends Model {
    private LocalDateTime date;
    @ManyToOne
    private Tache tache;
    private boolean done;

    public Rappel(LocalDateTime date, Tache tache) {
        this.date = date;
        this.tache = tache;
        this.done = false;
    }

    public boolean doNotify() {
        return !this.done && this.date.isBefore(LocalDateTime.now());
    }

    public void isDone() {
        this.done = true;
    }

    public String message() {
        return "Rappel: " + this.tache.getTitle();
    }
}
