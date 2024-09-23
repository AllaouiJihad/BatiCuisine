package main.java.com.baticuisine.ui;

import main.java.com.baticuisine.model.Projet;
import main.java.com.baticuisine.service.ProjetService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProjetUi {
    public static void displayProjectById(Scanner scan, ProjetService projetService) {
        System.out.println("Affichage d'un projet par son ID...");
        System.out.print("Veuillez entrer l'ID du projet : ");

        try {
            int id = Integer.parseInt(scan.nextLine().trim());
            Optional<Projet> projetOptional = projetService.getById(id);

            if (projetOptional.isPresent()) {
                Projet projet = projetOptional.get();
                System.out.println("╔═════════════════════════╗");
                System.out.println("║    Détails du Projet    ║");
                System.out.println("╚═════════════════════════╝");
                System.out.println("ID: " + projet.getId());
                System.out.println("Nom: " + projet.getNomProjet());
                System.out.println("Marge beneficiaire: " + projet.getMargeBeneficiaire());
                System.out.println("Cout total: " + projet.getCoutTotal());
                System.out.println("Etat de projet " + projet.getEtatProjet());
                System.out.println("Client: " + projet.getClient().getName());
                // Ajoutez d'autres détails du projet selon votre modèle
            } else {
                System.out.println("Aucun projet trouvé avec l'ID " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Erreur : Veuillez entrer un nombre valide pour l'ID du projet.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du projet : " + e.getMessage());

        }
    }

    public static void displayAllProjects(ProjetService projetService) {
        try {
            List<Projet> projects = projetService.getAll();

            if (projects.isEmpty()) {
                System.out.println("Aucun projet n'a été trouvé.");
                return;
            }

            System.out.println("╔═════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                       Liste des Projets                             ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════╝");

            System.out.printf("%-5s %-20s %-15s %-15s %-15s %-20s%n",
                    "ID", "Nom du Projet", "Marge Bénéf.", "Coût Total", "État", "Client");
            System.out.println("------------------------------------------------------------------------------");

            for (Projet projet : projects) {
                System.out.printf("%-5d %-20s %-15.2f %-15.2f %-15s %-20s%n",
                        projet.getId(),
                        projet.getNomProjet(),
                        projet.getMargeBeneficiaire(),
                        projet.getCoutTotal(),
                        projet.getEtatProjet(),
                        projet.getClient().getName());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des projets : " + e.getMessage());
        }
    }

    public static void deleteProject(Scanner scan, ProjetService projetService) {
        System.out.println("Suppression d'un projet...");
        System.out.print("Veuillez entrer l'ID du projet à supprimer : ");

        try {
            int id = Integer.parseInt(scan.nextLine().trim());

            // Vérifier si le projet existe avant de le supprimer
            Optional<Projet> projetOptional = projetService.getById(id);

            if (projetOptional.isPresent()) {
                Projet projet = projetOptional.get();
                System.out.println("Vous êtes sur le point de supprimer le projet suivant :");
                System.out.println("ID : " + projet.getId());
                System.out.println("Nom : " + projet.getNomProjet());
                System.out.println("État : " + projet.getEtatProjet());
                System.out.println("Client : " + projet.getClient().getName());

                System.out.print("Êtes-vous sûr de vouloir supprimer ce projet ? (o/n) : ");
                String confirmation = scan.nextLine().trim().toLowerCase();

                if (confirmation.equals("o")) {
                    boolean deleted = projetService.delete(id);
                    if (deleted) {
                        System.out.println("Le projet a été supprimé avec succès.");
                    } else {
                        System.out.println("Échec de la suppression du projet.");
                    }
                } else {
                    System.out.println("Suppression annulée.");
                }
            } else {
                System.out.println("Aucun projet trouvé avec l'ID " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Erreur : Veuillez entrer un nombre valide pour l'ID du projet.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du projet : " + e.getMessage());
        }
    }



}
