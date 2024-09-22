package main.java.com.baticuisine.repository;

import main.java.com.baticuisine.model.MainOeuvre;
import main.java.com.baticuisine.model.Projet;
import main.java.com.baticuisine.repository.interfaces.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainOeuvreRepositoryImpl implements Repository<MainOeuvre> {

    Connection connection;

    public MainOeuvreRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public MainOeuvre add(MainOeuvre mainOeuvre) throws SQLException {
        String sql = "INSERT INTO mainoeuvres (nom, type_composant, taux_tva, taux_horaire, heures_travail, productivite_ouvrier, projet_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, mainOeuvre.getNom());
            pstmt.setString(2, mainOeuvre.getTypeComposant());
            pstmt.setDouble(3, mainOeuvre.getTauxTva());
            pstmt.setDouble(4, mainOeuvre.getTauxHoraire());
            pstmt.setDouble(5, mainOeuvre.getHeuresTravail());
            pstmt.setDouble(6, mainOeuvre.getProductiviteOuvrier());
            pstmt.setInt(7, mainOeuvre.getProjet().getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating main d'oeuvre failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    mainOeuvre.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating main d'oeuvre failed, no ID obtained.");
                }
            }
        }
        return mainOeuvre;
    }

    @Override
    public List<MainOeuvre> findAll() throws SQLException {

        List<MainOeuvre> mainOeuvres = new ArrayList<>();
        String sql = "SELECT m.*, p.id as projet_id, p.nom_projet as projet_nom FROM mainoeuvres m LEFT JOIN projets p ON m.projet_id = p.id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                mainOeuvres.add(extractMainOeuvreFromResultSet(rs));
            }
        }
        return mainOeuvres;

    }

    @Override
    public MainOeuvre update(MainOeuvre mainOeuvre) throws SQLException {
        String sql = "UPDATE mainoeuvres SET nom = ?, type_composant = ?, taux_tva = ?, taux_horaire = ?, heures_travail = ?, productivite_ouvrier = ?, projet_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, mainOeuvre.getNom());
            pstmt.setString(2, mainOeuvre.getTypeComposant());
            pstmt.setDouble(3, mainOeuvre.getTauxTva());
            pstmt.setDouble(4, mainOeuvre.getTauxHoraire());
            pstmt.setDouble(5, mainOeuvre.getHeuresTravail());
            pstmt.setDouble(6, mainOeuvre.getProductiviteOuvrier());
            pstmt.setInt(7, mainOeuvre.getProjet().getId());
            pstmt.setInt(8, mainOeuvre.getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating main d'oeuvre failed, no rows affected.");
            }
        }
        return mainOeuvre;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM main_oeuvre WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public Optional<MainOeuvre> findById(int id) throws SQLException {
        String sql = "SELECT m.*, p.id as projet_id, p.nom_projet as projet_nom FROM mainoeuvres m LEFT JOIN projets p ON m.projet_id = p.id WHERE m.id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractMainOeuvreFromResultSet(rs));
                }
            }
        }
        return Optional.empty();
    }

    private MainOeuvre extractMainOeuvreFromResultSet(ResultSet rs) throws SQLException {
        Projet projet = new Projet(rs.getString("projet_nom"));
        projet.setId(rs.getInt("projet_id"));

        MainOeuvre mainOeuvre = new MainOeuvre(
                rs.getString("nom"),
                rs.getString("typeComposant"),
                rs.getDouble("tauxTva"),
                projet,
                rs.getDouble("tauxHoraire"),
                rs.getDouble("heuresTravail"),
                rs.getDouble("productiviteOuvrier")
        );
        mainOeuvre.setId(rs.getInt("id"));
        return mainOeuvre;
    }
}
