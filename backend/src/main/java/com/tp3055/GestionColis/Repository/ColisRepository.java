package com.tp3055.GestionColis.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp3055.GestionColis.Model.Entity.*;

public interface ColisRepository extends JpaRepository<Colis, Long> {

    Optional<Colis> findByExpedtionCode(String expedtionCode);

    @Query("SELECT c FROM Colis c WHERE c.fromTown = ?1 or c.toTown = ?1")
    List<Colis> findColisOfAgence(String town);

    @Query("SELECT c FROM Colis c WHERE c.state='ARRIVER' and c.fromTown = ?1 or c.toTown = ?1")
    List<Colis> findColisArriveSend(String town);

    @Query("SELECT c FROM Colis c WHERE c.state='RETIRER' and c.fromTown = ?1 or c.toTown = ?1")
    List<Colis> findColisRemoveFromTown(String town);

    @Query("SELECT c FROM Colis c WHERE c.state = 'EXPEDIE' and c.fromTown = ?1 or c.toTown = ?1")
    List<Colis> findColisSendFromTown(String town);

    @Query("SELECT c FROM Colis c WHERE c.toTown = ?1 and c.fromTown = ?2")
    List<Colis> findColisSendBetweenTown(String town1, String town2);

    @Query("SELECT c FROM Colis c WHERE c.expedtionCode = ?1")
    Colis findColisByExpedtionCode(String code);

    @Query("SELECT c FROM Colis c WHERE c.state = ?1 and c.fromTown = ?2 or c.toTown= ?2")
    List<Colis> findColisByState(String state, String town);

    @Query("SELECT c FROM Colis c WHERE c.state = 'PENDING' and c.fromTown = ?1 or c.toTown = ?1")
    List<Colis> listPendingFromTown(String town);

    @Query("SELECT c FROM Colis c WHERE c.saveDate BETWEEN ?1 AND ?2")
    List<Colis> listColisBetweenDate(Date date1, Date date2);

    @Query("SELECT c FROM Colis c WHERE c.state = ?3 and c.saveDate BETWEEN ?1 AND ?2 ")
    List<Colis> listColisBetweenDate(Date date1, Date date2, State state);

    @Query("SELECT c FROM Colis c WHERE c.fromTown = ?1 AND c.saveDate BETWEEN ?2 AND ?3 ")
    List<Colis> listColisBetweenDate(String town,Date date1, Date date2);

    @Query("SELECT c FROM Colis c WHERE c.fromTown = ?1 AND c.state = ?4 and c.saveDate BETWEEN ?2 AND ?3 ")
    List<Colis> listColisBetweenDate(String town,Date date1, Date date2, State state);

}
