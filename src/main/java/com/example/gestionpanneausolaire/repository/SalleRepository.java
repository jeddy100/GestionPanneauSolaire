package com.example.gestionpanneausolaire.repository;

import com.example.gestionpanneausolaire.model.Panneau;
import com.example.gestionpanneausolaire.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.LogManager;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {

    @Query("select sal from Salle sal where sal.panneau= :panneau")
    List<Salle> getSalleByIdPanneau(@Param("panneau") Panneau panneau);
}
