package main.java.com.baticuisine.repository.interfaces;

import main.java.com.baticuisine.model.Projet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProjetRepository {
    Projet addProjet(Projet projet) throws SQLException;
    List<Projet> findAll() throws SQLException;
    Projet updateProjet(Projet projet) throws SQLException;
    boolean deleteProjet(int id) throws SQLException;
    Optional<Projet> findById(int id) throws SQLException;
}
