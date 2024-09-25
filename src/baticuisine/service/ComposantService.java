package baticuisine.service;

import baticuisine.config.DatabaseConnection;
import baticuisine.repository.ComposantRepositoryImpl;

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
