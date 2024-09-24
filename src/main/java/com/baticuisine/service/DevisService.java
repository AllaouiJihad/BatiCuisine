package main.java.com.baticuisine.service;

import main.java.com.baticuisine.config.DatabaseConnection;
import main.java.com.baticuisine.model.Devis;
import main.java.com.baticuisine.model.Projet;
import main.java.com.baticuisine.repository.DevisRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class DevisService {

    Connection connection = DatabaseConnection.getInstance().getConnection();

    DevisRepositoryImpl devisRepository;

    public DevisService() {
        this.devisRepository = new DevisRepositoryImpl(connection);
    }

    public Devis add(Date dateEmission, Date dateValidite, Double coutTotal , boolean accepte, Projet projet) throws SQLException{
        Devis devis = new Devis(coutTotal,dateEmission,dateValidite, accepte);
        devis.setProjet(projet);
        return devisRepository.add(devis);
    }

    public List<Devis> findAll()throws SQLException{
        return devisRepository.findAll();
    }

    public Devis update(Devis devis)throws SQLException{
        return devisRepository.update(devis);
    }

    public boolean delete(int id) throws SQLException{
        return devisRepository.delete(id);
    }

    public Optional<Devis> findById(int id)throws SQLException{
        return devisRepository.findById(id);
    }
}
