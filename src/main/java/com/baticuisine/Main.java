package main.java.com.baticuisine;

import main.java.com.baticuisine.model.Client;
import main.java.com.baticuisine.service.ClientService;
import main.java.com.baticuisine.service.ProjetService;
import main.java.com.baticuisine.ui.ClientUi;
import main.java.com.baticuisine.ui.Menu;
import main.java.com.baticuisine.ui.ProjetUi;

import java.sql.SQLException;
import java.util.Optional;
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
                    case 6:
                        System.out.println("Calcul du coût d'un projet...");
                        // Ajoutez ici la logique pour calculer le coût d'un projet
                        break;
                    case 4:
                        ProjetUi.displayProjectById(scan,projetService);
                        break;
                    case 5:
                        ProjetUi.deleteProject(scan,projetService);
                        break;
                    case 7:
                        System.out.println("Gestion de Devis...");
                        // Ajoutez ici la logique pour la gestion de devis
                        break;
                    case 8:
                        ClientUi.handleClientMenu(scan,clientService);
                        break;
                    case 9:
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