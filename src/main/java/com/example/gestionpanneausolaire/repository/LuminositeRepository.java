package com.example.gestionpanneausolaire.repository;

import com.example.gestionpanneausolaire.model.Luminosite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LuminositeRepository extends JpaRepository<Luminosite,Long> {
    @Query("select lum from Luminosite lum where lum.date = :dat ")
    List<Luminosite> getLuminositeByDate(@Param("dat")LocalDate dat);
}
