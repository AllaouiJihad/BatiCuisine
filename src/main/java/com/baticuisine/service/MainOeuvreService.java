package main.java.com.baticuisine.service;

import main.java.com.baticuisine.config.DatabaseConnection;
import main.java.com.baticuisine.model.MainOeuvre;
import main.java.com.baticuisine.model.Projet;
import main.java.com.baticuisine.repository.MainOeuvreRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainOeuvreService {

    private Connection connection = DatabaseConnection.getInstance().getConnection();

    MainOeuvreRepositoryImpl mainOeuvreRepository ;

    public MainOeuvreService() {
        this.mainOeuvreRepository = new MainOeuvreRepositoryImpl(connection);
    }

    public MainOeuvre add(String name, Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier, Projet projet) throws SQLException {
        MainOeuvre mainOeuvre = new MainOeuvre(name,"MainOeuvre",0.0,tauxHoraire,heuresTravail,productiviteOuvrier);
        mainOeuvre.setProjet(projet);
        return mainOeuvreRepository.add(mainOeuvre);
    }

    public List<MainOeuvre> findAll() throws SQLException {
        return mainOeuvreRepository.findAll();
    }

    public MainOeuvre update(MainOeuvre mainOeuvre) throws SQLException {
        return mainOeuvreRepository.update(mainOeuvre);
    }

    public boolean delete(int id) throws SQLException {
        return mainOeuvreRepository.delete(id);
    }

    public Optional<MainOeuvre> findById(int id)throws SQLException{
        return mainOeuvreRepository.findById(id);
    }

    public double calculateCost(MainOeuvre mainOeuvre){
        double cost = mainOeuvre.getTauxHoraire() * mainOeuvre.getHeuresTravail() * mainOeuvre.getProductiviteOuvrier();
        return cost;
    }


    public double CostWTVA (MainOeuvre mainOeuvre){
        double costWithoutTax = mainOeuvre.getTauxHoraire() * mainOeuvre.getHeuresTravail() * mainOeuvre.getProductiviteOuvrier();
        return  costWithoutTax + (costWithoutTax * mainOeuvre.getTauxTva() / 100);
    }


}
