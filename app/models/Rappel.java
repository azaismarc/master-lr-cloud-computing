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

    public Rappel(LocalDateTime date, Tache tache) {
        this.date = date;
        this.tache = tache;
    }

    public boolean doNotify() {
        System.out.println(LocalDateTime.now());
        return this.date.isBefore(LocalDateTime.now());
    }

    public String message() {
        return "Rappel: " + this.tache.getTitle();
    }
}
