package com.tp3055.GestionColis.Model.Entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @Column(name = "is_admin")
    @Builder.Default
    private boolean isAdmin = false;
    
    @Column(name = "is_enreg")
    @Builder.Default
    private boolean isSaver = false;

    @Column(name="is_envoi")
    @Builder.Default
    private boolean isSender = false;

    public Profile orElse(Object object) {
        return null;
    }
}
