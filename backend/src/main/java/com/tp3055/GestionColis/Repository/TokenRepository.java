package com.tp3055.GestionColis.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tp3055.GestionColis.Model.Entity.Token;

public interface TokenRepository extends JpaRepository<Token,Long>{
    Optional<Token> findByKey(String key);
}
