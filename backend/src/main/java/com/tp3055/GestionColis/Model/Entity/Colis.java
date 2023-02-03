package com.tp3055.GestionColis.Model.Entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Colis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String description;

    private String clientCNI="";// CNI du client

    private String clientName=""; // nom du client

    @Column(nullable = false)
    private String recieverName; // nom du destinataire

    private String recieverPhone; // numero telephone du destinataire du colis

    @Column(name = "from_town",nullable=false)
    private String fromTown; // de la ville

    @Column(name = "to_town",nullable=false)
    private String toTown; // a la ville

    @Enumerated(EnumType.STRING)
    private State state = State.ATTENTE; // etat du colis

    @Column(unique = true)
    private String expedtionCode;

    @Column(name = "save_on")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date saveDate;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;
}
