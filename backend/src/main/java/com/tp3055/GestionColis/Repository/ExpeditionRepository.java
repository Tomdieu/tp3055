package com.tp3055.GestionColis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tp3055.GestionColis.Model.Entity.Expedition;

public interface ExpeditionRepository extends JpaRepository<Expedition,Long>{
    
}
