package main.java.com.baticuisine;

import main.java.com.baticuisine.model.Client;
import main.java.com.baticuisine.service.ClientService;
import main.java.com.baticuisine.ui.Menu;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        Scanner scan = new Scanner(System.in);

        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║        Bienvenue dans l'application de gestion des projets        ║");
        System.out.println("║               de rénovation de cuisines                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");

        int choix;

        do {
            try {
                choix = Menu.mainMenu(scan);

                switch(choix) {
                    case 1:
                        Menu.handleClientMenu(scan, clientService);
                        break;
                    case 2:
                        System.out.println("Affichage des projets existants...");
                        // Ajoutez ici la logique pour afficher les projets existants
                        break;
                    case 3:
                        System.out.println("Calcul du coût d'un projet...");
                        // Ajoutez ici la logique pour calculer le coût d'un projet
                        break;
                    case 4:
                        System.out.println("Merci d'avoir utilisé notre application. Au revoir !");
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            } catch (SQLException e) {
                System.out.println("Une erreur de base de données s'est produite : " + e.getMessage());
                choix = 0; // Pour continuer la boucle
            } catch (Exception e) {
                System.out.println("Une erreur inattendue s'est produite : " + e.getMessage());
                choix = 0; // Pour continuer la boucle
            }
        } while(choix != 4);

        scan.close();
    }








}