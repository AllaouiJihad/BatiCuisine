package main.java.com.baticuisine.service;

import main.java.com.baticuisine.config.DatabaseConnection;
import main.java.com.baticuisine.model.Composants;
import main.java.com.baticuisine.model.MainOeuvre;
import main.java.com.baticuisine.model.Materiau;
import main.java.com.baticuisine.model.Projet;
import main.java.com.baticuisine.repository.ComposantRepositoryImpl;
import main.java.com.baticuisine.repository.MainOeuvreRepositoryImpl;
import main.java.com.baticuisine.repository.MateriauRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class ComposantService {

    private Connection connection = DatabaseConnection.getInstance().getConnection();
    ComposantRepositoryImpl composantRepository;
    public ComposantService() {
        this.composantRepository = new ComposantRepositoryImpl(connection);
    }

    public boolean updateTva(int id, Double tauxTVA) throws SQLException {
        return composantRepository.updateTva(id,tauxTVA);
    }









}
