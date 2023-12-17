package com.example.gestionpanneausolaire.objet;

import com.example.gestionpanneausolaire.model.Salle;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Coupures {
    LocalDate date;
    List<Salle> listeSalle;
    List<DetailCoupure> detailCoupures;
    LocalTime heureCoupure;

    public Coupures(LocalDate date, List<Salle> listeSalle, List<DetailCoupure> detailCoupures, LocalTime heureCoupure) {
        this.date = date;
        this.listeSalle = listeSalle;
        this.detailCoupures = detailCoupures;
        this.heureCoupure = heureCoupure;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Salle> getListeSalle() {
        return listeSalle;
    }

    public void setListeSalle(List<Salle> listeSalle) {
        this.listeSalle = listeSalle;
    }

    public List<DetailCoupure> getDetailCoupures() {
        return detailCoupures;
    }

    public void setDetailCoupures(List<DetailCoupure> detailCoupures) {
        this.detailCoupures = detailCoupures;
    }

    public LocalTime getHeureCoupure() {
        return heureCoupure;
    }

    public void setHeureCoupure(LocalTime heureCoupure) {
        this.heureCoupure = heureCoupure;
    }
}
