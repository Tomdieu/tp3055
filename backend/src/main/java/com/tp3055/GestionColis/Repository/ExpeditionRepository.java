package com.tp3055.GestionColis.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp3055.GestionColis.Model.Entity.Expedition;

public interface ExpeditionRepository extends JpaRepository<Expedition,Long>{

    @Query("SELECT e FROM Expedition e INNER JOIN Colis c on c.id = e.Colis.id AND c.id = ?1")
    Expedition findColisById(Long colisId);
    
    @Query("SELECT e FROM Expedition e INNER JOIN Colis c on e.Colis.id = c.id and c.fromTown = ?1 or c.toTown = ?1")
    List<Expedition> listColisByTown(String town);

    @Query("SELECT e FROM Expedition e INNER JOIN Colis c on e.Colis.id = c.id and c.state = 'ATTENTE' and c.fromTown = ?1")
    List<Expedition> listColisSaveByTown(String town);

    @Query("SELECT e FROM Expedition e INNER JOIN Colis c on e.Colis.id = c.id and c.state = 'EXPEDIE' and c.fromTown = ?1 or c.toTown = ?1")
    List<Expedition> listColisSendByTown(String town);
}
