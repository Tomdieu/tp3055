package com.tp3055.GestionColis.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp3055.GestionColis.Model.Entity.Colis;
import com.tp3055.GestionColis.Model.Entity.Profile;
import com.tp3055.GestionColis.Model.Entity.Retrait;
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

    public Optional<Colis> findByCode(String code){
        return colisRepository.findByExpedtionCode(code);
    }

    public Colis saveColis(Colis colis){
        return colisRepository.save(colis);
    }

    public List<Colis> listColis(){
        return colisRepository.findAll();
    }

    public List<Colis> listColis(String town){
        return colisRepository.findColisOfAgence(town);
    }

    public List<Colis> listColisSeByTown(String town){
        return colisRepository.findColisSendFromTown(town);
    }

    public List<Colis> listPendingColisFrom(String town){
        return colisRepository.listPendingFromTown(town);
    }

    public List<Colis> listColisArrive(String town){
        return colisRepository.findColisArriveSend(town);
    }

    public List<Colis> listColisRetirer(String town){
        return colisRepository.findColisRemoveFromTown(town);
    }

    public List<Colis> listColisSendBetweenTown(String from_town,String to_town){
        return colisRepository.findColisSendBetweenTown(from_town,to_town);
    }

    public Colis findByExpeditionCode(String code){
        return colisRepository.findColisByExpedtionCode(code);
    }

    public List<Colis> listColisByState(String state,String town){
        return colisRepository.findColisByState(state,town);
    }


    public List<Colis> listColisPendingFromTown(String town){
        return colisRepository.listPendingFromTown(town);
    }

    public List<Colis> listColisBetweenDate(String date1,String date2){
        return colisRepository.listColisBetweenDate(date1,date2);
    }

    public List<Colis> listColisBetweenDate(String date1,String date2,String state){
        return colisRepository.listColisBetweenDate(date1,date2,state);
    }

    public Object save(Colis colis) {
        return colisRepository.save(colis);
    }

    public Colis sendColis(long colisId, long userId) {
        Colis colis = colisRepository.findById(colisId).orElse(null);
        if(colis == null){
            throw new IllegalStateException("Le colis que vous volez envoyer n'existe pas!");
        }
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            throw new IllegalStateException("error agent n'existe pas!");
        }
        expeditionService.save(colis,user);

        return colisRepository.findById(colisId).get();
    }

    public Colis arriveColis(long colisId, long userId) {
        Colis colis = colisRepository.findById(colisId).orElse(null);
        if(colis == null){
            throw new IllegalStateException("Le colis que vous volez envoyer n'existe pas!");
        }
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            throw new IllegalStateException("error agent n'existe pas!");
        }
        expeditionService.save(colis,user);

        return colisRepository.findById(colisId).get();
    }

    public Colis withdrawColis(long colisId, long userId) {
        Colis colis = colisRepository.findById(colisId).orElse(null);

        if(colis == null){
            throw new IllegalStateException("Le colis que vous volez retirer n'existe pas");
        }
        Profile profile = profileService.findByUserId(userId);
        if(profile == null){
            throw new IllegalStateException("L'utilisateur n'existe pas");
        }
        if(!profile.isSaver()){
            throw new IllegalStateException("Not allow to withdraw a package");
        }
        Retrait retrait = retraitService.withdrawColis(colisId, userId);

        return retrait.getColis();
    }

}
