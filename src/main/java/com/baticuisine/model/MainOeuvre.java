package main.java.com.baticuisine.model;

import java.math.BigDecimal;

public class MainOeuvre extends Composants{
    private BigDecimal tauxHoraire;
    private BigDecimal heuresTravail;
    private BigDecimal productiviteOuvrier;

    public MainOeuvre(Long id, String nom, String typeComposant, BigDecimal tauxTva, Projet projet, BigDecimal tauxHoraire, BigDecimal heuresTravail, BigDecimal productiviteOuvrier) {
        super(id, nom, typeComposant, tauxTva, projet);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public BigDecimal getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(BigDecimal tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public BigDecimal getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(BigDecimal heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public BigDecimal getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(BigDecimal productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }

    @Override
    public String toString() {
        return "MainOeuvre{" +
                "tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                '}';
    }
}
