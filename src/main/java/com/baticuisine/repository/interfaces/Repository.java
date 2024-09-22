package main.java.com.baticuisine.repository.interfaces;

import main.java.com.baticuisine.model.Projet;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    T add(T entity) throws SQLException;
    List<T> findAll() throws SQLException;
    T update(T entity) throws SQLException;
    boolean delete(int id) throws SQLException;
    Optional<T> findById(int id) throws SQLException;
}
