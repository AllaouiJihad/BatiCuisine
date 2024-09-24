package main.java.com.baticuisine.ui;

import main.java.com.baticuisine.model.Composants;
import main.java.com.baticuisine.model.Materiau;
import main.java.com.baticuisine.model.Projet;
import main.java.com.baticuisine.service.MainOeuvreService;
import main.java.com.baticuisine.service.MateriauService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MateriauUi {


    public static void addMateriauxToProject(Projet projet, Scanner scanner, MateriauService materiauService) throws SQLException {
        System.out.println("--- Ajout des matériaux ---");
        boolean addMore = true;
        List<Composants> composants=new ArrayList<>();
        while (addMore) {
            System.out.print("Entrez le nom du matériau : ");
            String name = scanner.nextLine();

            System.out.print("Entrez la quantité de ce matériau : ");
            double quantity = Double.parseDouble(scanner.nextLine());

            System.out.print("Entrez le coût unitaire de ce matériau (€) : ");
            double unitCost = Double.parseDouble(scanner.nextLine());

            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            double transportCost = Double.parseDouble(scanner.nextLine());

            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            double qualityCoefficient = Double.parseDouble(scanner.nextLine());

            Materiau materiau = materiauService.add(name, unitCost, quantity, transportCost, qualityCoefficient,projet);
            composants.add(materiau);
            if (materiau != null) {
                System.out.println("Matériau ajouté avec succès !");
            } else {
                System.out.println("Erreur lors de l'ajout du matériau.");
            }

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            addMore = scanner.nextLine().trim().equalsIgnoreCase("y");
        }

        MainOeuvreUi.addMateriauxToProject(projet,scanner,new MainOeuvreService(),composants);

    }

}
