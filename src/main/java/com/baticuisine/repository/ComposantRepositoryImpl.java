package main.java.com.baticuisine.repository;

import main.java.com.baticuisine.model.Composants;
import main.java.com.baticuisine.repository.interfaces.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ComposantRepositoryImpl implements Repository<Composants> {
    @Override
    public Composants add(Composants entity) throws SQLException {
        return null;
    }

    @Override
    public List<Composants> findAll() throws SQLException {
        return null;
    }

    @Override
    public Composants update(Composants entity) throws SQLException {
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
}
