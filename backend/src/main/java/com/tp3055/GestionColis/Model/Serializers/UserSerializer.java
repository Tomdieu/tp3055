package com.tp3055.GestionColis.Model.Serializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tp3055.GestionColis.Model.Entity.Profile;
import com.tp3055.GestionColis.Model.Entity.User;
import com.tp3055.GestionColis.Repository.ProfileRepository;

import lombok.Data;

// @Component
@Data
public class UserSerializer{
    private Profile profile;

    private Long id;

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private String town;

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
        return profileRepository.findProfileByUserId(this.getId());
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
