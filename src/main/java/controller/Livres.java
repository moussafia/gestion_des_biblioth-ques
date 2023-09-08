package controller;

import ORM.orm;

import java.sql.ResultSet;
import java.util.*;

public class Livres {
    private int id;
    private String auteur;
    private String nomLivre;
    private int ISBN;
    private int id_statut;
    public int getId() {
        return id;
    }

    public int setId(int id) {
        return id=id;
    }
    public int getId_statut() {
        return id_statut;
    }
    public int SetId_statut(int id_statut) {
        return id_statut=id_statut;
    }

    public int getISBN() {
        return ISBN;
    }
    public int setISBN(int ISBN) {
        return ISBN=ISBN;
    }

    public String getAuteur() {
        return auteur;
    }
    public String setAuteur(String auteur) {
        return auteur=auteur;
    }
    public String getNomLivre() {
        return nomLivre;
    }

    public String setNomLivre(String nomLivre) {
        return nomLivre=nomLivre;
    }

    public static Livres AjouterLivre(Livres livre) throws Exception {
        orm insertLivre=new orm("livre");
        Map<String, String> dataLivre=new HashMap<>();
        dataLivre.put("nom_livre",livre.getNomLivre());
        dataLivre.put("auteur",livre.getAuteur());
        dataLivre.put("id_status",String.valueOf(livre.getId_statut()));
        dataLivre.put("ISBN",String.valueOf(livre.getISBN()));
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
        updaterLivre.WHERE("id","=",String.valueOf(livre.getId()));
        System.out.println(livre);
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

}

