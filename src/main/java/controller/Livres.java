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

    public  <T> List<Status> statistiqueStatus() throws Exception {
        PreparedStatement ps=null;
        ResultSet response=null;
        db database=new db();
        Connection con=null;
        List<Status> countLivre=new ArrayList<>();
        con=database.connect();
        String sql="SELECT \tCount(*) as nombre_livre , s.status\n" +
                "FROM livre l\n" +
                "join status s on l.satus_id=s.id\n" +
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
        if (id>=1) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Invalid ISBN format");
        }
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        if (auteur.matches("^[A-Za-z ]+$")) {
            this.auteur = auteur;
        } else {
            throw new IllegalArgumentException("Invalid name format");
        }
    }

    public String getNomLivre() {

        return nomLivre;
    }

    public void setNomLivre(String nomLivre) {
        if (nomLivre.matches("^[A-Za-z]+$")) {
            this.nomLivre = nomLivre;
        } else {
            throw new IllegalArgumentException("Invalid name format");
        }
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        if (nomLivre.matches("^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$")) {
            this.ISBN = ISBN;
        } else {
            throw new IllegalArgumentException("Invalid ISBN format");
        }

    }

    public int getId_statut() {
        return id_statut;
    }

    public void setId_statut(int id_statut) {
        if (id_statut>=1) {
            this.id_statut = id_statut;
        } else {
            throw new IllegalArgumentException("Invalid ISBN format");
        }
        
    }

}

