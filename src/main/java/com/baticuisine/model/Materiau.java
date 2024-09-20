package main.java.com.baticuisine.model;

import java.math.BigDecimal;

public class Materiau  extends Composants{
    private BigDecimal coutUnitaire;
    private BigDecimal quantite;
    private BigDecimal coutTransport;
    private BigDecimal coefficientQualite;

    public Materiau(Long id, String nom, String typeComposant, BigDecimal tauxTva, Projet projet, BigDecimal coutUnitaire, BigDecimal quantite, BigDecimal coutTransport, BigDecimal coefficientQualite) {
        super(id, nom, typeComposant, tauxTva, projet);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public BigDecimal getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(BigDecimal coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(BigDecimal coutTransport) {
        this.coutTransport = coutTransport;
    }

    public BigDecimal getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(BigDecimal coefficientQualite) {
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
