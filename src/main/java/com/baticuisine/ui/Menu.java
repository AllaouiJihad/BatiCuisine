package main.java.com.baticuisine.ui;

import main.java.com.baticuisine.model.Client;
import main.java.com.baticuisine.service.ClientService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    public static int mainMenu(Scanner scan) {
        while (true) {
            System.out.println("╔═════════════════════════╗");
            System.out.println("║      *** Menu ***       ║");
            System.out.println("╚═════════════════════════╝");

            System.out.println("1. ➤ Créer un nouveau projet");
            System.out.println("2. ➤ Afficher les projets existants");
            System.out.println("3. ➤ Calculer le coût d'un projet");
            System.out.println("4. ➤ Afficher un projet par son ID ");
            System.out.println("5. ➤ Supprimer un projet");
            System.out.println("6. ➤ Calculer le coût d'un projet");
            System.out.println("7. ➤ Gestion de Devis ");
            System.out.println("8. ➤ Gestion de Clients ");
            System.out.println("9. ➤ Quitter");

            System.out.print("\nVeuillez choisir une option: ");

            try {
                int choice = Integer.parseInt(scan.nextLine().trim());
                if (choice >= 1 && choice <= 9) {
                    return choice;
                } else {
                    System.out.println("Option invalide. Veuillez choisir un nombre entre 1 et 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        }
    }
    public static int clientMenu(Scanner scan) {
        System.out.println("╔═════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       🔍 Recherche de client                    ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════╝");

        System.out.println("Souhaitez-vous :");
        System.out.println("1. 🔎 Chercher un client existant");
        System.out.println("2. ➕ Ajouter un nouveau client");

        System.out.print("\nVeuillez choisir une option : ");

        return scan.nextInt();
    }
    public static void displayClientInfo(Client client) {
        System.out.println("Client  !");
        System.out.println("Nom : " + client.getName());
        System.out.println("Adresse : " + client.getAdresse());
        System.out.println("Numéro de téléphone : " + client.getTelephone());
    }

    public static void handleClientMenu(Scanner scan, ClientService clientService) throws SQLException {
        try {
            int clientChoix = Menu.clientMenu(scan);
            scan.nextLine();
            switch (clientChoix) {
                case 1:
                    searchExistingClient(scan, clientService);
                    break;
                case 2:
                    addNewClient(scan, clientService);
                    break;
                default:
                    System.out.println("Option invalide. Retour au menu principal.");
            }


        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
        }


    }
    public static void searchExistingClient(Scanner scan, ClientService clientService) throws SQLException {
        System.out.println("--- Recherche de client existant ---");
        System.out.println("Entrez le nom du client :");
        String nom = scan.nextLine();
        Optional<Client> client = clientService.findByname(nom);
        if (client.isPresent()) {
            Menu.displayClientInfo(client.get());
            System.out.println("Souhaitez-vous continuer avec ce client ? (y/n)");
            String validate = scan.nextLine();
            // Ajoutez ici la logique pour continuer avec le client
        } else {
            System.out.println("Client non trouvé !");
        }
    }

    public static void addNewClient(Scanner scan, ClientService clientService) throws SQLException {
        System.out.println("--- Ajouter un client  ---");
        System.out.println("Entrez le nom du client :");
        String nom = scan.nextLine();
        System.out.println("Entrez l'adresse du client :");
        String adresse = scan.nextLine();
        System.out.println("Entrez le numéro de téléphone du client :");
        String tel = scan.nextLine();
        System.out.println("Est-ce que ce client est professionnel : (o/n)");
        String isPro = scan.nextLine();
        Client client = clientService.addClient(nom, adresse, tel, isPro.equalsIgnoreCase("o"));

        Menu.displayClientInfo(client);
        System.out.println("Souhaitez-vous continuer avec ce client ? (y/n)");
        String validate = scan.nextLine();
        // Ajoutez ici la logique pour continuer avec le client
    }


}
