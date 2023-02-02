package com.tp3055.GestionColis.Service;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp3055.GestionColis.Model.Entity.Colis;
import com.tp3055.GestionColis.Model.Entity.Retrait;
import com.tp3055.GestionColis.Model.Entity.State;
import com.tp3055.GestionColis.Model.Entity.User;
import com.tp3055.GestionColis.Repository.ColisRepository;
import com.tp3055.GestionColis.Repository.RetraitRepository;
import com.tp3055.GestionColis.Repository.UserRepository;

@Service
public class RetraitService {
    
    @Autowired
    private RetraitRepository retraitRepository;
    @Autowired
    private ColisRepository colisRepository;

    @Autowired
    private UserRepository userRepository;

    public Retrait save(Colis colis,User user){
        Retrait retrait = new Retrait();
        retrait.setColis(colis);
        retrait.setArriveDate(LocalDateTime.now());
        retrait.setArriveBy(user);
        return retraitRepository.save(retrait);
    }

    public Retrait getRetraitById(Long id){
        return retraitRepository.findById(id).orElse(null);
    }

    public Retrait withdrawColis(Long colisId,Long userId){
        Optional<Colis> colis = colisRepository.findById(colisId);
        if(!colis.isPresent()){
            throw new IllegalStateException("Le colis n'existe pas");
        }
        Retrait retrait = retraitRepository.findByColisId(colisId);

        if(retrait == null){
            throw new IllegalStateException("Le colis n'existe pas");
        }

        Optional<User> user = userRepository.findById(userId);

        if(!user.isPresent()){
            throw new IllegalStateException("L'utilisateur n'existe pas");
        }
        Colis c = colis.get();
        c.setState(State.RETIRER);
        colisRepository.save(c);

        retrait.setWithdrawDate(LocalDateTime.now());

        return retraitRepository.save(retrait);

    }
}
