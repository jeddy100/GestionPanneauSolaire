package com.example.gestionpanneausolaire.main;

import com.example.gestionpanneausolaire.model.DonneesPanneau;
import com.example.gestionpanneausolaire.model.Eleve;
import com.example.gestionpanneausolaire.model.Luminosite;
import com.example.gestionpanneausolaire.model.Panneau;
import com.example.gestionpanneausolaire.objet.Coupures;
import com.example.gestionpanneausolaire.objet.Donneepanneau;
import com.example.gestionpanneausolaire.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.text.Element;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class Main {
    @Bean
    CommandLineRunner commandLineRunner(SalleRepository salleRepository, PanneauRepository panneauRepository, EleveRepository eleveRepository, LuminositeRepository luminositeRepository,DonneesPanneauRepository donneesPanneauRepository){
        return args -> {
            double consommationEleve=70;
            LocalDate dateprevision= LocalDate.parse("2024-01-03");
            List<Panneau> panneauList=panneauRepository.findAll();
            Panneau panneau1=panneauList.get(0);
            double moyenneconsoeleve=Donneepanneau.moyenneConsommation(dateprevision,panneau1,donneesPanneauRepository,eleveRepository,luminositeRepository);
            List<Eleve>elevemoyenne=Donneepanneau.getMoyenneNombreEleve(panneau1,dateprevision,eleveRepository,salleRepository);


            System.out.println(panneau1.getNomPanneau());
            List<Eleve> eleveList = eleveRepository.getEleveByDate(dateprevision, panneau1.getId());
            List<Luminosite> luminositeList=luminositeRepository.findAll();

            Donneepanneau donneepanneau = new Donneepanneau(moyenneconsoeleve,dateprevision,panneau1,elevemoyenne,luminositeList);

            Coupures coupures=donneepanneau.getHeureCoupure(salleRepository);

            System.out.println("heure de coupure:"+coupures.getHeureCoupure());

//            double consoDicho=donneepanneau.dichotomie2(coupures,salleRepository);
//
//            System.out.println("consommation estimee:"+ consoDicho);

            System.out.println("moyenne:"+moyenneconsoeleve);

//            List<DonneesPanneau> donneesPanneauList=donneesPanneauRepository.findAll();
//            for (int i = 0; i <donneesPanneauList.size() ; i++) {
//                System.out.println("date:"+donneesPanneauList.get(i).getDate() +"  moyenne: "+Donneepanneau.moyenneConsommation(donneesPanneauList.get(i).getDate(),panneau1,donneesPanneauRepository,eleveRepository,luminositeRepository));
//`
//            }

//            LocalDate datetest= LocalDate.parse("2023-12-14");
//
//            List<Eleve>testdate=Donneepanneau.getMoyenneNombreEleve(panneau1,dateprevision,eleveRepository,salleRepository);
//            System.out.println(testdate.get(0).getNombreEleve());
        };
    }
}
