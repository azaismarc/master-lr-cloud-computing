package models;

import play.db.jpa.Model;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
        return this.date.isBefore(LocalDateTime.now(ZoneId.of("Europe/Paris")));
    }

    public String message() {
        return "Rappel: " + this.tache.getTitle();
    }
}
