package View;

import java.util.Scanner;

public class index {
        public void menu() throws Exception {
            Scanner scan=new Scanner(System.in);
            boolean exist=true;
            while(exist){
                System.out.println("choisir un nombre selon votre choix");
                System.out.println("1: Ajouter une livre");
                System.out.println("2: afficher les listes des livre disponible");
                System.out.println("3: supprimer une livre");
                System.out.println("4: modifier une livre ");
                System.out.println("5: rechercher par une ISBN ou auteur ou nom de livre");
                System.out.println("6: emprunter un livre");
                System.out.println("7: afficher les livres emprunter");
                System.out.println("8: retourner un livre emprunter");
                System.out.println("9: afficher les statistique");
                System.out.println("0: Exist");
                int choise=scan.nextInt();
                switch (choise){
                    case 1:
                        ViewLivre.ajoutlivre();
                        break;
                    case 2:
                        ViewLivre.getLivreDisponible();
                        break;
                    case 3:
                        ViewLivre.deleteLivreperdu();
                        break;
                    case 4:
                        ViewLivre.updatelivre();
                        break;
                    case 5:
                        ViewLivre.rechercheLivreDispo();
                        break;
                    case 6:
                        EmprunteurView.EmpruntLivre();
                        break;
                    case 7:
                        EmprunteurView.afficheLivreEmprunte();
                        break;
                    case 8:
                        EmprunteurView.RetournerLivreEmprunte();
                        break;
                    case 9:
                        ViewLivre.StatistiqueLivre();
                        break;
                    case 0:
                        exist=false;
                        break;
                    default:
                        exist=false;
                        break;
                }

            }

        }

    }

