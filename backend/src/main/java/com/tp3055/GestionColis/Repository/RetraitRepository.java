package com.tp3055.GestionColis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp3055.GestionColis.Model.Entity.Retrait;

public interface RetraitRepository extends JpaRepository<Retrait,Long>{

    @Query("SELECT r from Retrait r INNER JOIN Colis c on r.colis.id = c.id and c.id = ?1")
    Retrait findByColisId(Long colisId);
    

}
