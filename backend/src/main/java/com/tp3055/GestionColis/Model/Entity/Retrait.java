package com.tp3055.GestionColis.Model.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Retrait {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @OneToOne
    private Colis colis;
    
    @ManyToOne
    private User withrawValidator; // represente celui 

    @Column(name="date_arriver")
    private LocalDateTime arriveDate; // date d'arriver du colis

    @Column(name="date_retrait")
    private LocalDateTime withdrawDate; // date du retrait du colis


}
