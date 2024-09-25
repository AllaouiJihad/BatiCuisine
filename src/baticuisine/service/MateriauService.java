package baticuisine.service;

import baticuisine.config.DatabaseConnection;
import baticuisine.model.Materiau;
import baticuisine.model.Projet;
import baticuisine.repository.MateriauRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MateriauService {
    private Connection connection = DatabaseConnection.getInstance().getConnection();

    private MateriauRepositoryImpl materiauRepository;

    public MateriauService(){
        this.materiauRepository = new MateriauRepositoryImpl(connection);
    }

    public Materiau add(String nom, Double coutUnitaire, Double qte, Double coutTransport, Double cQualite, Projet projet) throws SQLException {
        Materiau materiau = new Materiau(nom,"materiau",0.0,coutUnitaire,qte,coutTransport,cQualite);
        materiau.setProjet(projet);
        return materiauRepository.add(materiau);
    }
    public List<Materiau> findAll() throws SQLException {

        return materiauRepository.findAll();
    }

    public Materiau update(Materiau materiau) throws SQLException{
        return materiauRepository.update(materiau);
    }
    public Boolean delete(int id)throws SQLException{
        return materiauRepository.delete(id);
    }
    public Optional<Materiau> findById(int id)throws SQLException{
        return materiauRepository.findById(id);
    }


    public double calculateCost(Materiau material) {
        Double coutUnitaire=material.getCoutUnitaire();
        double costWithoutTax = (coutUnitaire * material.getQuantite() * material.getCoefficientQualite()) + material.getCoutTransport();
        return costWithoutTax + (costWithoutTax * material.getTauxTva() / 100);
    }


    public double CostWTVA(Materiau material){
        Double coutUnitaire=material.getCoutUnitaire();
        double costWithoutTax = (coutUnitaire * material.getQuantite() * material.getCoefficientQualite()) + material.getCoutTransport();
        return costWithoutTax;
    }

}
