package com.example.gestionpanneausolaire.repository;

import com.example.gestionpanneausolaire.model.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EleveRepository extends JpaRepository<Eleve,Long> {
    @Query("select e from Eleve e where e.salle.id =: salle")
    Eleve getEleveByidSalle(@Param("salle") Long salle);

    @Query("select e from Eleve e where e.date =:dat and e.salle.panneau.id =:idPanneau")
    List<Eleve> getEleveByDate(@Param("dat") LocalDate dat, @Param("idPanneau") Long idPanneau);


//    @Query("select e from Eleve e where e.date =: luminosite")
//    List<Eleve> getEleveByLuminosite(@Param("lum")Luminosite luminosite);

    @Query(value = "select ROUND(AVG(nombre_eleve), 0) as nombre_eleves, heure_debut, heure_fin,id_salle from eleve join salle s on s.id = eleve.id_salle join panneau s2 on s2.id = s.id_panneau   where extract(dow from date)=extract (DOW from CAST(?1 AS DATE)) and id_panneau=?2  group by heure_debut, heure_fin, id_salle", nativeQuery = true)
    List<Object[]> getPointageByDatePointage(@Param("datePointage") LocalDate datePointage, @Param("idsecteur") Long idsecteur);





}
