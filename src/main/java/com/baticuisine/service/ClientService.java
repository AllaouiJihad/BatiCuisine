package main.java.com.baticuisine.service;

import main.java.com.baticuisine.config.DatabaseConnection;
import main.java.com.baticuisine.model.Client;
import main.java.com.baticuisine.repository.ClientRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClientService {
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private ClientRepositoryImpl clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepositoryImpl(connection);
    }
    public boolean clienExist(String name) throws SQLException {
        return clientRepository.clienExist(name);
    }
    public Client addClient(String name, String phone, String address, boolean isProfessional) throws SQLException {

        Client nouvClient = new Client(name, phone, address, isProfessional);
        try {
            Client addedClient = clientRepository.addClient(nouvClient);
            return addedClient;
        } catch (SQLException e) {
            System.out.println("Erreur SQL lors de l'ajout du client : " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println("Erreur inattendue lors de l'ajout du client : " + e.getMessage());
            throw new SQLException("Erreur lors de l'ajout du client", e);
        }
    }

    public List<Client> findAllClients() throws SQLException {
        return clientRepository.findAll();
    }
    public Client updateClient(Client client) throws SQLException {
        return clientRepository.updateClient(client);
    }

    public boolean deleteClient(int id) throws SQLException {
        return clientRepository.deleteClient(id);
    }

    public Optional<Client> findByname(String name) throws SQLException {
        return clientRepository.findByName(name);
    }

    public Optional<Client> findById(int id) throws SQLException {
        return clientRepository.findByID(id);
    }

}
