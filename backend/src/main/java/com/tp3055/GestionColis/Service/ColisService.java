package com.tp3055.GestionColis.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp3055.GestionColis.Model.Entity.Colis;
import com.tp3055.GestionColis.Model.Entity.State;
import com.tp3055.GestionColis.Model.Entity.Profile;
import com.tp3055.GestionColis.Model.Entity.Retrait;
import com.tp3055.GestionColis.Model.Entity.Expedition;

import com.tp3055.GestionColis.Model.Entity.User;
import com.tp3055.GestionColis.Repository.ColisRepository;
import com.tp3055.GestionColis.Repository.UserRepository;

@Service
public class ColisService {
    @Autowired
    private ColisRepository colisRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpeditionService expeditionService;

    @Autowired
    private RetraitService retraitService;

    @Autowired
    private ProfileService profileService;

    public Optional<Colis> findByCode(String code) {
        return colisRepository.findByExpedtionCode(code);
    }

    public Colis saveColis(Colis colis) {
        return colisRepository.save(colis);
    }

    public List<Colis> listColis() {
        return colisRepository.findAll();
    }

    public List<Colis> listColis(String town) {
        return colisRepository.findColisOfAgence(town);
    }

    public List<Colis> listColisSeByTown(String town) {
        return colisRepository.findColisSendFromTown(town);
    }

    public List<Colis> listPendingColisFrom(String town) {
        return colisRepository.listPendingFromTown(town);
    }

    public List<Colis> listColisArrive(String town) {
        return colisRepository.findColisArriveSend(town);
    }

    public List<Colis> listColisRetirer(String town) {
        return colisRepository.findColisRemoveFromTown(town);
    }

    public List<Colis> listColisSendBetweenTown(String from_town, String to_town) {
        return colisRepository.findColisSendBetweenTown(from_town, to_town);
    }

    public Colis findByExpeditionCode(String code) {
        return colisRepository.findColisByExpedtionCode(code);
    }

    public List<Colis> listColisByState(String state, String town) {
        return colisRepository.findColisByState(state, town);
    }

    public List<Colis> listColisPendingFromTown(String town) {
        return colisRepository.listPendingFromTown(town);
    }

    public List<Colis> listColisBetweenDate(Date date1, Date date2) {
        return colisRepository.listColisBetweenDate(date1, date2);
    }

    public List<Colis> listColisBetweenDate(Date date1, Date date2, State state) {
        return colisRepository.listColisBetweenDate(date1, date2, state);
    }

    public List<Colis> listColisBetweenDate(String town, Date date1, Date date2) {
        return colisRepository.listColisBetweenDate(town, date1, date2);
    }

    public List<Colis> listColisBetweenDate(String town, Date date1, Date date2, State state) {
        return colisRepository.listColisBetweenDate(town, date1, date2, state);
    }

    public Colis save(Colis colis, Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalStateException("error agent n'existe pas!");
        }

        Colis c = colisRepository.save(colis);

        expeditionService.save(colis, user);

        return c;

    }

    public Colis sendColis(long colisId, long userId) {
        Colis colis = colisRepository.findById(colisId).orElse(null);
        if (colis == null) {
            throw new IllegalStateException("Le colis que vous volez envoyer n'existe pas!");
        }
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalStateException("error agent n'existe pas!");
        }
        if (colis.getState() != State.ATTENTE) {
            throw new IllegalStateException(
                    "Desoler vous ne pouvez pas envoyer le colis parcequ'il n'est pas en attente");
        }
        colis.setState(State.EXPEDIE);

        colisRepository.save(colis);

        expeditionService.sendColis(colis.getId(), user.getId());

        return colisRepository.findById(colisId).get();
    }

    public Colis arriveColis(long colisId, long userId) {
        Colis colis = colisRepository.findById(colisId).orElse(null);
        if (colis == null) {
            throw new IllegalStateException("Le colis que vous volez envoyer n'existe pas!");
        }
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalStateException("error agent n'existe pas!");
        }
        if (colis.getState() != State.EXPEDIE) {
            throw new IllegalStateException(
                    "Desoler vous ne pouvez pas marquer le colis comme arriver car il n'a pas encore etait expedier");
        }
        colis.setState(State.ARRIVER);
        colisRepository.save(colis);
        expeditionService.save(colis, user);

        return colisRepository.findById(colisId).get();
    }

    public Colis withdrawColis(long colisId, long userId) {
        Colis colis = colisRepository.findById(colisId).orElse(null);

        if (colis == null) {
            throw new IllegalStateException("Le colis que vous volez retirer n'existe pas");
        }
        Profile profile = profileService.findByUserId(userId);
        if (profile == null) {
            throw new IllegalStateException("L'utilisateur n'existe pas");
        }
        if (!profile.isSaver()) {
            throw new IllegalStateException("Not allow to withdraw a package");
        }
        if (colis.getState() != State.ARRIVER) {
            throw new IllegalStateException(
                    "Desoler vous ne pouvez pas retirer le colis car il n'est pas encore arriver");
        }
        colis.setState(State.RETIRER);
        colisRepository.save(colis);

        Retrait retrait = retraitService.withdrawColis(colisId, userId);

        return retrait.getColis();
    }

    public Colis findById(Long id) {
        return colisRepository.findById(id).orElse(null);
    }

    public List<Colis> getAll() {
        return colisRepository.findAll();
    }

    public Expedition getColisExpeditionDetail(Long colisId) {
        return expeditionService.getColisById(colisId);
    }

    public Retrait getColisRetraitDetail(Long colisId) {
        return retraitService.getColisById(colisId);
    }

    public List<Retrait> getColisArrive(String town) {
        return retraitService.getColisArrive(town);
    }

    public List<Retrait> getColisWithdraw(String town) {
        return retraitService.getColisWithdraw(town);
    }

    public List<Expedition> getColisPending(String town) {
        return expeditionService.getColisPending(town);
    }

    public List<Expedition> getColisSend(String town) {
        return expeditionService.getColisSend(town);
    }

}
