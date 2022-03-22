package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Tache")
public class Tache extends Model {
	private String title;
    private boolean done;

    public Tache(String title) {
        super();
        this.title = title;
        this.done = false;
    }

    public String getTitle() {
        return this.title;
    }

    public Tache setTitle(String title) {
        this.title = title;
        return this;
    }

    public boolean getDone() {
        return this.done;
    }

    public void changeStatut() {
        this.done = !this.done;
    }
}
