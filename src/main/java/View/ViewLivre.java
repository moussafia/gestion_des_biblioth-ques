package View;

import controller.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewLivre {
    public static void ajoutlivre() throws Exception {
        Scanner scan = new Scanner(System.in);
        Collections collection=new Collections();
        System.out.println("donner le ISBN de livre");
        String ISBN = scan.nextLine();
        if (!collection.rechercheLivreDispo("ISBN",ISBN).isEmpty()) {
            System.out.println("ISBN est deja existe.Si vous voulez ajouter quantity de ce livre modifier la quantite");
        } else {
            System.out.println("donner le nom de livre");
            String nom_livre = scan.nextLine();
            System.out.println("donner le nom d'auteur");
            String nom_auteur = scan.nextLine();
            System.out.println("donner quantity de livre");
            int quantity = Integer.parseInt(scan.nextLine());
            collection.setNomLivre(nom_livre.trim());
            collection.setAuteur(nom_auteur.trim());
            collection.setISBN(ISBN.trim());
            collection.setQuantity(quantity);
            collection.AjouterCollection(collection);
            System.out.println("collection a ete ajoute avec succes");
        }
    }

    public static void updatelivre() throws Exception {
        Scanner scan = new Scanner(System.in);
        Collections collection=new Collections();
        System.out.println("donner le ISBN de livre");
        String ISBN = scan.nextLine();
        int id_livre=collection.find("ISBN",ISBN);
        if (id_livre>0) {
            System.out.println("donner le nom de livre");
            String nom_livre = scan.nextLine();
            System.out.println("donner le nom d'auteur");
            String nom_auteur = scan.nextLine();
            System.out.println("donner quantity de livre");
            int quantity = Integer.parseInt(scan.nextLine());
            collection.setId(id_livre);
            collection.setNomLivre(nom_livre.trim());
            collection.setAuteur(nom_auteur.trim());
            collection.setISBN(ISBN.trim());
            collection.setQuantity(quantity);
            collection.UpdateCollection(collection);
            System.out.println("collection a ete modifie avec succes");
        } else {
            System.out.println("ISBN est n' existe pas");
        }
    }

    public static void getLivreDisponible() throws Exception {
        Collections livre=new Collections();
        List<Collections> livresList=livre.AffichageLivreDispo();
        System.out.println("----------------------------------------------------------");
        System.out.printf("| %-4s | %-20s | %-20s | %-4s |\n", "Quantity", "Nom de livre", "Nom de l'auteur", "ISBN");
        System.out.println("----------------------------------------------------------");
        for(Collections livr: livresList){
            System.out.printf("| %-4d | %-20s | %-20s | %-4s |\n", livr.getQuantity(), livr.getNomLivre(), livr.getAuteur(), livr.getISBN());
        }
        System.out.println("----------------------------------------------------------");
    }

    public static void deleteLivreperdu() throws Exception{
        Scanner scan=new Scanner(System.in);
        System.out.println("donner le ISBN de livre");
        String ISBN = scan.nextLine();
        Collections collections = new Collections();
        int id_livre=collections.find("ISBN",ISBN);
        if (id_livre>0) {
            collections.deleteCollection(id_livre);
            System.out.println("collection delete en succes");
        }else{
            System.out.println("ISBN n'existe pas");
        }
    }

    public  static  void  rechercheLivreDispo() throws  Exception{
        Scanner scan=new Scanner(System.in);
        Collections collections=new Collections();
        List<Collections> livres=new ArrayList<>();
        System.out.println("rechercher par ISBN ou nom auteur ou nom de livre de livre");
        System.out.println("1:ISBN");
        System.out.println("2:nom d'auteur");
        System.out.println("3:nom de livre");
        int choise= Integer.parseInt(scan.nextLine());
        switch (choise){
            case 1:
                System.out.println("donner ISBN");
                Object ISBN=scan.nextLine();
                livres= collections.rechercheLivreDispo("ISBN",ISBN);
                break;
            case 2:
                System.out.println("donner auteur");
                Object auteur=scan.nextLine();
                livres= collections.rechercheLivreDispo("auteur",auteur);
                break;
            case 3:
                System.out.println("donner nom de livre");
                Object nomLivre=scan.nextLine();
                livres= collections.rechercheLivreDispo("nom_livre",nomLivre);
                break;
            default:
                break;
        }

        System.out.println("---------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s| %-20s | %-4s |\n", "Nom de livre", "Nom de l'auteur", "ISBN","quantity");
        System.out.println("---------------------------------------------------------------------------------------");
        for(Collections livr: livres){
            System.out.printf("| %-20s | %-20s | %-20s| %-4d |\n", livr.getNomLivre(), livr.getAuteur(), livr.getISBN(),livr.getQuantity());
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }


    public static void StatistiqueLivre() throws Exception{
        Livres livre=new Livres();
        List<Status> livresConut=livre.statistiqueStatus();
        System.out.println("");
        System.out.println("-----------------------------------------\n");
        System.out.printf("|%-20s|%4s|\n","status","nombres des livres");
        System.out.println("-----------------------------------------\n");
        for (Status items: livresConut){
            System.out.printf("|%-20s|%4d\n",items.getStatus(),items.getNombre_livre());
        }
        System.out.println("-----------------------------------------\n");

    }

}
