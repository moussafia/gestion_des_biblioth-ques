package View;

import java.util.Scanner;

public class index {
        public static void menu() throws Exception {
            Scanner scan=new Scanner(System.in);
            ViewLivre livre= new ViewLivre();
            System.out.println("choisir un nombre selon votre choix");
                System.out.println("1: Ajouter une livre");
                System.out.println("2: afficher les listes des livre disponible");
                System.out.println("3: supprimer une livre");
                System.out.println("4: modifier une livre ");
                System.out.println("5: rechercher par une ISBN ou auteur ou nom de livre");
                System.out.println("7: emprunter un livre");
                System.out.println("8: afficher les livres emprunter");
                System.out.println("9: retourner un livre emprunter");
                System.out.println("10: afficher les statistique");
                int choise=scan.nextInt();
                switch (choise){
                    case 1:
                        livre.ajoutlivre();
                        break;
                    case 2:
                        livre.getLivreDisponible();
                        break;
                    case 3:
                        livre.deleteLivreperdu();
                        break;
                    case 4:
                        livre.updatelivre();
                        break;
                    case 5:
                        livre.rechercheByISBN();
                        break;
                }

        }

    }

