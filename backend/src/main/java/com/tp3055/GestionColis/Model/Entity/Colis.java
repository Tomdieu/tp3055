package com.tp3055.GestionColis.Model.Entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Colis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Long description;

    private String clientCNI;//CNI du client

    private String clientName; // nom du client

    private String recieverName; // nom du destinataire

    private String recieverPhone; // numero telephone du destinataire du colis

    @Column(name="from_town")
    private String fromTown; // de la ville

    @Column(name="to_town")
    private String toTown; // a la ville

    @Enumerated(EnumType.STRING)
    private State state; // etat du colis

    @Column(unique = true)
    private String expedtionCode;

    
}
