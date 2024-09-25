package baticuisine.repository;

import baticuisine.model.Devis;
import baticuisine.model.Projet;
import baticuisine.repository.interfaces.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DevisRepositoryImpl implements Repository<Devis> {
    Connection connection;

    public DevisRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Devis add(Devis devis) throws SQLException {
        String sql = "INSERT INTO devis (montant_estime, date_emission, date_validite, accepte, projet_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            duplicate(devis, pstmt);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating devis failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    devis.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating devis failed, no ID obtained.");
                }
            }
        }
        return devis;
    }

    private void duplicate(Devis devis, PreparedStatement pstmt) throws SQLException {
        pstmt.setDouble(1, devis.getMontantEstime());
        pstmt.setDate(2, new java.sql.Date(devis.getDateEmission().getTime()));
        pstmt.setDate(3, new java.sql.Date(devis.getDateValidite().getTime()));
        pstmt.setBoolean(4, devis.isAccepte());
        pstmt.setLong(5, devis.getProjet().getId());
    }

    @Override
    public List<Devis> findAll() throws SQLException {
        List<Devis> devisList = new ArrayList<>();
        String sql = "SELECT d.*, p.nom_projet as projet_nom FROM devis d LEFT JOIN projets p ON d.projet_id = p.id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                devisList.add(extractDevisFromResultSet(rs));
            }
        }
        return devisList;
    }

    @Override
    public Devis update(Devis devis) throws SQLException {
        String sql = "UPDATE devis SET montant_estime = ?, date_emission = ?, date_validite = ?, accepte = ?, projet_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            duplicate(devis, pstmt);
            pstmt.setLong(6, devis.getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating devis failed, no rows affected.");
            }
        }
        return devis;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM devis WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public Optional<Devis> findById(int id) throws SQLException {
        String sql = "SELECT d.*, p.nom_projet as projet_nom FROM devis d LEFT JOIN projets p ON d.projet_id = p.id WHERE d.id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractDevisFromResultSet(rs));
                }
            }
        }
        return Optional.empty();
    }

    private Devis extractDevisFromResultSet(ResultSet rs) throws SQLException {

        Optional<Projet> projet = new ProjetRepositoryImpl(connection).findById(rs.getInt("projet_id"));

        Devis devis = new Devis(
                rs.getDouble("montant_estime"),
                rs.getDate("date_emission"),
                rs.getDate("date_validite"),
                rs.getBoolean("accepte")

        );

        devis.setProjet(projet.orElse(null));

        return devis;

    }
}
