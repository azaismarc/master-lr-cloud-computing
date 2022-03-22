package controllers;

import java.time.LocalDateTime;
import java.util.List;
import play.mvc.Controller;
import models.Rappel;
import models.Tache;

public class Application extends Controller {

    // Affiche toutes les tâches (voir variable taches) dans le template views/listTache.html
    public static void listTache() {
        List<Tache> taches = Tache.findAll();
		render(taches);
    }

    // Affiche le template views/ajouterTacheForm.html (formulaire d'ajout d'une tâche)
    public static void ajouterTacheForm() {
        render();
    }

    // Ajoute une nouvelle tâche en base de données et affiche le template views/ajouterTache.html
    public static void ajouterTache(String title) {
        Tache tache = new Tache(title);
        tache.save();
        render();
    }

    // Change le statut d'une tâche en base de données
    public static void validerTache(Long id) {
        Tache tache = Tache.findById(id);
        tache.changeStatut();
        tache.save();
    }

    // Supprime une tâche en base de données
    public static void supprimerTache(Long id) {
        Tache tache = Tache.findById(id);
        tache.delete();
        renderJSON(tache);
    }

    // Modifie une tâche en base de données
    public static void editTache(Long id, String title) {
        Tache tache = Tache.findById(id);
        tache.setTitle(title);
        tache.save();
        renderJSON(tache);
    }

    public static void ajouterRappelForm(Long id) {
        render();
    }

    public static void ajouterRappel(Long id, LocalDateTime date) {
        Tache tache = Tache.findById(id);
        Rappel rappel = new Rappel(date, tache);
        rappel.save();
        render();
    }
}