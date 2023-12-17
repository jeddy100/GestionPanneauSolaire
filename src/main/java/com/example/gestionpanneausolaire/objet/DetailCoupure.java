package com.example.gestionpanneausolaire.objet;

import com.example.gestionpanneausolaire.model.Luminosite;

public class DetailCoupure {
    double consommation ;
    Luminosite luminosite;
    double puissancePanneaux;
    double resteBatterie;
    boolean coupure;

    public double getConsommation() {
        return consommation;
    }

    public void setConsommation(double consommation) {
        this.consommation = consommation;
    }

    public Luminosite getLuminosite() {
        return luminosite;
    }

    public void setLuminosite(Luminosite luminosite) {
        this.luminosite = luminosite;
    }

    public double getPuissancePanneaux() {
        return puissancePanneaux;
    }

    public void setPuissancePanneaux(double puissancePanneaux) {
        this.puissancePanneaux = puissancePanneaux;
    }

    public double getResteBatterie() {
        return resteBatterie;
    }

    public void setResteBatterie(double resteBatterie) {
        this.resteBatterie = resteBatterie;
    }

    public boolean isCoupure() {
        return coupure;
    }

    public void setCoupure(boolean coupure) {
        this.coupure = coupure;
    }

    public DetailCoupure(double consommation, Luminosite luminosite, double puissancePanneaux, double resteBatterie, boolean coupure) {
        this.consommation = consommation;
        this.luminosite = luminosite;
        this.puissancePanneaux = puissancePanneaux;
        this.resteBatterie = resteBatterie;
        this.coupure = coupure;
    }
    public DetailCoupure(){

    }
}
