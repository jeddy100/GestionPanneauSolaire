package com.example.gestionpanneausolaire.repository;

import com.example.gestionpanneausolaire.model.DonneesPanneau;
import com.example.gestionpanneausolaire.model.Panneau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanneauRepository extends JpaRepository<Panneau,Long> {

}
