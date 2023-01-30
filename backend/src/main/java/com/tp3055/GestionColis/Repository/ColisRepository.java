package com.tp3055.GestionColis.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp3055.GestionColis.Model.Entity.*;

public interface ColisRepository extends JpaRepository<Colis,Long>{
    
    Optional<Colis> findByExpedtionCode(String expedtionCode);

    @Query("SELECT c FROM Colis c WHERE c.fromTown = ?1")
	List<Colis> findColisSendFromTown(String town);

    @Query("SELECT c FROM Colis c WHERE c.toTown = ?1")
	List<Colis> findColisSendToTown(String town);

    @Query("SELECT c FROM Colis c WHERE c.toTown = ?1 and c.fromTown = ?2")
	List<Colis> findColisSendBetweenTown(String town1,String town2);

    @Query("SELECT c FROM Colis c WHERE c.expedtionCode = ?1")
    Colis findColisByExpedtionCode(String code);

    @Query("SELECT c FROM Colis c WHERE c.state = ?1")
    Colis findColisByState(String state);
}
