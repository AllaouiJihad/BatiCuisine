package main.java.com.baticuisine.model;

public class Client {
    private int id;
    private String name;
    private String adresse;
    private String telephone;
    private boolean is_professional;

    public Client(int id, String name, String adresse, String telephone, boolean is_professional) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.telephone = telephone;
        this.is_professional = is_professional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isIs_professional() {
        return is_professional;
    }

    public void setIs_professional(boolean is_professional) {
        this.is_professional = is_professional;
    }
}
