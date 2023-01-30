package com.tp3055.GestionColis.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp3055.GestionColis.Model.Entity.Profile;
import com.tp3055.GestionColis.Model.Entity.User;

public interface ProfileRepository extends JpaRepository<Profile,Long>{
    @Query("SELECT p FROM Profile p INNER JOIN User u on u.id = p.user.id WHERE p.user.id = ?1")
    Profile findProfileByUserId(Long id);

    Optional<Profile> findProfileByUser(User user);

    @Query("SELECT p FROM Profile p INNER JOIN User u on u.id = p.user.id WHERE p.user.town = ?1")
    List<Profile> findAllByTown(String town);
}
