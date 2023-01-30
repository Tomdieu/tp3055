package com.tp3055.GestionColis.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp3055.GestionColis.Model.Entity.Profile;
import com.tp3055.GestionColis.Model.Entity.Token;
import com.tp3055.GestionColis.Model.Entity.User;
import com.tp3055.GestionColis.Model.Serializers.UserSerializer;
import com.tp3055.GestionColis.Repository.ProfileRepository;
import com.tp3055.GestionColis.Repository.TokenRepository;
import com.tp3055.GestionColis.Repository.UserRepository;
import com.tp3055.GestionColis.utils.LoginRequest;
import com.tp3055.GestionColis.utils.UserRequest;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public UserSerializer saveUser(UserRequest user) {

        User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        
        if(existingUser != null){
            throw new IllegalStateException("username already taken");
        }

        User _user = User.builder()
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .password(user.getPassword())
                .town(user.getTown())
                .build();
        User saveUser = userRepository.save(_user);
        Profile profile = Profile.builder()
                .user(_user)
                .isAdmin(user.getIsAdmin())
                .isSaver(user.getIsSaver())
                .isSender(user.getIsSender())
                .build();
        profileRepository.save(profile);

        Token token = Token.builder().user(saveUser).build();

        tokenRepository.save(token);

        UserSerializer serializer = new UserSerializer(saveUser);

        return serializer;
    }

    public Profile getUserById(Long id) {
        System.out.println("Profile : " + profileRepository.findProfileByUserId(id));
        return profileRepository.findProfileByUserId(id);
    }

    public User getUserByName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<Profile> getAllUsers() {
        System.out.println("Users : " + userRepository.findAll());
        return profileRepository.findAll();
    }

    public List<Profile> getAllUsersByTown(String town) {
        System.out.println("Users : " + userRepository.findAllByTown(town));
        System.out.println("Profile : " + profileRepository.findAllByTown(town));
        return profileRepository.findAllByTown(town);
    }

    public Profile loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
        System.out.println("User found : " + user);
        if (user != null) {
            if (user.getPassword() == loginRequest.getPassword()) {
                return profileRepository.findProfileByUserId(user.getId());
            }
        }

        return null;

    }

    @Transactional
    public User updateUser(Long id,UserRequest userRequest) {
        Boolean exist =userRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("User don't exists");
        }
        User existingUser = userRepository.findById(id).orElse(null);
        if(userRequest.getUsername()!=null){
            if(userRepository.findByUsername(userRequest.getUsername()).isPresent()){
                throw new IllegalStateException("Username already taken");
            }
            existingUser.setUsername(userRequest.getUsername());
        }
        existingUser.setFirstname(userRequest.getFirstname());
        existingUser.setLastname(userRequest.getLastname());
        return null;
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User Deleted!";
    }
}
