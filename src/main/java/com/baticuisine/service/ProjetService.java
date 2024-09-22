package main.java.com.baticuisine.service;

import main.java.com.baticuisine.config.DatabaseConnection;
import main.java.com.baticuisine.repository.ClientRepositoryImpl;
import main.java.com.baticuisine.repository.ProjetRepositoryImpl;

import java.sql.Connection;

public class ProjetService {
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private ProjetRepositoryImpl projetRepository ;

    public ProjetService(ProjetRepositoryImpl projetRepository) {
        this.projetRepository = projetRepository;
    }
}
