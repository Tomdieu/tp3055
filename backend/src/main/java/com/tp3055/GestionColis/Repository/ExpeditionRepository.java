package com.tp3055.GestionColis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp3055.GestionColis.Model.Entity.Expedition;

public interface ExpeditionRepository extends JpaRepository<Expedition,Long>{

    @Query("SELECT e FROM Expedition e INNER JOIN Colis c on c.id = e.Colis.id AND c.id = ?1")
    Expedition findColisById(Long colisId);
    
}
