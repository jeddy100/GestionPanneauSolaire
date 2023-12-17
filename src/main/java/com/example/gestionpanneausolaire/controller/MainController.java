package com.example.gestionpanneausolaire.controller;

import com.example.gestionpanneausolaire.model.Eleve;
import com.example.gestionpanneausolaire.model.Luminosite;
import com.example.gestionpanneausolaire.model.Panneau;
import com.example.gestionpanneausolaire.objet.Coupures;
import com.example.gestionpanneausolaire.objet.Donneepanneau;
import com.example.gestionpanneausolaire.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@Controller

public class MainController {

    @Autowired
    EleveRepository eleveRepository;
    @Autowired
    SalleRepository salleRepository;
    @Autowired
    PanneauRepository panneauRepository;
    @Autowired
    LuminositeRepository luminositeRepository;
    @Autowired
    DonneesPanneauRepository donneesPanneauRepository;

    @GetMapping("/insertdate")
    public String home(){
        return "insertDate";
    }


    @PostMapping("/datepost")
    public String newPrevision(@RequestParam("dateprevision") LocalDate dateprevision, Model model) throws Exception {
        List<Panneau> panneauList=panneauRepository.findAll();
        Panneau panneau1=panneauList.get(0);
        double moyenneconsoeleve= Donneepanneau.moyenneConsommation(dateprevision,panneau1,donneesPanneauRepository,eleveRepository,luminositeRepository);
        List<Eleve>elevemoyenne=Donneepanneau.getMoyenneNombreEleve(panneau1,dateprevision,eleveRepository,salleRepository);


        System.out.println(panneau1.getNomPanneau());
        List<Eleve> eleveList = eleveRepository.getEleveByDate(dateprevision, panneau1.getId());
        List<Luminosite> luminositeList=luminositeRepository.findAll();

        Donneepanneau donneepanneau = new Donneepanneau(moyenneconsoeleve,dateprevision,panneau1,elevemoyenne,luminositeList);

        Coupures coupures=donneepanneau.getHeureCoupure(salleRepository);
        System.out.println("qwdkhgqwdiqwuhdiqw ="+coupures.getHeureCoupure());

        model.addAttribute("date",dateprevision);
        model.addAttribute("coupures",coupures.getHeureCoupure());
        model.addAttribute("donneepanneau",donneepanneau);
        return "prevision";
    }


}
