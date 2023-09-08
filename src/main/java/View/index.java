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
                int choise=scan.nextInt();
                switch (choise){
                    case 1:
                        livre.ajoutlivre();
                    case 2:
                        livre.getLivreDisponible();
                    case 3:
                        livre.deleteLivreperdu();
                }

        }

    }

