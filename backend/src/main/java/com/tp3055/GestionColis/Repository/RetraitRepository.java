package com.tp3055.GestionColis.Repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp3055.GestionColis.Model.Entity.Retrait;

public interface RetraitRepository extends JpaRepository<Retrait,Long>{

    @Query("SELECT r from Retrait r INNER JOIN Colis c on r.colis.id = c.id and c.id = ?1")
    Retrait findByColisId(Long colisId);
    
    @Query("SELECT r FROM Retrait r INNER JOIN Colis c on r.colis.id = c.id and c.fromTown = ?1 or c.toTown = ?1")
    List<Retrait> listColisByTown(String town);

    @Query("SELECT r FROM Retrait r INNER JOIN Colis c on r.colis.id = c.id and c.state = 'ATTENTE' and c.fromTown = ?1 or c.toTown = ?1")
    List<Retrait> listColisWithdrawByTown(String town);

    @Query("SELECT r FROM Retrait r INNER JOIN Colis c on r.colis.id = c.id and c.state = 'ARRIVER' and c.fromTown = ?1 or c.toTown = ?1")
    List<Retrait> listColisArriveByTown(String town);

}
