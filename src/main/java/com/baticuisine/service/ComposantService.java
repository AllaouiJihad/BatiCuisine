package main.java.com.baticuisine.service;

import main.java.com.baticuisine.config.DatabaseConnection;
import main.java.com.baticuisine.model.Composants;
import main.java.com.baticuisine.model.MainOeuvre;
import main.java.com.baticuisine.model.Materiau;
import main.java.com.baticuisine.model.Projet;
import main.java.com.baticuisine.repository.MainOeuvreRepositoryImpl;
import main.java.com.baticuisine.repository.MateriauRepositoryImpl;

import java.sql.Connection;

public class ComposantService {

    private Connection connection = DatabaseConnection.getInstance().getConnection();

    MainOeuvreRepositoryImpl mainOeuvreRepository;
    MateriauRepositoryImpl materiauRepository;

    public ComposantService() {
        this.mainOeuvreRepository = new MainOeuvreRepositoryImpl(connection);
        this.materiauRepository = new MateriauRepositoryImpl(connection);
    }


}
