package main.java.com.baticuisine.repository;

import main.java.com.baticuisine.model.Client;
import main.java.com.baticuisine.repository.interfaces.ClientRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {

    Connection connection ;

    public ClientRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Client addClient(Client client) throws SQLException {
        String sql= "INSERT INTO clients(nom,telephone,adresse,est_professionnel) VALUES (?,?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, client.getName());
            pstmt.setString(2, client.getName());
            pstmt.setString(3, client.getAdresse());
            pstmt.setBoolean(4, client.is_professional());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("L'ajout du client a échoué, aucune ligne affectée.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("L'ajout du client a échoué, aucun ID obtenu.");
                }
            }

            return client;
        } catch (SQLException e) {
            System.out.println("Erreur SQL dans le repository : " + e.getMessage());
            throw e;
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
    public Optional<Client> findById(int id) throws SQLException {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Client client = mapResultSetToClient(rs);
                return Optional.of(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Client updateClient(Client client) throws SQLException {
        String query = "UPDATE clients SET nom = ?, telephone = ?, adresse = ?, est_professional = ? WHERE nom = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getAdresse());
            statement.setString(3, client.getTelephone());
            statement.setBoolean(4, client.is_professional());
            statement.setLong(5, client.getId());
            statement.executeUpdate();
            return client;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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

    private Client mapResultSetToClient(ResultSet rs) throws SQLException {
        Client client = new Client(
                rs.getString("nom"),
                rs.getString("adresse"),
                rs.getString("telephone"),
                rs.getBoolean("est_professionnel")
        );
        client.setId(rs.getInt("id"));
        return client;
    }

}
