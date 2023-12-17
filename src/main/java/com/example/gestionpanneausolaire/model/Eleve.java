package com.example.gestionpanneausolaire.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Eleve {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;

    private int nombreEleve;

    @ManyToOne
    @JoinColumn(name = "id_salle")
    private Salle salle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getNombreEleve() {
        return nombreEleve;
    }

    public void setNombreEleve(int nombreEleve) {
        this.nombreEleve = nombreEleve;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Eleve(Long id, LocalDate date, LocalTime heureDebut, LocalTime heureFin, int nombreEleve, Salle salle) {
        this.id = id;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.nombreEleve = nombreEleve;
        this.salle = salle;
    }

    public Eleve(LocalDate date, LocalTime heureDebut, LocalTime heureFin, int nombreEleve, Salle salle) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.nombreEleve = nombreEleve;
        this.salle = salle;
    }

    public Eleve() {

    }

}
