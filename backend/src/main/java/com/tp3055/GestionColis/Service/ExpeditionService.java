package com.tp3055.GestionColis.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp3055.GestionColis.Model.Entity.Colis;
import com.tp3055.GestionColis.Model.Entity.Expedition;
import com.tp3055.GestionColis.Model.Entity.State;
import com.tp3055.GestionColis.Model.Entity.User;
import com.tp3055.GestionColis.Repository.ColisRepository;
import com.tp3055.GestionColis.Repository.ExpeditionRepository;

@Service
public class ExpeditionService {

    @Autowired
    public ExpeditionRepository expeditionRepository;

    @Autowired
    public ColisRepository colisRepository;

    @Autowired
    public UserService userService;

    public Expedition getColisById(Long id){
        return expeditionRepository.findColisById(id);
    }

    public Expedition save(Colis colis, User sender) {
        Expedition exp = Expedition.builder().saveBy(sender).Colis(colis).date(new Date()).build();
        return expeditionRepository.save(exp);
    }

    public Expedition sendColis(Long colisId,Long userId) {
        Colis colis = colisRepository.findById(colisId).orElse(null);
        if (colis == null) {
            throw new IllegalStateException("Le colis que vous lez envoyer n'existe pas!");
        }
        User user = userService.findById(userId);
        if(user == null){
            throw new IllegalStateException("Le colis que vous lez envoyer n'existe pas!");
        }
        colis.setState(State.EXPEDIE);

        Expedition exp = expeditionRepository.findColisById(colisId);
        exp.setDate(new Date());

        colisRepository.save(colis);

        return expeditionRepository.save(exp);
    }

    public List<Expedition> getColisPending(String town) {
        return expeditionRepository.listColisSaveByTown(town);
    }

    public List<Expedition> getColisSend(String town) {
        return expeditionRepository.listColisSendByTown(town);
    }
}
