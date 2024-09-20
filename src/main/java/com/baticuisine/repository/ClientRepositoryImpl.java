package main.java.com.baticuisine.repository;

import main.java.com.baticuisine.model.Client;
import main.java.com.baticuisine.repository.interfaces.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {

    Connection connection ;

    public ClientRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addClient(Client client) throws SQLException {
        String query= "INSERT INTO clients(nom,telephone,adresse,est_rofessional) VALUES (?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getTelephone());
            preparedStatement.setString(3, client.getAdresse());
            preparedStatement.setBoolean (4, client.is_professional());
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet= preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Client client =new Client(
                        resultSet.getString("nom"),
                        resultSet.getString("telephone"),
                        resultSet.getString("adresse"),
                        resultSet.getBoolean("est_professional")
                );

                client.setId(resultSet.getInt("id"));
                clients.add(client);
            }
        }
        return clients;
    }

    @Override
    public Optional<Client> findByName(String name) throws SQLException {
        String query = "SELECT * FROM clients WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, name);
            try (ResultSet resultSet= preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                    Client client =new Client(
                            resultSet.getString("name"),
                            resultSet.getString("phone"),
                            resultSet.getString("address"),
                            resultSet.getBoolean("isProfessional")
                    );
                    client.setId(resultSet.getInt("id"));
                    return Optional.of(client);
                } else {
                    return Optional.empty();
                }
            }



        }
    }

    @Override
    public boolean clienExist(String name) throws SQLException {
        String query= "SELECT * FROM clients WHERE nom = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                return resultSet.next();
            }
        }
    }

    @Override
    public boolean updateClient(Client client, String originalName) throws SQLException {
        String query = "UPDATE clients SET nom = ?, telephone = ?, adresse = ?, est_professional = ? WHERE nom = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2,client.getTelephone());
            preparedStatement.setString(3,client.getAdresse());
            preparedStatement.setBoolean(4, client.is_professional());
            preparedStatement.setString(5,originalName);
            int rowsUpdated= preparedStatement.executeUpdate();

            return rowsUpdated >0;
        }
    }

    @Override
    public boolean deleteClient(String name) throws SQLException {
        String query = "DELETE FROM clients WHERE nom =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

}
