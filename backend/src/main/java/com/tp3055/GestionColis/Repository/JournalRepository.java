package com.tp3055.GestionColis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tp3055.GestionColis.Model.Entity.Journal;

public interface JournalRepository extends  JpaRepository<Journal,Long>{
    
}
