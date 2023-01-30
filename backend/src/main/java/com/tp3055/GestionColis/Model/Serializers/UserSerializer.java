package com.tp3055.GestionColis.Model.Serializers;

import org.springframework.beans.factory.annotation.Autowired;

import com.tp3055.GestionColis.Model.Entity.Profile;
import com.tp3055.GestionColis.Model.Entity.User;
import com.tp3055.GestionColis.Repository.ProfileRepository;


public class UserSerializer extends User {
    private Profile profile;

    @Autowired
    private ProfileRepository profileRepository;

    public UserSerializer(User user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setFirstname(user.getFirstname());
        this.setLastname(user.getLastname());
        this.setPassword(user.getPassword());
        this.setTown(user.getTown());
        this.profile = this.loadProfile();
        // this.profile =
        // profileRepository.findProfileByUser(user.getId()).orElse(null);
    }

    public Profile loadProfile() {
        System.out.println("Repo : "+profileRepository);
            return null;
        // return profileRepository.findProfileByUser(this.getId()).orElse(null);
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
