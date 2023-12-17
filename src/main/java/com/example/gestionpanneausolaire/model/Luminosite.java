package com.example.gestionpanneausolaire.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Luminosite {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;

    private double niveauLuminosite;

    public Luminosite(Long id, LocalDate date, LocalTime heureDebut, LocalTime heureFin, double niveauLuminosite) {
        this.id = id;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.niveauLuminosite = niveauLuminosite;
    }

    public Luminosite(LocalDate date, LocalTime heureDebut, LocalTime heureFin, double niveauLuminosite) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.niveauLuminosite = niveauLuminosite;
    }
    public Luminosite(){

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public double getNiveauLuminosite() {
        return niveauLuminosite;
    }

    public void setNiveauLuminosite(double niveauLuminosite) {
        this.niveauLuminosite = niveauLuminosite;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
