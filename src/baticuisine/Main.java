package baticuisine;

import baticuisine.service.ClientService;
import baticuisine.service.ProjetService;
import baticuisine.ui.ClientUi;
import baticuisine.ui.Menu;
import baticuisine.ui.ProjetUi;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        ProjetService projetService = new ProjetService();
        Scanner scan = new Scanner(System.in);

        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║ Bienvenue dans l'application de gestion des projets               ║");
        System.out.println("║ de rénovation de cuisines                                         ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");

        int choix;
        do {
            choix = Menu.mainMenu(scan);
            try {
                switch(choix) {
                    case 1:
                        Menu.handleClientMenu(scan, clientService);
                        break;
                    case 2:
                        ProjetUi.displayAllProjects(projetService);
                        break;
                    case 3:
                        System.out.println("entrer Id de projet :");
                        int id = scan.nextInt();
                        Double cout = projetService.coutTotal(id);
                        System.out.println("cout total de projet : "+ cout );
                        break;
                    case 4:
                        ProjetUi.displayProjectById(scan,projetService);
                        break;
                    case 5:
                        ProjetUi.deleteProject(scan,projetService);
                        break;

                    case 6:
                        ClientUi.handleClientMenu(scan,clientService);
                        break;
                    case 7:
                        System.out.println("Merci d'avoir utilisé notre application. Au revoir !");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Une erreur de base de données s'est produite : " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Une erreur inattendue s'est produite : " + e.getMessage());
            }
        } while(choix != 9);

        scan.close();
    }
}