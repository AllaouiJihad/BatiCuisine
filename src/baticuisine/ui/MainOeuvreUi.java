package baticuisine.ui;

import baticuisine.model.Composants;
import baticuisine.model.MainOeuvre;
import baticuisine.model.Projet;
import baticuisine.service.ComposantService;
import baticuisine.service.MainOeuvreService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainOeuvreUi {

    public static void addMateriauxToProject(Projet projet, Scanner scanner, MainOeuvreService mainOeuvreService, List<Composants> composants
    ) throws SQLException {
        System.out.println("--- --- Ajout de la main-d'œuvre --- ---");
        boolean addMore = true;

        while (addMore) {
            System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste)  : ");
            String name = scanner.nextLine();

            System.out.print("Entrez le taux horaire de cette main-d'œuvre : ");
            double tauxHoraire = Double.parseDouble(scanner.nextLine());

            System.out.print("Entrez le nombre d'heures travaillées : ");
            double heuresTravail = Double.parseDouble(scanner.nextLine());

            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            double productiviteOuvrier = Double.parseDouble(scanner.nextLine());


            MainOeuvre mainOeuvre = mainOeuvreService.add(name,tauxHoraire,heuresTravail,productiviteOuvrier,projet);
            composants.add(mainOeuvre);
            if (mainOeuvre != null) {
                System.out.println("mainOeuvre ajouté avec succès !");
            } else {
                System.out.println("Erreur lors de l'ajout du mainOeuvre.");
            }

            System.out.print("Voulez-vous ajouter un autre mainOeuvre ? (y/n) : ");
            addMore = scanner.nextLine().trim().equalsIgnoreCase("y");
        }


        projet.setComposants(composants);
        ComposantUi.calculCout(scanner,projet,new ComposantService());

    }

}
