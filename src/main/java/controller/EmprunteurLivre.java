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

    private Livres livre;

    private Emprunteur emprunteur;
    private EmprunteurLivre emprunteur_livre;


    public <T>List<EmprunteurLivre> AffichageLivreEmprunter(String column,T condition) throws Exception{
        List<EmprunteurLivre> livresList=new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet response=null;
        db database=new db();
        Connection con=null;
        con=database.connect();
        String sql="SELECT l.nom_livre,l.auteur,l.ISBN,e.nom_emprunteur,\n" +
                "e.numero_condidat,el.date_emprunte,el.date_retour,el.retard\n" +
                " FROM livre l\n" +
                "join emprunteur_livre el on l.id=el.livre_id\n" +
                "JOIN emprunteur e on e.id=el.emprunteur_id\n" +
                "WHERE "+column+"='"+condition+"'";
        ps=con.prepareStatement(sql);
        response=ps.executeQuery();

        while(response.next()){
            livre=new Livres();
            livre.setNomLivre(response.getString("nom_livre"));
            livre.setAuteur(response.getString("auteur"));
            livre.setISBN(response.getInt("ISBN"));
            emprunteur=new Emprunteur();
            emprunteur.setNumeroCondidat(response.getInt("numero_condidat"));
            emprunteur.setNom(response.getString("nom_emprunteur"));
            emprunteur_livre=new EmprunteurLivre();
            emprunteur_livre.setDate_emprunte(response.getDate("date_emprunte"));
            emprunteur_livre.setDate_retour(response.getDate("date_retour"));
            emprunteur_livre.setRetard(response.getInt("retard"));
            emprunteur_livre.setLivre(livre);
            emprunteur_livre.setEmprunteur(emprunteur);
            livresList.add(emprunteur_livre);
        }
        return livresList;
    }

    public <T>void RetournerLivre(int nCondidat,int ISBN) throws Exception{
            List<EmprunteurLivre> livresList=new ArrayList<>();
            PreparedStatement ps=null;
            ResultSet response=null;
            db database=new db();
            Connection con=null;
            con=db.connect();
            String sqlLivre="SELECT  e.id,l.id\n" +
                    "from emprunteur e \n" +
                    "join emprunteur_livre el ON el.emprunteur_id=e.id\n" +
                    "join livre l on l.id=el.livre_id\n" +
                    "where l.ISBN='"+ISBN+"' and e.numero_condidat='"+nCondidat+"'";
            ps=con.prepareStatement(sqlLivre);
            response= ps.executeQuery();
            if(response.next()){
                System.out.println(response.getInt("e.id"));
                orm ormDeleteEmprunteur=new orm("emprunteur");
                ormDeleteEmprunteur.WHERE("id","=",response.getInt("e.id")).delete();
                System.out.println("deleted succesfully");
            }else{
                System.out.println("vous etes pas emprunter un livre");
            }

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
    public EmprunteurLivre getEmprunteur_livre() {
        return emprunteur_livre;
    }

    public void setEmprunteur_livre(EmprunteurLivre emprunteur_livre) {
        this.emprunteur_livre = emprunteur_livre;
    }

}
