package com.tp3055.GestionColis.Model.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Retrait {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @OneToOne
    private Colis colis;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mark_arrive_by")
    private User arriveBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "withdraw_by")
    private User withrawBy; // represente celui 

    @Column(name="date_arriver")
    private LocalDateTime arriveDate; // date d'arriver du colis

    @Column(name="date_retrait")
    private LocalDateTime withdrawDate; // date du retrait du colis

}
