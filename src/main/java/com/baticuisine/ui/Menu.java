package main.java.com.baticuisine.ui;

import java.util.Scanner;

public class Menu {

    public static int mainMenu(Scanner scan) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║        Bienvenue dans l'application de gestion des projets        ║");
        System.out.println("║               de rénovation de cuisines                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");

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
        System.out.println("║                       🔍 Recherche de client                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════╝");

        System.out.println("Souhaitez-vous :");
        System.out.println("1. 🔎 Chercher un client existant");
        System.out.println("2. ➕ Ajouter un nouveau client");

        System.out.print("\nVeuillez choisir une option : ");

        return scan.nextInt();
    }


}
