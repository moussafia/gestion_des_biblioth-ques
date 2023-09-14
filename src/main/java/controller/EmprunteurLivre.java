package controller;

import ORM.orm;
import database.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmprunteurLivre {

    private int livre_id;
    private int Emprunteur_id;
    private Date date_emprunte;
    private Date date_retour;
    private int retard;

    private Collections collections;

    private Livres livre;

    private Emprunteur emprunteur;
    private Status status;

    private EmprunteurLivre emprunt_livre;


    public <T>List<EmprunteurLivre> AffichageLivreEmprunter() throws Exception{
        List<EmprunteurLivre> livresList=new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet response=null;
        db database=new db();
        Connection con=null;
        con=database.connect();
        String sql="SELECT c.nom_livre,c.auteur,c.ISBN,s.status,\n" +
                "e.nom_emprunteur,e.nemuro_emprunteur,el.date_emprunte,\n" +
                "el.date_retour,el.retard from collection c\n" +
                "join livre l on c.id=l.collection_id \n" +
                "join status s on l.satus_id=s.id \n" +
                "join emprunteur e\n" +
                "join emprunteur_livre el on el.emprunteur_id=e.id and l.id=el.livre_id\n" +
                "WHERE l.satus_id=2";
        ps=con.prepareStatement(sql);
        response=ps.executeQuery();
        while(response.next()){
            collections=new Collections();
            collections.setNomLivre(response.getString("nom_livre"));
            collections.setAuteur(response.getString("auteur"));
            collections.setISBN(response.getString("ISBN"));
            status=new Status();
            status.setStatus(response.getString("status"));
            emprunteur=new Emprunteur();
            emprunteur.setNumeroCondidat(response.getInt("nemuro_emprunteur"));
            emprunteur.setNom(response.getString("nom_emprunteur"));
            emprunt_livre=new EmprunteurLivre();
            emprunt_livre.setDate_emprunte(response.getDate("date_emprunte"));
            emprunt_livre.setDate_retour(response.getDate("date_retour"));
            emprunt_livre.setRetard(response.getInt("retard"));
            emprunt_livre.setCollections(collections);
            emprunt_livre.setStatus(status);
            emprunt_livre.setEmprunteur(emprunteur);
            livresList.add(emprunt_livre);
        }
        return livresList;
    }

    public <T> boolean RetournerLivre(int nCondidat) throws Exception{
            orm ormReturnLivre=new orm();
            int id_emprunteur=ormReturnLivre.find("emprunteur","nemuro_emprunteur",nCondidat);
            String sql="update emprunteur_livre el\n" +
                    "SET isRetourner=1\n" +
                    "where  el.emprunteur_id="+id_emprunteur+" AND el.date_emprunte=(SELECT (date_emprunte) " +
                    "from emprunteur_livre el \n" +
                    "where el.emprunteur_id="+id_emprunteur+" GROUP by el.date_emprunte DESC LIMIT 1)";
        System.out.println(sql);
            boolean response= ormReturnLivre.queryUpdate(sql);
            return response;

    }

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

    public Date getDate_emprunte() {
        return date_emprunte;
    }

    public void setDate_emprunte(Date date_emprunte) {
        this.date_emprunte = date_emprunte;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }

    public int getRetard() {
        return retard;
    }

    public void setRetard(int retard) {
        this.retard = retard;
    }

    public Livres getLivre() {
        return livre;
    }

    public void setLivre(Livres livre) {
        this.livre = livre;
    }

    public Emprunteur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public Collections getCollections() {
        return collections;
    }

    public void setCollections(Collections collections) {
        this.collections = collections;
    }

    public EmprunteurLivre getEmprunt_livre() {
        return emprunt_livre;
    }

    public void setEmprunt_livre(EmprunteurLivre emprunt_livre) {
        this.emprunt_livre = emprunt_livre;
    }
}
