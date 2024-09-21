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
        System.out.println("Début de la méthode addClient");

        // Vérification des paramètres
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du client ne peut pas être vide");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Le numéro de téléphone du client ne peut pas être vide");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("L'adresse du client ne peut pas être vide");
        }

        System.out.println("Création d'un nouvel objet Client");
        Client nouvClient = new Client(name, phone, address, isProfessional);
        System.out.println(nouvClient);
        System.out.println("Appel de la méthode addClient du repository");
        try {
            Client addedClient = clientRepository.addClient(nouvClient);
            System.out.println("Client ajouté avec succès");
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
    public Client updateClient(String originalName, String name, String phone, String address, boolean isProfessional) throws SQLException {
        Client client = new Client(name, phone, address, isProfessional);
        return clientRepository.updateClient(client);
    }

    public boolean deleteClient(String name) throws SQLException {
        return clientRepository.deleteClient(name);
    }

    public Optional<Client> findByname(String name) throws SQLException {
        return clientRepository.findByName(name);
    }

}
