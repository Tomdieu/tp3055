package com.tp3055.GestionColis.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp3055.GestionColis.Model.Entity.Profile;
import com.tp3055.GestionColis.Model.Entity.User;
import com.tp3055.GestionColis.Model.Serializers.UserSerializer;
import com.tp3055.GestionColis.Repository.ProfileRepository;
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
    private TokenService tokenService;

    public UserSerializer saveUser(UserRequest user) {

        User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);

        if (existingUser != null) {
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

        tokenService.generateToken(saveUser);

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
            System.out.println(user.getPassword() + " " + loginRequest.getPassword());
            if (isValidPassword(user.getPassword(), loginRequest.getPassword())) {
                return profileRepository.findProfileByUserId(user.getId());
            }
        }

        throw new IllegalStateException("User not found");

    }

    private boolean isValidPassword(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    @Transactional
    public User updateUser(Long id, UserRequest userRequest) {
        Boolean exist = userRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("User don't exists");
        }
        User existingUser = userRepository.findById(id).orElse(null);
        Profile userProfile = profileRepository.findProfileByUserId(id);
        if (userRequest.getUsername() != null) {
            if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
                throw new IllegalStateException("Username already taken");
            }
            existingUser.setUsername(userRequest.getUsername());
        }
        if (userRequest.getFirstname() != null) {
            existingUser.setFirstname(userRequest.getFirstname());
        }

        if (userRequest.getLastname() != null) {
            existingUser.setLastname(userRequest.getLastname());
        }

        if (userRequest.getTown() != null) {
            existingUser.setTown(userRequest.getTown());
        }

        if (userRequest.getIsAdmin()) {
            userProfile.setAdmin(userRequest.getIsAdmin());
        }

        if (userRequest.getIsSaver()) {
            userProfile.setSaver(userRequest.getIsSaver());
        }

        if (userRequest.getIsSender()) {
            userProfile.setSender(userRequest.getIsSender());
        }

        User u = userRepository.save(existingUser);
        profileRepository.save(userProfile);

        return u;
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User Deleted!";
    }

    public User userExistWith(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            return null;
        }
        if (user.get().getPassword().equals(password)) {
            return user.get();
        }
        return null;
    }
}
