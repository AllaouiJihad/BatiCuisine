package main.java.com.baticuisine.repository;

import main.java.com.baticuisine.model.Composants;
import main.java.com.baticuisine.repository.interfaces.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ComposantRepositoryImpl implements Repository<Composants> {
    Connection connection;

    public ComposantRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Composants add(Composants entity) throws SQLException {
        return null;
    }

    @Override
    public List<Composants> findAll() throws SQLException {
        return null;
    }

    @Override
    public Composants update(Composants composants) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Optional<Composants> findById(int id) throws SQLException {
        return Optional.empty();
    }
    public boolean updateTva(int id, Double tauxTva) throws SQLException {
        String sql = "UPDATE composants SET taux_tva = ? WHERE projet_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setDouble(1, tauxTva);
            pstmt.setInt(2, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating composant failed, no rows affected.");
            }
        }
        return true;
    }
}
