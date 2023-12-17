package com.example.gestionpanneausolaire.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class DonneesPanneau {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_panneau")
    private Panneau panneau;

    private LocalDate date;
    private LocalTime heureCoupure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Panneau getPanneau() {
        return panneau;
    }

    public void setPanneau(Panneau panneau) {
        this.panneau = panneau;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeureCoupure() {
        return heureCoupure;
    }

    public void setHeureCoupure(LocalTime heureCoupure) {
        this.heureCoupure = heureCoupure;
    }

    public DonneesPanneau(Long id, Panneau panneau, LocalDate date, LocalTime heureCoupure) {
        this.id = id;
        this.panneau = panneau;
        this.date = date;
        this.heureCoupure = heureCoupure;
    }

    public DonneesPanneau(Panneau panneau, LocalDate date, LocalTime heureCoupure) {
        this.panneau = panneau;
        this.date = date;
        this.heureCoupure = heureCoupure;
    }

    public DonneesPanneau(){

    }
}
