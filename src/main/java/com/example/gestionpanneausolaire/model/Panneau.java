package com.example.gestionpanneausolaire.model;

import com.example.gestionpanneausolaire.repository.EleveRepository;
import com.example.gestionpanneausolaire.repository.SalleRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Panneau {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private double capaciteMax;
    private double capaciteBatterie;
    private String nomPanneau;

    public Panneau(Long id, double capaciteMax, double capaciteBatterie, String nomPanneau) {
        this.id = id;
        this.capaciteMax = capaciteMax;
        this.capaciteBatterie = capaciteBatterie;
        this.nomPanneau = nomPanneau;
    }

    public Panneau(double capaciteMax, double capaciteBatterie, String nomPanneau) {
        this.capaciteMax = capaciteMax;
        this.capaciteBatterie = capaciteBatterie;
        this.nomPanneau = nomPanneau;
    }
    public Panneau(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(double capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public double getCapaciteBatterie() {
        return capaciteBatterie;
    }

    public void setCapaciteBatterie(double capaciteBatterie) {
        this.capaciteBatterie = capaciteBatterie;
    }

    public String getNomPanneau() {
        return nomPanneau;
    }

    public void setNomPanneau(String nomPanneau) {
        this.nomPanneau = nomPanneau;
    }

    /////// ///////////



//    public List<Salle> getSalleParDate(Luminosite luminosite,EleveRepository eleveRepository,SalleRepository salleRepository){
//        Salle salle=salleRepository.getSalleByIdPanneau(this.getId());
//        Eleve eleve=eleveRepository.getEleveByidSalle(salle.getId());
//        if(eleve.getDate())
//    }




}
