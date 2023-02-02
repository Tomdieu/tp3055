package com.tp3055.GestionColis.Model.Entity;

import java.util.Date;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
public class Expedition implements TypeMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Colis Colis;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "save_by")
    private User saveBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "send_by")
    private User sendBy;

    @Column(name = "save_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date savedOn;

    @Column(name = "send_date")

    private Date date;

}
