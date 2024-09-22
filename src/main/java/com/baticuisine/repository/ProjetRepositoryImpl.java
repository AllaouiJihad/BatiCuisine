package main.java.com.baticuisine.repository;

import main.java.com.baticuisine.model.Client;
import main.java.com.baticuisine.model.Projet;
import main.java.com.baticuisine.model.enums.EtatProjet;
import main.java.com.baticuisine.repository.interfaces.ProjetRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetRepositoryImpl implements ProjetRepository {
    Connection connection;

    public ProjetRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Projet addProjet(Projet projet) throws SQLException {
        String sql = "INSERT INTO projets (nom_projet, marge_beneficiaire, cout_total, etat_projet, client_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, projet.getNomProjet());
            statement.setDouble(2, projet.getMargeBeneficiaire());
            statement.setDouble(3, projet.getCoutTotal());
            statement.setString(4, projet.getEtatProjet().toString()); // Assuming `etatProjet` is stored as a String
            statement.setLong(5, projet.getClient().getId()); // Assuming `Client` has an `id` field

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                projet.setId(rs.getInt("id"));
            }
            return projet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Projet> findAll() throws SQLException {
        List<Projet> projets = new ArrayList<>();
        String sql = "SELECT * FROM project";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Projet projet = mapResultSetToProject(rs);
                projets.add(projet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projets;
    }

    @Override
    public Projet updateProjet(Projet projet) throws SQLException {
        String sql = "UPDATE project SET nom_projet = ?, marge_beneficiaire = ?, cout_total = ?, etat_projet = ?, client_id = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, projet.getNomProjet());
            statement.setDouble(2, projet.getMargeBeneficiaire());
            statement.setDouble(3, projet.getCoutTotal());
            statement.setString(4, projet.getEtatProjet().toString());
            statement.setLong(5, projet.getClient().getId());
            statement.setLong(6, projet.getId());
            statement.executeUpdate();
            return projet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteProjet(int id) throws SQLException {
        String sql = "DELETE FROM project WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            int rowsDeleted = statement.executeUpdate();;
            return rowsDeleted > 0;
        }

    }

    @Override
    public Optional<Projet> findById(int id) throws SQLException {
        String sql = "SELECT * FROM projets WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Projet project = mapResultSetToProject(rs);
                return Optional.of(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Projet mapResultSetToProject(ResultSet rs) throws SQLException {
        // Assuming Project has a ProjectStatus Enum, and client is loaded separately
        Projet projet = new Projet(
                rs.getString("nom_projet"),
                rs.getDouble("marge_beneficiaire"),
                rs.getDouble("cout_total"),
                EtatProjet.valueOf(rs.getString("etat_projet"))

        );
        projet.setId(rs.getInt("id"));
        int clientId = rs.getInt("client_id");
        Optional<Client> client = new ClientRepositoryImpl(connection).findById(clientId);
        client.ifPresent(projet::setClient);

        return projet;
    }

}
