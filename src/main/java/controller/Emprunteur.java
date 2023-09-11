package controller;

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

        public Emprunteur EmprunterLivre(Emprunteur emprunte,int ISBN){
                String sql_emprunteur="INSERT INTO emprunteurs(nom_emprunteur,numero_condidat) values('";
                sql_emprunteur+=getNom()+"','"+getNumeroCondidat()+"')";

                String sql_emprunteurs_livres="INSERT INTO emprunteurs_livre values(";

                return  emprunte;
        }
}
