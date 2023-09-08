package View;

import controller.Livres;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewLivre {
    public static Livres ajoutlivre() throws Exception {
        Scanner scan=new Scanner(System.in);
        System.out.println("donner le nom de livre");
        String nom_livre= scan.nextLine();
        System.out.println("donner le nom d'auteur");
        String nom_auteur= scan.nextLine();
        System.out.println("donner le ISBN de livre");
        int ISBN= Integer.parseInt(scan.nextLine());
        Livres livre=new Livres();
        livre.setNomLivre(nom_livre);
        livre.setAuteur(nom_auteur);
        livre.SetId_statut(1);
        livre.setISBN(1234);
        livre.AjouterLivre(livre);
        return livre;
    }
    public static void getLivreDisponible() throws Exception {
        Livres livre=new Livres();
        List<Livres> livresList=livre.AffichageLivreDispo();
        System.out.println("----------------------------------------------------------");
        System.out.printf("| %-4s | %-20s | %-20s | %-4s |\n", "ID", "Nom de livre", "Nom de l'auteur", "ISBN");
        System.out.println("----------------------------------------------------------");
        for(Livres livr: livresList){
            System.out.printf("| %-4d | %-20s | %-20s | %-4d |\n", livr.getId(), livr.getNomLivre(), livr.getAuteur(), livr.getISBN());
        }
        System.out.println("----------------------------------------------------------");
    }

    public static void deleteLivreperdu() throws Exception{
        Scanner scan=new Scanner(System.in);
        System.out.println("donner id de livre qui veut vouler Supprimer");
        int id=Integer.parseInt(scan.nextLine());
        Livres livre=new Livres();
        livre.deleteUneLivre(id);
    }
}
