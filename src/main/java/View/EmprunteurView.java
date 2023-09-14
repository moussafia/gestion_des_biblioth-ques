package View;

import controller.Collections;
import controller.Emprunteur;
import controller.EmprunteurLivre;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmprunteurView {

    public  static  void EmpruntLivre() throws  Exception{
        Scanner scan=new Scanner(System.in);
        Emprunteur emprunteur=new Emprunteur();
        System.out.println("donner numero d'etudiant(e)");
        int quantity=0;
        int numero_etud= Integer.parseInt(scan.nextLine());
        if(!Emprunteur.CheckEmprunteur(numero_etud)){
            System.out.println("vous ete deja emprunter un livre");
        }
        else{
            emprunteur.setNumeroCondidat(numero_etud);
            System.out.println("donner ISBN");
            Object ISBN=scan.nextLine();
            Collections collections=new Collections();
            List<Collections> livres=new ArrayList<>();
            livres=collections.rechercheLivreDispo("ISBN",ISBN);
            for(Collections liv: livres){
                quantity=liv.getQuantity();
            }
            if(quantity>0){
                System.out.println();
                System.out.println("donner nom emprunteur");
                String nom_emprunteur=scan.nextLine();
                emprunteur.setNom(nom_emprunteur);
                emprunteur.EmpruntLivre(emprunteur,ISBN);
                System.out.println("livre a ete emprunté en succes");
            }else {
                System.out.println("quantity est null");
            }
        }
    }

    public  static  void afficheLivreEmprunte() throws Exception{
        EmprunteurLivre livre=new EmprunteurLivre();
        List<EmprunteurLivre> livresList=livre.AffichageLivreEmprunter();
        System.out.println("---------------------------------------------------------------------------" +
                "------------------------------------------------------------------------------------------" +
                "---------------------");
        System.out.printf("| %-20s | %-20s | %-15s | %-20s | %-20s| %-4s | %-20s| %-20s | %-4s |\n",
                "Nom de livre", "Nom de l'auteur", "ISBN",  "Status","Nom de l'emprunteur",
                "Numero d'emprateur", "Date emprunte", "Date de retour", "Retard");

        System.out.println("-------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------" +
                "---------------------");
        for (EmprunteurLivre livr : livresList) {
            System.out.printf("| %-20s | %-20s | %-15s | %-20s | %-20s| %-20d | %-20s| %-20s | %-4d |\n",
                    livr.getCollections().getNomLivre(),
                    livr.getCollections().getAuteur(),
                    livr.getCollections().getISBN(),
                    livr.getStatus().getStatus(),
                    livr.getEmprunteur().getNom(),
                    livr.getEmprunteur().getNumeroCondidat(),
                    livr.getDate_emprunte(),
                    livr.getDate_retour(),
                    livr.getRetard());
        }
        System.out.println("------------------------------------------------------------------------------" +
                "---------------------------------------------------------------------------------------" +
                "---------------------");
    }

    public  static void RetournerLivreEmprunte() throws  Exception{
        EmprunteurLivre emprunteurLivre=new EmprunteurLivre();
        Scanner scanner=new Scanner(System.in);
        System.out.println("donner le numero de condidat");
        int nCondidat= scanner.nextInt();
        if(emprunteurLivre.RetournerLivre(nCondidat)){
            System.out.println("livre a ete retourner en sucess");
        }else {
            System.out.println("vous avez pas emprunter un livre");
        }
    }
}
