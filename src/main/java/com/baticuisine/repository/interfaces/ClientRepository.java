package main.java.com.baticuisine.repository.interfaces;

import main.java.com.baticuisine.model.Client;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client addClient(Client client) throws SQLException;
    List<Client> findAll() throws SQLException;
    Optional<Client> findByName(String name) throws SQLException;
    Client updateClient(Client client) throws SQLException;
    boolean deleteClient(String name) throws SQLException;
    boolean clienExist(String name) throws SQLException;
    Optional<Client> findById(int id) throws SQLException;
}
