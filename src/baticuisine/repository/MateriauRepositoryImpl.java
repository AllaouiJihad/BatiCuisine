package baticuisine.repository;

import baticuisine.model.Materiau;
import baticuisine.model.Projet;
import baticuisine.repository.interfaces.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MateriauRepositoryImpl implements Repository<Materiau> {
    Connection connection;

    public MateriauRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Materiau add(Materiau materiau) throws SQLException {
        String sql = "INSERT INTO materiaux (nom, type_composant, taux_tva, cout_unitaire, quantite, cout_transport, coefficient_qualite, projet_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, materiau.getNom());
            pstmt.setString(2, materiau.getTypeComposant());
            pstmt.setDouble(3, materiau.getTauxTva());
            pstmt.setDouble(4, materiau.getCoutUnitaire());
            pstmt.setDouble(5, materiau.getQuantite());
            pstmt.setDouble(6, materiau.getCoutTransport());
            pstmt.setDouble(7, materiau.getCoefficientQualite());
            pstmt.setInt(8, materiau.getProjet().getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating materiau failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    materiau.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating materiau failed, no ID obtained.");
                }
            }
        }
        return materiau;
    }

    @Override
    public List<Materiau> findAll() throws SQLException {
        List<Materiau> materiaux = new ArrayList<>();
        String sql = "SELECT m.*, p.id as projet_id, p.nom_projet as projet_nom FROM materiaux m LEFT JOIN projets p ON m.projet_id = p.id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                materiaux.add(extractMateriauFromResultSet(rs));
            }
        }
        return materiaux;
    }

    @Override
    public Materiau update(Materiau materiau) throws SQLException {
        String sql = "UPDATE materiaux SET nom = ?, type_Composant = ?, taux_tva = ?, cout_unitaire = ?, quantite = ?, cout_transport = ?, coefficient_qualite = ?, projet_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, materiau.getNom());
            pstmt.setString(2, materiau.getTypeComposant());
            pstmt.setDouble(3, materiau.getTauxTva());
            pstmt.setDouble(4, materiau.getCoutUnitaire());
            pstmt.setDouble(5, materiau.getQuantite());
            pstmt.setDouble(6, materiau.getCoutTransport());
            pstmt.setDouble(7, materiau.getCoefficientQualite());
            pstmt.setInt(8, materiau.getProjet().getId());
            pstmt.setInt(9, materiau.getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating materiau failed, no rows affected.");
            }
        }
        return materiau;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM materiaux WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public Optional<Materiau> findById(int id) throws SQLException {
        String sql = "SELECT m.*, p.id as projet_id, p.nom_projet as projet_nom FROM materiaux m LEFT JOIN projets p ON m.projet_id = p.id WHERE m.id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractMateriauFromResultSet(rs));
                }
            }
        }
        return Optional.empty();
    }

    private Materiau extractMateriauFromResultSet(ResultSet rs) throws SQLException {
        Projet projet = new Projet(rs.getString("projet_nom"));
        projet.setId(rs.getInt("projet_id"));

        Materiau materiau = new Materiau(
                rs.getString("nom"),
                rs.getString("typeComposant"),
                rs.getDouble("tauxTva"),
                rs.getDouble("coutUnitaire"),
                rs.getDouble("quantite"),
                rs.getDouble("coutTransport"),
                rs.getDouble("coefficientQualite")
        );
        materiau.setId(rs.getInt("id"));
        return materiau;
    }
}
