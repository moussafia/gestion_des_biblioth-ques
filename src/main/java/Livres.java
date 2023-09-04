public class Livres {
    private int id;
    private String auteur;
    private String nomLivre;
    private long ISBN;
    private int id_statut;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_statut() {
        return id_statut;
    }
    public void setId_statut(int id_statut) {
        this.id_statut = id_statut;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getNomLivre() {
        return nomLivre;
    }

    public void setNomLivre(String nomLivre) {
        this.nomLivre = nomLivre;
    }
}
