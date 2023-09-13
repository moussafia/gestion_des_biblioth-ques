package controller;

import ORM.orm;
import database.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;
public class Emprunteur {
        private int id;
        private String nom;
        private int numeroCondidat;

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getNom() {
                return nom;
        }

        public void setNom(String nom) {
                this.nom = nom;
        }

        public int getNumeroCondidat() {
                return numeroCondidat;
        }

        public void setNumeroCondidat(int numeroCondidat) {
                this.numeroCondidat = numeroCondidat;
        }

        public <T>void EmpruntLivre(Emprunteur emprunte,T ISBN) throws Exception {
            orm ormLivre=new orm("livre");
            orm ormEmprunteur=new orm("emprunteur");
            orm ormEL=new orm("emprunteur_livre");
            Map<String, String> dataEmprunteur=new HashMap<>();
            dataEmprunteur.put("numero_condidat",String.valueOf(emprunte.getNumeroCondidat()));
            dataEmprunteur.put("nom_emprunteur",emprunte.getNom());
            ResultSet generatedKeyIDEmprunteur=ormEmprunteur.save(dataEmprunteur);
            generatedKeyIDEmprunteur.next();
            int emprunteur_id=generatedKeyIDEmprunteur.getInt(1);
            ResultSet generatedKeyIDLivre=ormLivre.WHERE("ISBN","=",ISBN)
                    .WHERE("status_id","=",1).first();
            generatedKeyIDLivre.next();
            int livre_id = generatedKeyIDLivre.getInt("id");
            Map<String, String> dataEmpruntLivre=new HashMap<>();
            dataEmpruntLivre.put("emprunteur_id ",String.valueOf(emprunteur_id));
            dataEmpruntLivre.put("livre_id",String.valueOf(livre_id));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date();
            String formattedDateNow = dateFormat.format(currentDate);
            dataEmpruntLivre.put("date_emprunte", formattedDateNow);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 40);
            Date updatedDate = calendar.getTime();
            String formattedDateRetour = dateFormat.format(updatedDate);
            System.out.println("Updated date: " + formattedDateRetour);
            dataEmpruntLivre.put("date_retour",formattedDateRetour);
            dataEmpruntLivre.put("retard","0");
            ormEL.save(dataEmpruntLivre);

        }

}
