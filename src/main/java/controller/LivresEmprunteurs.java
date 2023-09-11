package controller;

import java.time.format.DateTimeFormatter;

public class LivresEmprunteurs {
    private int livre_id;
    private int Emprunteur_id;
    private DateTimeFormatter date_emprunte;
    private DateTimeFormatter date_retour;
    private int retard_time;

    public int getLivre_id() {
        return livre_id;
    }

    public void setLivre_id(int livre_id) {
        this.livre_id = livre_id;
    }

    public int getEmprunteur_id() {
        return Emprunteur_id;
    }

    public void setEmprunteur_id(int emprunteur_id) {
        Emprunteur_id = emprunteur_id;
    }

    public DateTimeFormatter getDate_emprunte() {
        return date_emprunte;
    }

    public void setDate_emprunte(DateTimeFormatter date_emprunte) {
        this.date_emprunte = date_emprunte;
    }

    public DateTimeFormatter getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(DateTimeFormatter date_retour) {
        this.date_retour = date_retour;
    }

    public int getRetard_time() {
        return retard_time;
    }

    public void setRetard_time(int retard_time) {
        this.retard_time = retard_time;
    }
}
