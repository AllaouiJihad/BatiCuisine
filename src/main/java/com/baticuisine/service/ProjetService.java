package main.java.com.baticuisine.service;

import main.java.com.baticuisine.config.DatabaseConnection;
import main.java.com.baticuisine.model.Composants;
import main.java.com.baticuisine.model.MainOeuvre;
import main.java.com.baticuisine.model.Materiau;
import main.java.com.baticuisine.model.Projet;
import main.java.com.baticuisine.model.enums.EtatProjet;
import main.java.com.baticuisine.repository.ClientRepositoryImpl;
import main.java.com.baticuisine.repository.ProjetRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProjetService {
    private Connection connection = DatabaseConnection.getInstance().getConnection();
    private ProjetRepositoryImpl projetRepository ;
    MateriauService materiauService=new MateriauService();
    MainOeuvreService mainOeuvreService=new MainOeuvreService();
    ClientService clientService = new ClientService();

    public ProjetService() {
        this.projetRepository = new ProjetRepositoryImpl(connection);
    }

    public Projet create(String nom , Double marge, int clinet_id) throws SQLException {
       Projet projet = new Projet(nom,marge,0.0, EtatProjet.ENCOURS);
       projet.setClient(clientService.findById(clinet_id).get());
        return projetRepository.addProjet(projet);
    }

    public Optional<Projet> getById(int id) throws SQLException {
        return projetRepository.findById(id);
    }

    public List<Projet> getAll() throws SQLException {
        return projetRepository.findAll();
    }

    public Projet update(Projet project) throws SQLException {
        return projetRepository.updateProjet(project);
    }

    public boolean delete(int id) throws SQLException {
        return projetRepository.deleteProjet(id);
    }

    public double calculateTotalCost(Projet project) {
        double TotalCost=0.0;
        double ComponentTotalCost=0.0;


        for (Composants component: project.getComposants()){
            if (component instanceof Materiau){
                 ComponentTotalCost += materiauService.calculateCost((Materiau) component);
            }else {
                ComponentTotalCost+= mainOeuvreService.calculateCost((MainOeuvre) component);
            }
        }

        double CoutMarge=(ComponentTotalCost* project.getMargeBeneficiaire())/100;

        TotalCost=ComponentTotalCost+CoutMarge;

        return TotalCost;
    }


}
