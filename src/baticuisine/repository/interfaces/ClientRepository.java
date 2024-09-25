package baticuisine.repository.interfaces;

import baticuisine.model.Client;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client addClient(Client client) throws SQLException;
    List<Client> findAll() throws SQLException;
    Optional<Client> findByName(String name) throws SQLException;
    Optional<Client> findByID(int id) throws SQLException;
    Client updateClient(Client client) throws SQLException;
    boolean deleteClient(int id) throws SQLException;
    boolean clienExist(String name) throws SQLException;
    Optional<Client> findById(int id) throws SQLException;
}
