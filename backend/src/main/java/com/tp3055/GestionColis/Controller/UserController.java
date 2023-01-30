package com.tp3055.GestionColis.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp3055.GestionColis.Model.Entity.Profile;
import com.tp3055.GestionColis.Model.Entity.User;
import com.tp3055.GestionColis.Model.Serializers.UserSerializer;
import com.tp3055.GestionColis.Service.UserService;
import com.tp3055.GestionColis.utils.LoginRequest;
import com.tp3055.GestionColis.utils.UserRequest;

import jakarta.annotation.Nullable;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * @return All the users
     */
    @GetMapping(path = "/")
    public ResponseEntity<List<Profile>> getAllUsers(@Nullable @RequestParam String town) {
        System.out.println("Town : " + town);
        if (town != null)
            return ResponseEntity.ok(service.getAllUsersByTown(town));

        else
            return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Profile> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserSerializer> registerUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(service.saveUser(userRequest));
    }

    @PostMapping(path = "/login")
    public Profile loginUser(@RequestBody(required = true) LoginRequest loginRequest) {
        System.out.println("Enter in login");
        return service.loginUser(loginRequest);
    }

    @PatchMapping(path = "/{userId}/update")
    public User updateUser(@PathVariable(required = true) Long userId, @RequestBody UserRequest userRequest) {
        return service.updateUser(userId, userRequest);
    }

    @DeleteMapping(path = "/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        return service.deleteUser(userId);
    }

}