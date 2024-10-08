package baticuisine.ui;

import baticuisine.model.*;
import baticuisine.model.enums.EtatProjet;
import baticuisine.service.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ComposantUi {


    public static void calculCout(Scanner scanner, Projet projet, ComposantService composantService) throws SQLException {
        System.out.println("--- Calcul du coût total ---");
        System.out.println("Entrez le pourcentage de TVA (%) : ");
        double tauxTVA = Double.parseDouble(scanner.nextLine());
        composantService.updateTva(projet.getId(), tauxTVA);

        System.out.println("Calcul du coût en cours...\n");
        System.out.println("--- Résultat du Calcul ---");

        System.out.println("Nom du projet : " + projet.getNomProjet());
        System.out.println("Client : " + projet.getClient().getName());
        System.out.println("Adresse du chantier : " + projet.getClient().getAdresse());

        System.out.println("\n--- Détail des Coûts ---");

        // Calcul des coûts des matériaux
        List<Composants> materiaux = projet.getComposants().stream()
                .filter(c -> c instanceof Materiau)
                .map(c -> {
                    c.setTauxTva(tauxTVA);
                    return c;
                })
                .collect(Collectors.toList());


        double coutTotalMateriauxSansTVA = 0.0;
        double coutTotalMateriauxAvecTVA = 0.0;

        System.out.println("1. Matériaux :");
        for (Composants comp : materiaux) {
            Materiau mat = (Materiau) comp;
            double coutMateriau = new MateriauService().calculateCost(mat);
            double coutMateriauSansTVA =new  MateriauService().CostWTVA(mat);
            coutTotalMateriauxSansTVA += coutMateriauSansTVA;
            coutTotalMateriauxAvecTVA += coutMateriau;

            System.out.printf("- %s : %.2f € (quantité : %.2f %s, coût unitaire : %.2f €/%s, qualité : %.2f, transport : %.2f €)%n",
                    mat.getNom(), coutMateriau, mat.getQuantite(), mat.getCoutUnitaire(), mat.getCoutUnitaire(), mat.getCoutUnitaire(),
                    mat.getCoefficientQualite(), mat.getCoutTransport());
        }
        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**%n", coutTotalMateriauxSansTVA);
        System.out.printf("**Coût total des matériaux avec TVA (%.0f%%) : %.2f €**%n", tauxTVA, coutTotalMateriauxAvecTVA);

        // Calcul des coûts de main-d'œuvre
        List<Composants> mainOeuvre = projet.getComposants().stream()
                .filter(c -> c instanceof MainOeuvre)
                .map(c -> {
                    c.setTauxTva(tauxTVA);
                    return c;
                })
                .collect(Collectors.toList());

        double coutTotalMainOeuvreSansTVA = 0.0;
        double coutTotalMainOeuvreAvecTVA = 0.0;

        System.out.println("\n2. Main-d'œuvre :");
        for (Composants comp : mainOeuvre) {
            MainOeuvre mo = (MainOeuvre) comp;

            double coutMO = mo.calculateCost();
            double coutMOSansTVA = mo.CostWTVA();
            coutTotalMainOeuvreSansTVA += coutMOSansTVA;
            coutTotalMainOeuvreAvecTVA += coutMO;

            System.out.printf("- %s : %.2f € (taux horaire : %.2f €/h, heures travaillées : %.2f h, productivité : %.2f)%n",
                    mo.getNom(), coutMO, mo.getTauxHoraire(), mo.getHeuresTravail(), mo.getProductiviteOuvrier());
        }
        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**%n", coutTotalMainOeuvreSansTVA);
        System.out.printf("**Coût total de la main-d'œuvre avec TVA (%.0f%%) : %.2f €**%n", tauxTVA, coutTotalMainOeuvreAvecTVA);

        // Calcul du coût total
        double coutTotalAvantMarge = coutTotalMateriauxAvecTVA + coutTotalMainOeuvreAvecTVA;

        double margeBeneficiaire = projet.getMargeBeneficiaire();
        double coutMarge = (coutTotalAvantMarge * margeBeneficiaire) / 100;
        double coutTotalFinal = coutTotalAvantMarge + coutMarge;

        System.out.printf("\n3. Coût total avant marge : %.2f €%n", coutTotalAvantMarge);
        System.out.printf("4. Marge bénéficiaire (%.0f%%) : %.2f €%n", margeBeneficiaire, coutMarge);
        System.out.printf("**Coût total final du projet : %.2f €**%n", coutTotalFinal);

        projet.setCoutTotal(coutTotalFinal);

        System.out.println("--- Enregistrement du Devis ---");

        /*System.out.println("Entrez la date d'émission du devis (format : jj/mm/aaaa) :");
        LocalDate dateEmission = LocalDate.parse(scanner.nextLine());
        System.out.println("Entrez la date de validité du devis (format : jj/mm/aaaa) :");
        LocalDate dateValidite = LocalDate.parse(scanner.nextLine());*/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Entrez la date d'émission du devis (format : jj/mm/aaaa) :");
        String emission = scanner.nextLine();
        LocalDate checkInDate = LocalDate.parse(emission, formatter);
        Date dateEmission = Date.from(checkInDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("Entrez la date de validité du devis (format : jj/mm/aaaa) :");
        String validity = scanner.nextLine();
        LocalDate validityDate = LocalDate.parse(validity, formatter);
        Date dateValidite = Date.from(validityDate.atStartOfDay(ZoneId.systemDefault()).toInstant());



        System.out.println("Souhaitez-vous enregistrer le devis ? (y/n) : \n");
        String validate = scanner.nextLine();
        if (validate.equalsIgnoreCase("y")){
            boolean accepte = true;
            Devis devis = new DevisService().add(dateEmission, dateValidite,coutTotalFinal,accepte,projet);
            System.out.println(devis);
            projet.setEtatProjet(EtatProjet.TERMINE);
            new ProjetService().update(projet);
            System.out.println("Devis enregistré avec succès !\n" +
                    "--- Fin du projet ---");
        }else {
            projet.setEtatProjet(EtatProjet.ANNULE);
            new ProjetService().update(projet);
            System.out.println("--- Fin du projet ---");
        }

    }


}
