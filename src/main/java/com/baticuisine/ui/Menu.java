package main.java.com.baticuisine.ui;

import main.java.com.baticuisine.model.Client;
import main.java.com.baticuisine.service.ClientService;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    public static int mainMenu(Scanner scan) {


        System.out.println("╔═════════════════════════╗");
        System.out.println("║      *** Menu ***       ║");
        System.out.println("╚═════════════════════════╝");

        System.out.println("1. ➤ Créer un nouveau projet");
        System.out.println("2. ➤ Afficher les projets existants");
        System.out.println("3. ➤ Calculer le coût d'un projet");
        System.out.println("4. ➤ Quitter");

        System.out.print("\nVeuillez choisir une option: ");

        return scan.nextInt();
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
        System.out.println("Client trouvé !");
        System.out.println("Nom : " + client.getName());
        System.out.println("Adresse : " + client.getAdresse());
        System.out.println("Numéro de téléphone : " + client.getTelephone());
    }

    public static void handleClientMenu(Scanner scan, ClientService clientService) throws SQLException {
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
