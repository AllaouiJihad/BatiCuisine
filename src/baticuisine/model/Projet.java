package baticuisine.model;

import baticuisine.model.enums.EtatProjet;

import java.util.ArrayList;
import java.util.List;

public class Projet {
    private int id;
    private String nomProjet;
    private Double margeBeneficiaire;
    private Double coutTotal;
    private EtatProjet etatProjet;
    private Client client;
    private List<Composants> composants = new ArrayList<>();
    private Devis devis;

    public Projet( String nomProjet, Double margeBeneficiaire, Double coutTotal, EtatProjet etatProjet) {
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
    }

    public Projet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public Double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(Double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public Double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(Double coutTotal) {
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

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nomProjet='" + nomProjet + '\'' +
                ", margeBeneficiaire=" + margeBeneficiaire +
                ", coutTotal=" + coutTotal +
                ", etatProjet=" + etatProjet +
                ", client=" + client +
                ", composants=" + composants +
                ", devis=" + devis +
                '}';
    }
}
