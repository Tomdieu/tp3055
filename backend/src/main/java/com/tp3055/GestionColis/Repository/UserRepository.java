package com.tp3055.GestionColis.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.security.core.userdetails.User;
import org.springframework.data.jpa.repository.Query;

import com.tp3055.GestionColis.Model.Entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    Optional<User> findByUsername(String username);

    List<User> findByTown(String town);

    @Query("SELECT u FROM User u WHERE u.town = ?1")
    List<User> findAllByTown(String town);
}
