package com.tp3055.GestionColis.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp3055.GestionColis.Model.Entity.Colis;
import com.tp3055.GestionColis.Repository.ColisRepository;

@Service
public class ColisService {
    @Autowired
    private ColisRepository colisRepository;

    public Colis saveColis(Colis colis){
        return colisRepository.save(colis);
    }

    public List<Colis> listColis(){
        return colisRepository.findAll();
    }

    public List<Colis> listColisSeByTown(String town){
        return colisRepository.findColisSendFromTown(town);
    }

}
