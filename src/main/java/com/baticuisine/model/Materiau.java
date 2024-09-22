package main.java.com.baticuisine.model;

import java.math.BigDecimal;

public class Materiau  extends Composants{
    private Double coutUnitaire;
    private Double quantite;
    private Double coutTransport;
    private Double coefficientQualite;

    public Materiau(String nom, String typeComposant, Double tauxTva, Projet projet, Double coutUnitaire, Double quantite, Double coutTransport, Double coefficientQualite) {
        super(nom, typeComposant, tauxTva, projet);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public Double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(Double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(Double coutTransport) {
        this.coutTransport = coutTransport;
    }

    public Double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(Double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    @Override
    public String toString() {
        return "Materiau{" +
                "coutUnitaire=" + coutUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", coefficientQualite=" + coefficientQualite +
                '}';
    }
}
