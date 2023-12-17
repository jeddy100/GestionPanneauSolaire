package com.example.gestionpanneausolaire.objet;

import com.example.gestionpanneausolaire.model.*;
import com.example.gestionpanneausolaire.repository.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Donneepanneau {
    private double Consommationeleve;
    private LocalDate datePrevision;
    private Panneau panneau;
    private List<Eleve> eleveList;
    private List<Luminosite> luminositeList;


    public LocalDate getDatePrevision() {
        return datePrevision;
    }

    public void setDatePrevision(LocalDate datePrevision) {
        this.datePrevision = datePrevision;
    }

    public Panneau getPanneau() {
        return panneau;
    }

    public void setPanneau(Panneau panneau) {
        this.panneau = panneau;
    }

    public List<Eleve> getEleveList() {
        return eleveList;
    }

    public void setEleveList(List<Eleve> eleveList) {
        this.eleveList = eleveList;
    }
    public void setEleveList(EleveRepository eleveRepository) throws Exception {
        if (getDatePrevision()==null){
            throw new Exception("date prevision peut pas etre null");
        }
        else {
//            System.out.println("date:"+getDatePrevision()+"  panneau id "+getPanneau().getId());
            setEleveList(eleveRepository.getEleveByDate(getDatePrevision(), getPanneau().getId()));
        }
    }

    public List<Luminosite> getLuminositeList() {
        return luminositeList;
    }

    public void setLuminositeList(List<Luminosite> luminositeList) {
        this.luminositeList = luminositeList;
    }
    public void setLuminositeList(LuminositeRepository luminositeRepository) throws Exception {
        if (getDatePrevision()==null){
            throw new Exception("date prevision peut pas etre null");
        }
        else {
            setLuminositeList(luminositeRepository.getLuminositeByDate(getDatePrevision()));
        }
    }

    public Donneepanneau(EleveRepository eleveRepository, SalleRepository salleRepository, PanneauRepository panneauRepository) {
    }



    public double getConsommationeleve() {
        return Consommationeleve;
    }

    public void setConsommationeleve(double consommationeleve) {
        Consommationeleve = consommationeleve;
    }

    public Donneepanneau(double consommationeleve, LocalDate datePrevision, Panneau panneau, List<Eleve> eleveList, List<Luminosite> luminositeList) {
        Consommationeleve = consommationeleve;
        this.datePrevision = datePrevision;
        this.panneau = panneau;
        this.eleveList = eleveList;
        this.luminositeList = luminositeList;
    }

    public Donneepanneau(LocalDate datePrevision, Panneau panneau, List<Eleve> eleveList, List<Luminosite> luminositeList) {
        this.setDatePrevision(datePrevision);
        this.setPanneau(panneau);
        this.setEleveList(eleveList);
        this.setLuminositeList(luminositeList);
    }
    public Donneepanneau(double consommationeleve,LocalDate datePrevision, Panneau panneau,EleveRepository eleveRepository, LuminositeRepository luminositeRepository) throws Exception {
        Consommationeleve = consommationeleve;
        this.setDatePrevision(datePrevision);
        this.setPanneau(panneau);
        this.setEleveList(eleveRepository);
        this.setLuminositeList(luminositeRepository.getLuminositeByDate(getDatePrevision()));
    }


    public Coupures getHeureCoupure(SalleRepository salleRepository){
        double batterie_2= getPanneau().getCapaciteBatterie()/2;
        double batterieinitial= getPanneau().getCapaciteBatterie()/2;

        double batterie=getPanneau().getCapaciteBatterie();
        double consommationTotale=0;
        double puissancePanneaux=0;
        boolean coupure = false;
        LocalTime heureCoupure= null;
        List<DetailCoupure> detailCoupures=new ArrayList<>();
        for (int i = 0; i < getLuminositeList().size(); i++) {
            double resteUtilisable=batterie - batterie_2;
//            for (int j = 0; j < eleveList.size(); j++) {
//                System.out.println(panneau.getNomPanneau() +" "+ eleveList.get(j).getNombreEleve());
//
//            }
//            System.out.println("somme des eleves:" +getSommeEleves( getLuminositeList().get(i)));
            consommationTotale= getConsommationeleve()*getSommeEleves(getLuminositeList().get(i));
            puissancePanneaux= getPanneau().getCapaciteMax()*((double) getLuminositeList().get(i).getNiveauLuminosite() /10);
            if(puissancePanneaux<consommationTotale){
                batterie-=(consommationTotale-puissancePanneaux);
            }
            /////recharge


            if(batterie<batterie_2 && puissancePanneaux<consommationTotale){
                int minutes =(int)( (puissancePanneaux+resteUtilisable)*60/consommationTotale);
                heureCoupure= Utils.addMinuteHour(getLuminositeList().get(i).getHeureDebut(),minutes);
                coupure=true;
                detailCoupures.add(new DetailCoupure(consommationTotale, getLuminositeList().get(i),puissancePanneaux,batterie_2,coupure));

                break;
            }
            detailCoupures.add(new DetailCoupure(consommationTotale,getLuminositeList().get(i),puissancePanneaux,batterie,coupure));
        }
        Coupures coupures= new Coupures(getDatePrevision(), getSalleFromData(salleRepository), detailCoupures, heureCoupure);
        return coupures;
    }

    public int getSommeEleves(Luminosite luminosite){
        List<Eleve> eleves= getPointageByLuminosite(luminosite);
        int somme = 0;
        for (int i = 0; i < eleves.size(); i++) {
            somme+=eleves.get(i).getNombreEleve();
        }
        return somme;}

    public List<Eleve> getPointageByLuminosite(Luminosite luminosite){
        List<Eleve>list=new ArrayList<>();
        for (int i = 0; i <eleveList.size() ; i++) {
//            System.out.println("nombre eleve ini:"+eleveList.get(i).getNombreEleve());
            if (luminosite.getHeureDebut().isAfter(eleveList.get(i).getHeureDebut()) && luminosite.getHeureFin().isBefore(eleveList.get(i).getHeureFin()) ){
//                System.out.println(luminosite.getHeureDebut()+" "+ luminosite.getHeureFin());
                list.add(eleveList.get(i));
                for (int j = 0; j < list.size(); j++) {
//                    System.out.println("nb eleve tafiditra"+list.get(j).getNombreEleve());

                }
            }
        }
        return  list;
    }

    public List<Salle> getSalleFromData(SalleRepository salleRepository){
        return salleRepository.getSalleByIdPanneau(panneau);
    }

    public double dichotomie2 ( Coupures coupuresPanneaux ,SalleRepository salleRepository){
        LocalTime localTime=coupuresPanneaux.getHeureCoupure();
        double intervale1= 0 ;
        double intervalle2 = 200;
        double milieu=0;
        System.out.println("Local "+ localTime);

        while ((intervalle2-intervale1) >= 1e-6){
            milieu= (intervale1+intervalle2)/2;

            this.setConsommationeleve(milieu);
            Coupures heureCoupure= this.getHeureCoupure(salleRepository);
//            System.out.println("milieu:"+ milieu+" heurecoupure:"+heureCoupure.getHeureCoupure() +" "+ intervale1+" "+intervalle2);

            if(heureCoupure.getHeureCoupure()==null){
//                continue;
                intervale1=milieu;
            }
            else{
                if(localTime.isAfter(heureCoupure.getHeureCoupure())){
                    intervalle2=milieu;
                }
                else if(localTime.isBefore(heureCoupure.getHeureCoupure())){
                    intervale1=milieu;
                }
                if(heureCoupure.getHeureCoupure().equals(localTime)){
                    return milieu;
                }

            }

        }
        return milieu;
    }

    public double dichotomie3 ( DonneesPanneau coupuresPanneaux){
        LocalTime localTime=coupuresPanneaux.getHeureCoupure();
        double intervale1= 0 ;
        double intervalle2 = 200;
        double milieu=0;
//        System.out.println("Local "+ localTime);

        while ((intervalle2-intervale1) >= 1e-6){
            milieu= (intervale1+intervalle2)/2;

            this.setConsommationeleve(milieu);
            LocalTime heureCoupure= this.getHeureCoupure();
//            System.out.println(milieu+" "+heureCoupure +" "+ intervale1+" "+intervalle2);

            if(heureCoupure==null){
//                continue;
                intervale1=milieu;
            }
            else{
                if(localTime.isAfter(heureCoupure)){
                    intervalle2=milieu;
                }
                else if(localTime.isBefore(heureCoupure)){
                    intervale1=milieu;
                }
                if(heureCoupure.equals(localTime)){
                    return milieu;
                }

            }

        }
        return milieu;
    }



    /////////////////////////   ////////////

    public LocalTime getHeureCoupure(){
        double batterie_2= getPanneau().getCapaciteBatterie()/2;
        double batterie=getPanneau().getCapaciteBatterie();
        double consommationTotale=0;
        double puissancePanneaux=0;
        boolean coupure = false;
        LocalTime heureCoupure= null;
        List<DetailCoupure> detailCoupures=new ArrayList<>();
        for (int i = 0; i < getLuminositeList().size(); i++) {
            double resteUtilisable=batterie - batterie_2;
//            for (int j = 0; j < eleveList.size(); j++) {
//                System.out.println(panneau.getNomPanneau() +" "+ eleveList.get(j).getNombreEleve());
//
//            }
//            System.out.println("somme des eleves:" +getSommeEleves( getLuminositeList().get(i)));
            consommationTotale= getConsommationeleve()*getSommeEleves(getLuminositeList().get(i));
            puissancePanneaux= getPanneau().getCapaciteMax()*((double) getLuminositeList().get(i).getNiveauLuminosite() /10);
            if(puissancePanneaux<consommationTotale){
                batterie-=(consommationTotale-puissancePanneaux);
            }

            if(batterie<batterie_2 && puissancePanneaux<consommationTotale){
                int minutes =(int)( (puissancePanneaux+resteUtilisable)*60/consommationTotale);
                heureCoupure= Utils.addMinuteHour(getLuminositeList().get(i).getHeureDebut(),minutes);
                coupure=true;

                break;
            }
        }
        return heureCoupure;
    }


    public static double moyenneConsommation (LocalDate datePrevisions, Panneau panneaux, DonneesPanneauRepository donneesPanneauRepository, EleveRepository pointageRepository, LuminositeRepository luminositeRepository) throws Exception {
        // alaina ny liste an'ny coupures panneaux rehetra
//        List<DonneesPanneau> coupuresPanneaux= coupuresPanneauxRepository.getCoupuresPanneauxBydate(datePrevisions,panneaux.getId());
        List<DonneesPanneau> coupuresPanneaux=donneesPanneauRepository.getDonneesPanneauByDat(datePrevisions,panneaux.getId());
        double sommeConsommation=0;
        int nombreDonne=0;
        List<Eleve> eleveList = pointageRepository.getEleveByDate(datePrevisions, panneaux.getId());
        List<Luminosite> luminositeList=luminositeRepository.findAll();
        // creer l'objet datapanneaux

        for (int i = 0; i < coupuresPanneaux.size(); i++) {
            Donneepanneau dataPanneaux= new Donneepanneau(coupuresPanneaux.get(i).getDate(),panneaux, eleveList,luminositeList);
            double conso=dataPanneaux.dichotomie3(coupuresPanneaux.get(i));
            sommeConsommation+=conso;
            nombreDonne++;
//            System.out.println("=> "+conso);

        }
        // calcul consommation par jours séléctionnée
        // calcul moyenne conso
        System.out.println("sommeconsomation:"+sommeConsommation +" nombreDonne: "+nombreDonne);
        return sommeConsommation/nombreDonne;

    }
    public static List<Eleve> getMoyenneNombreEleve(Panneau secteur,LocalDate dateChoisis, EleveRepository pointageRepository, SalleRepository salleRepository) throws Exception {
        List<Object[]> result = pointageRepository.getPointageByDatePointage(dateChoisis, secteur.getId());
        List<Eleve> pointages = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Optional<Salle> salle = salleRepository.findById((Long) result.get(i)[3]);
            if(salle.isPresent()){
                pointages.add(new Eleve(dateChoisis, Utils.convertToTime(String.valueOf(result.get(i)[1])), Utils.convertToTime(String.valueOf(result.get(i)[2])), Integer.parseInt( String.valueOf(result.get(i)[0])), salle.get()));
            }
            else {
                throw new Exception(" salle introuvable ");
            }
        }
        return pointages;
    }




}
