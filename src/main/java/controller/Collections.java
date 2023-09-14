package controller;

import ORM.orm;
import database.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Collections {

    private int id;
    private String auteur;
    private String nomLivre;
    private  String ISBN;

    private int quantity;


    public Collections AjouterCollection(Collections collection) throws Exception {
        orm insertCollections=new orm("collection");
        Map<Object, Object> dataCollection=new HashMap<>();
        if(collection.getNomLivre()!=null &&
            collection.getAuteur()!=null &&
           collection.getISBN()!=null &&
          collection.getQuantity()>=1){
            dataCollection.put("nom_livre",collection.getNomLivre());
            dataCollection.put("auteur",collection.getAuteur());
            dataCollection.put("ISBN",collection.getISBN());
            dataCollection.put("quantity",collection.getQuantity());
            insertCollections.save(dataCollection);
        }
        return collection;
    }
    public List<Collections> AffichageLivreDispo() throws Exception{
        List<Collections> livresList=new ArrayList<>();
        String sqlAfficheDispo="SELECT c.nom_livre,c.ISBN,c.auteur,count(*) as quantity\n" +
                " FROM `collection` c Join livre l on l.collection_id=c.id where  l.satus_id=1 GROUP by c.ISBN";
        db database=new db();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet dataResponse=null;
        con=database.connect();
        ps=con.prepareStatement(sqlAfficheDispo);
        dataResponse=ps.executeQuery();
        while(dataResponse.next()){
            Collections livre=new Collections();
            livre.setNomLivre(dataResponse.getString("nom_livre"));
            livre.setAuteur(dataResponse.getString("auteur"));
            livre.setISBN(dataResponse.getString("ISBN"));
            livre.setQuantity(dataResponse.getInt("quantity"));
            livresList.add(livre);
        }
        return livresList;
    }

    public Collections UpdateCollection(Collections collection) throws Exception {
        orm updateCollection=new orm("collection");
        Map<Object, Object> dataCollection=new HashMap<>();
        if(collection.getNomLivre()!=null &&
                collection.getAuteur()!=null &&
                collection.getISBN()!=null &&
                collection.getQuantity()>=1) {
            dataCollection.put("nom_livre", collection.getNomLivre());
            dataCollection.put("auteur", collection.getAuteur());
            dataCollection.put("ISBN", collection.getISBN());
            dataCollection.put("quantity", collection.getQuantity());
            updateCollection.WHERE("id", "=", String.valueOf(collection.getId())).update(dataCollection);
        }
            return collection;
    }

    public <T> List<Collections> rechercheLivreDispo(String key,T recherche) throws Exception{
        List<Collections> LivresList=new ArrayList<>();
        String sqlRecherche="SELECT c.nom_livre,c.ISBN,c.auteur,count(*) as quantity\n" +
                " FROM `collection` c Join livre l on l.collection_id=c.id " +
                "where c."+key+"='"+recherche+"' AND l.satus_id=1 GROUP by c."+key;
        db database=new db();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet dataLivres=null;
        con=database.connect();
        ps=con.prepareStatement(sqlRecherche);
        dataLivres=ps.executeQuery();
        while (dataLivres.next()){
            Collections collections=new Collections();
            collections.setNomLivre(dataLivres.getString("nom_livre"));
            collections.setAuteur(dataLivres.getString("auteur"));
            collections.setISBN(dataLivres.getString("ISBN"));
            collections.setQuantity(dataLivres.getInt("quantity"));
            LivresList.add(collections);
        }
        return LivresList;

    }

    public <T> int find(String key, T condition) throws Exception{
        orm ormFind=new orm();
        int response=ormFind.find("collection",key,condition);
        return response;

    }

    public boolean deleteCollection(int id) throws Exception {
        orm deleteLivre=new orm("collection");
        try {
            deleteLivre.WHERE("id","=",id).delete();
        }catch (Exception e){
            System.out.println("il y a une error durrant suppression \n"+e);
        }
        return true;
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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantiry) {
        if(quantiry>0){
            this.quantity = quantiry;
        }else {
            System.out.println("invalid data");
        }
    }


}
