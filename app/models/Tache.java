package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Tache")
public class Tache extends Model {
	public String title;
    public boolean done;

    public Tache(String title) {
        super();
        this.title = title;
        this.done = false;
    }
}
