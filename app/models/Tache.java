package models;

import play.db.jpa.Model;

import javax.persistence.*;

@Entity
@Table(name="Tache")
public class Tache extends Model {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int id;
	public String nom;
    public boolean estFait;

    public Tache(String nom) {
        super();
        this.nom = nom;
        this.estFait = false;
    }
}
