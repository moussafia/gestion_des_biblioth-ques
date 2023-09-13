package View;

import controller.Emprunteur;
import controller.EmprunteurLivre;
import controller.Livres;
import controller.Status;

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
        livre.setISBN(ISBN);
        livre.AjouterLivre(livre);
        return livre;
    }

    public static Livres updatelivre() throws Exception {
        Scanner scan=new Scanner(System.in);
        System.out.println("donner id de livre");
        int id= Integer.parseInt(scan.nextLine());
        System.out.println("donner le nom de livre");
        String nom_livre= scan.nextLine();
        System.out.println("donner le nom d'auteur");
        String nom_auteur= scan.nextLine();
        System.out.println("donner le ISBN de livre");
        int ISBN= Integer.parseInt(scan.nextLine());
        System.out.println("donner le statut de livre");
        int statut_livre= Integer.parseInt(scan.nextLine());
        Livres livre=new Livres();
        livre.setId(id);
        livre.setNomLivre(nom_livre);
        livre.setAuteur(nom_auteur);
        livre.setISBN(ISBN);
        livre.setId_statut(statut_livre);
        livre.UpdateLivre(livre);
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

    public  static  void  rechercheByISBN() throws  Exception{
        Scanner scan=new Scanner(System.in);
        System.out.println("donner ISBN ou nom auteur ou nom de livre de livre");
        Object ISBN=scan.nextLine();
        Livres livre=new Livres();
        List<Livres> livres= livre.rechercheISBN(ISBN);
        System.out.println("----------------------------------------------------------");
        System.out.printf("| %-4s | %-20s | %-20s | %-4s |\n", "ID", "Nom de livre", "Nom de l'auteur", "ISBN");
        System.out.println("----------------------------------------------------------");
        for(Livres livr: livres){
            System.out.printf("| %-4d | %-20s | %-20s | %-4d |\n", livr.getId(), livr.getNomLivre(), livr.getAuteur(), livr.getISBN());
        }
        System.out.println("----------------------------------------------------------");
    }

    public  static  void EmpruntLivre() throws  Exception{
        Scanner scan=new Scanner(System.in);
        System.out.println("donner ISBN ou nom auteur ou nom de livre de livre");
        Object ISBN=scan.nextLine();
        Emprunteur emprunteur=new Emprunteur();
        System.out.println("donner nom emprunteur");
        String nom_emprunteur=scan.nextLine();
        emprunteur.setNom(nom_emprunteur);
        System.out.println("donner numero d'etudiant(e)");
        int numero_etud= Integer.parseInt(scan.nextLine());
        emprunteur.setNumeroCondidat(numero_etud);
        emprunteur.EmpruntLivre(emprunteur,ISBN);
    }

    public  static  void afficheLivreEmprunte() throws Exception{
        EmprunteurLivre livre=new EmprunteurLivre();
        List<EmprunteurLivre> livresList=livre.AffichageLivreEmprunter("l.status_id",2);
        System.out.println("---------------------------------------------------------------------------" +
                "------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s | %-15s | %-20s | %-20s | %-20s| %-20s | %-4s |\n",
                "Nom de livre", "Nom de l'auteur", "ISBN", "Nom de l'emprunteur",
                "Numero du candidat", "Date emprunte", "Date de retour", "Retard");

        System.out.println("-------------------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------------");
        for (EmprunteurLivre livr : livresList) {
            System.out.printf("| %-20s | %-20s | %-15s | %-20s | %-20s| %-20s | %-20s | %-4d |\n",
                    livr.getLivre().getNomLivre(),
                    livr.getLivre().getAuteur(),
                    livr.getLivre().getISBN(),
                    livr.getEmprunteur().getNom(),
                    livr.getEmprunteur().getNumeroCondidat(),
                    livr.getDate_emprunte(),
                    livr.getDate_retour().toString(),
                    livr.getRetard());
        }
        System.out.println("------------------------------------------------------------------------------" +
                "---------------------------------------------------------------------------------------");
    }

    public  static void RetournerLivreEmprunte() throws  Exception{
        EmprunteurLivre emprunteurLivre=new EmprunteurLivre();
        Scanner scanner=new Scanner(System.in);
        System.out.println("donner le numero de condidat");
        int nCondidat= scanner.nextInt();
        System.out.println("donner ISBN de livre");
        int ISBN= scanner.nextInt();
        emprunteurLivre.RetournerLivre(nCondidat,ISBN);
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
