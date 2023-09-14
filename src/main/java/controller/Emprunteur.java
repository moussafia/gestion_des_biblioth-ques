package controller;

import ORM.orm;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;
public class Emprunteur {
        private int id;
        private String nom;
        private int numeroCondidat;

        public <T>void EmpruntLivre(Emprunteur emprunte,T ISBN) throws Exception {
            orm ormEL = new orm("emprunteur_livre");
            Collections coll = new Collections();
            int collection_id = coll.find("ISBN", ISBN);
            int emprunteur_id = 0;
            if (collection_id > 0) {
            String sqlIdLivreDisponible="SELECT l.id from livre l\n" +
                        "join collection c on c.id="+collection_id+"\n" +
                        "WHERE l.satus_id=1 LIMIT 1";
                ResultSet ormIdLivre=orm.query(sqlIdLivreDisponible);
                if(ormIdLivre!=null){
                    int livre_id=ormIdLivre.getInt("id");
                    System.out.println(livre_id);
                    orm ormEmprunteur = new orm("emprunteur");
                    Map<Object, Object> dataEmprunteur = new HashMap<>();
                    dataEmprunteur.put("nemuro_emprunteur", emprunte.getNumeroCondidat());
                    dataEmprunteur.put("nom_emprunteur", emprunte.getNom());
                    ResultSet generatedKeyIDEmprunteur = ormEmprunteur.save(dataEmprunteur);
                    System.out.println("after save emprunteur");
                    if (generatedKeyIDEmprunteur.next()) {
                        emprunteur_id = generatedKeyIDEmprunteur.getInt(1);
                        System.out.println(emprunteur_id);
                        Map<Object, Object> dataEmpruntLivre = new HashMap<>();
                        dataEmpruntLivre.put("emprunteur_id", emprunteur_id);
                        dataEmpruntLivre.put("livre_id", String.valueOf(livre_id));
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date currentDate = new Date();
                        String formattedDateNow = dateFormat.format(currentDate);
                        dataEmpruntLivre.put("date_emprunte", formattedDateNow);
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DAY_OF_YEAR, 40);
                        Date updatedDate = calendar.getTime();
                        String formattedDateRetour = dateFormat.format(updatedDate);
                        dataEmpruntLivre.put("date_retour", formattedDateRetour);
                        dataEmpruntLivre.put("retard", 0);
                        ormEL.save(dataEmpruntLivre);
                    }
                }

            } else {
                System.out.println("ISBN n'existe pas");
            }

        }

        public static boolean CheckEmprunteur(int nCondidat) throws Exception {
            orm ormEmprunteur=new orm();
            int emprunteur_id=ormEmprunteur.find("emprunteur","nemuro_emprunteur",nCondidat);
            String sql="SELECT isRetourner as retourned FROM `emprunteur_livre` " +
                    "el WHERE el.emprunteur_id='"+emprunteur_id+"' ORDER BY el.isRetourner DESC  \n" +
                    "LIMIT 1";
            ResultSet dataIsRetourned=ormEmprunteur.query(sql);
            if(dataIsRetourned!=null){
                boolean isRetourned=dataIsRetourned.getBoolean(1)? true : false ;
                return isRetourned;
            }else {
                return true;
            }
        }

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

}
