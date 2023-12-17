package com.example.gestionpanneausolaire.model;

import jakarta.persistence.*;

@Entity
public class Salle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_panneau")
    private Panneau panneau;

    private String nomSalle;

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

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public Salle(Panneau panneau, String nomSalle) {
        this.panneau = panneau;
        this.nomSalle = nomSalle;
    }

    public Salle(Long id, Panneau panneau, String nomSalle) {
        this.id = id;
        this.panneau = panneau;
        this.nomSalle = nomSalle;
    }
    public Salle(){

    }
}
