package com.tp3055.GestionColis.Service;

import org.springframework.stereotype.Service;

import com.tp3055.GestionColis.Model.Entity.Profile;
import com.tp3055.GestionColis.Repository.ProfileRepository;

@Service
public class ProfileService {
    private ProfileRepository profileRepository;

    public Profile findByUserId(Long id){
        return profileRepository.findProfileByUserId(id);
    }
}
