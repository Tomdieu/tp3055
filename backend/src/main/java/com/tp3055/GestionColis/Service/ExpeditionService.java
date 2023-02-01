package com.tp3055.GestionColis.Service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.tp3055.GestionColis.Model.Entity.Colis;
import com.tp3055.GestionColis.Model.Entity.Expedition;
import com.tp3055.GestionColis.Model.Entity.State;
import com.tp3055.GestionColis.Model.Entity.User;
import com.tp3055.GestionColis.Repository.ColisRepository;
import com.tp3055.GestionColis.Repository.ExpeditionRepository;

@Service
public class ExpeditionService {
    public ExpeditionRepository expeditionRepository;

    public ColisRepository colisRepository;

    public Expedition save(Colis colis, User sender) {
        Expedition exp = Expedition.builder().senderValidator(sender).Colis(colis).date(new Date()).build();
        return expeditionRepository.save(exp);
    }

    public Expedition sendColis(Long colisId) {
        Colis colis = colisRepository.findById(colisId).orElse(null);
        if (colis == null) {
            throw new IllegalStateException("Le colis que vous lez envoyer n'existe pas!");
        }
        colis.setState(State.EXPEDIE);

        Expedition exp = expeditionRepository.findColisById(colisId);
        exp.setDate(new Date());

        colisRepository.save(colis);

        return expeditionRepository.save(exp);
    }
}
