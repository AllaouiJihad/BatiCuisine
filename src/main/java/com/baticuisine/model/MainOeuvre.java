package main.java.com.baticuisine.model;

import java.math.BigDecimal;

public class MainOeuvre extends Composants{
    private Double tauxHoraire;
    private Double heuresTravail;
    private Double productiviteOuvrier;

    public MainOeuvre(String nom, String typeComposant, Double tauxTva, Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier) {
        super( nom, typeComposant, tauxTva);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public Double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(Double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public Double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(Double heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public Double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(Double productiviteOuvrier) {
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
