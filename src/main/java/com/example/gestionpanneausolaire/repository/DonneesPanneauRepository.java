package com.example.gestionpanneausolaire.repository;

import com.example.gestionpanneausolaire.model.DonneesPanneau;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DonneesPanneauRepository extends JpaRepository<DonneesPanneau,Long> {
    @Query("select e from DonneesPanneau e where e.date <:dat and e.panneau.id =:idPanneau")
    public List<DonneesPanneau>getDonneesPanneauByDat(@Param("dat") LocalDate dat, @Param("idPanneau") Long idPanneau);
}
