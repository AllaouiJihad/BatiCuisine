package baticuisine.ui;

import baticuisine.model.Client;
import baticuisine.service.ClientService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientUi {
    public static void handleClientMenu(Scanner scan, ClientService clientService) {
        while (true) {

            System.out.println("╔═════════════════════════════════════════════════════════════════╗");
            System.out.println("║                        Gestion des Clients                      ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════╝");
            System.out.println("1. ➤ Créer un nouveau client");
            System.out.println("2. ➤ Afficher tous les clients");
            System.out.println("3. ➤ Afficher un client par ID");
            System.out.println("4. ➤ Mettre à jour un client");
            System.out.println("5. ➤ Supprimer un client");
            System.out.println("6. ➤ Retour au menu principal");
            System.out.print(" \n Choisissez une option : ");

            int choice = Integer.parseInt(scan.nextLine());

            try {
                switch (choice) {
                    case 1:
                        createClient(scan, clientService);
                        break;
                    case 2:
                        displayAllClients(clientService);
                        break;
                    case 3:
                        displayClientById(scan, clientService);
                        break;
                    case 4:
                        updateClient(scan, clientService);
                        break;
                    case 5:
                        deleteClient(scan, clientService);
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Option invalide. Veuillez réessayer.");
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'opération : " + e.getMessage());
            }
        }
    }

    private static void createClient(Scanner scan, ClientService clientService) throws SQLException {
        System.out.println("╔═════════════════════════════════════════════════════════════════╗");
        System.out.println("║                        Création d'un nouveau client             ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════╝");
        System.out.print("Nom : ");
        String name = scan.nextLine();
        System.out.print("Adresse : ");
        String adresse = scan.nextLine();
        System.out.print("Téléphone : ");
        String telephone = scan.nextLine();
        System.out.print("Client professionnel ? (o/n) : ");
        boolean isPro = scan.nextLine().equalsIgnoreCase("o");

        Client newClient = clientService.addClient(name, adresse, telephone, isPro);
        System.out.println("Client créé avec succès. ID : " + newClient.getId());
    }

    private static void displayAllClients(ClientService clientService) throws SQLException {
        List<Client> clients = clientService.findAllClients();
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
        } else {
            System.out.println("╔═════════════════════════════════════════════════════════════════╗");
            System.out.println("║                       Liste des clients                         ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════╝");
            for (Client client : clients) {
                System.out.printf("ID: %d, Nom: %s, Téléphone: %s, Adresse : %s , Pro: %s %n",
                        client.getId(), client.getName(), client.getTelephone(),client.getAdresse(), client.is_professional() ? "Oui" : "Non");
            }
        }
    }

    private static void displayClientById(Scanner scan, ClientService clientService) throws SQLException {
        System.out.print("Entrez le nom du client : ");
        String name = scan.nextLine();
        Optional<Client> clientOpt = clientService.findByname(name);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            System.out.printf("ID: %d%nNom: %s%nAdresse: %s%nTéléphone: %s%nPro: %s%n",
                    client.getId(), client.getName(), client.getAdresse(), client.getTelephone(), client.is_professional() ? "Oui" : "Non");
        } else {
            System.out.println("Client non trouvé.");
        }
    }

    private static void updateClient(Scanner scan, ClientService clientService) throws SQLException {
        System.out.print("Entrez l'ID du client à mettre à jour : ");
        int id = Integer.parseInt(scan.nextLine());
        Optional<Client> clientOpt = clientService.findById(id);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            System.out.print("Nouveau nom (laisser vide pour ne pas changer) : ");
            String name = scan.nextLine();
            if (!name.isEmpty()) client.setName(name);

            System.out.print("Nouvelle adresse (laisser vide pour ne pas changer) : ");
            String adresse = scan.nextLine();
            if (!adresse.isEmpty()) client.setAdresse(adresse);

            System.out.print("Nouveau téléphone (laisser vide pour ne pas changer) : ");
            String telephone = scan.nextLine();
            if (!telephone.isEmpty()) client.setTelephone(telephone);

            System.out.print("Client professionnel ? (o/n/laisser vide pour ne pas changer) : ");
            String isPro = scan.nextLine();
            if (!isPro.isEmpty()) client.setIs_professional(isPro.equalsIgnoreCase("o"));

            Client updated = clientService.updateClient(client);

            if (updated != null) {
                System.out.println("Client mis à jour avec succès.");
            } else {
                System.out.println("Échec de la mise à jour du client.");
            }
        } else {
            System.out.println("Client non trouvé.");
        }
    }

    private static void deleteClient(Scanner scan, ClientService clientService) throws SQLException {
        System.out.print("Entrez l'ID du client à supprimer : ");
        int id = Integer.parseInt(scan.nextLine());
        System.out.print("Êtes-vous sûr de vouloir supprimer ce client ? (o/n) : ");
        if (scan.nextLine().equalsIgnoreCase("o")) {
            boolean deleted = clientService.deleteClient(id);
            if (deleted) {
                System.out.println("Client supprimé avec succès.");
            } else {
                System.out.println("Échec de la suppression du client.");
            }
        } else {
            System.out.println("Suppression annulée.");
        }
    }
}
