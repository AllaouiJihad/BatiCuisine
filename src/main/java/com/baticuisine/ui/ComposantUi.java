package main.java.com.baticuisine.ui;

import java.util.Scanner;

public class ComposantUi {

    public static void calculCout(Scanner scanner){
        System.out.println("--- Calcul du coût total ---");
        System.out.println("Entrez le pourcentage de TVA (%) : ");
        double tauxTVA = Double.parseDouble(scanner.nextLine());
        System.out.println("Calcul du coût en cours...\n");
        System.out.println("--- Résultat du Calcul ---");

    }
}
