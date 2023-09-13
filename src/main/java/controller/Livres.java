package controller;

import ORM.orm;
import database.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Livres {
    private int id;
    private String auteur;
    private String nomLivre;
    private int ISBN;
    private int id_statut;


    public static Livres AjouterLivre(Livres livre) throws Exception {
        orm insertLivre=new orm("livre");
        Map<String, String> dataLivre=new HashMap<>();
        dataLivre.put("nom_livre",livre.getNomLivre());
        dataLivre.put("auteur",livre.getAuteur());
        dataLivre.put("ISBN",String.valueOf(livre.getISBN()));
        dataLivre.put("id_status",String.valueOf(1));
        insertLivre.save(dataLivre);
        System.out.println(livre);
        return livre;
    }

    public static Livres UpdateLivre(Livres livre) throws Exception {
        orm updaterLivre=new orm("livre");
        Map<String, String> dataLivre=new HashMap<>();
        dataLivre.put("nom_livre", livre.getNomLivre());
        dataLivre.put("auteur",livre.getAuteur());
        dataLivre.put("id_status",String.valueOf(livre.getId_statut()));
        dataLivre.put("ISBN",String.valueOf(livre.getISBN()));
        updaterLivre.WHERE("id","=",String.valueOf(livre.getId())).update(dataLivre);
        System.out.println(livre.id);
        return livre;
    }
    public List<Livres> AffichageLivreDispo() throws Exception{
        List<Livres> livresList=new ArrayList<>();
        orm afficheLivre=new orm("livre");
        ResultSet dataResponse = afficheLivre.orWHERE("id_status","=","1")
                .orWHERE("id_status","=","2").get();
        System.out.println(dataResponse);
        while(dataResponse.next()){
            Livres livre=new Livres();
            livre.id=dataResponse.getInt("id");
            livre.nomLivre=dataResponse.getString("nom_livre");
            livre.auteur=dataResponse.getString("auteur");
            livre.ISBN=dataResponse.getInt("ISBN");
            livresList.add(livre);
        }
        return livresList;
    }

    public static boolean deleteUneLivre(int id) throws Exception {
        orm deleteLivre=new orm("livre");
        try {
            deleteLivre.WHERE("id","=",String.valueOf(id)).delete();
        }catch (Exception e){
            System.out.println("il y a une error durrant suppression \n"+e);
        }
        return true;
    }

    public <T> List<Livres> rechercheISBN(T recherche) throws Exception{
        orm rechercheLivre=new orm("livre");
        List<Livres> LivresList=new ArrayList<>();
        ResultSet dataLivres= rechercheLivre.WHERE("ISBN","=",  recherche)
                .orWHERE("auteur","=",  recherche)
                .orWHERE("nom_livre","=",  recherche)
                .get();
        while (dataLivres.next()){
            Livres livre=new Livres();
            livre.id=dataLivres.getInt("id");
            livre.nomLivre=dataLivres.getString("nom_livre");
            livre.auteur=dataLivres.getString("auteur");
            livre.ISBN=dataLivres.getInt("ISBN");
            LivresList.add(livre);
        }
        return LivresList;

    }
    public  <T> List<Status> statistiqueStatus() throws Exception {
        PreparedStatement ps=null;
        ResultSet response=null;
        db database=new db();
        Connection con=null;
        List<Status> countLivre=new ArrayList<>();
        con=database.connect();
        String sql="SELECT \tCount(*) as nombre_livre , s.status\n" +
                "FROM livre l\n" +
                "join status s on l.status_id=s.id\n" +
                "GROUP  BY s.status;";
        ps=con.prepareStatement(sql);
        response=ps.executeQuery();
        while (response.next()){
            Status status=new Status();
            status.setStatus(response.getString("status"));
            status.setNombre_livre(response.getInt("nombre_livre"));
            countLivre.add(status);
        }
        return  countLivre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getId_statut() {
        return id_statut;
    }

    public void setId_statut(int id_statut) {
        this.id_statut = id_statut;
    }
}

