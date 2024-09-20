package main.java.com.baticuisine.model;

import main.java.com.baticuisine.model.enums.EtatProjet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Projet {
    private Long id;
    private String nomProjet;
    private BigDecimal margeBeneficiaire;
    private BigDecimal coutTotal;
    private EtatProjet etatProjet;
    private Client client;
    private List<Composants> composants = new ArrayList<>();
    private Devis devis;

    public Projet(Long id, String nomProjet, BigDecimal margeBeneficiaire, BigDecimal coutTotal, EtatProjet etatProjet, Client client, List<Composants> composants, Devis devis) {
        this.id = id;
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.client = client;
        this.composants = composants;
        this.devis = devis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public BigDecimal getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(BigDecimal margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public BigDecimal getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(BigDecimal coutTotal) {
        this.coutTotal = coutTotal;
    }

    public EtatProjet getEtatProjet() {
        return etatProjet;
    }

    public void setEtatProjet(EtatProjet etatProjet) {
        this.etatProjet = etatProjet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Composants> getComposants() {
        return composants;
    }

    public void setComposants(List<Composants> composants) {
        this.composants = composants;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }
}
