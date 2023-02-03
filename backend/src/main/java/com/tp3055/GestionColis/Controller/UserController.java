package com.tp3055.GestionColis.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

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
import com.tp3055.GestionColis.Service.UserService;
import com.tp3055.GestionColis.utils.LoginRequest;
import com.tp3055.GestionColis.utils.UserRequest;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService service;

    /**
     * @return All the users
     */
    @GetMapping(path = "/")
    public ResponseEntity<List<Profile>> getAllUsers(@Nullable @RequestParam String town) {
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
    public ResponseEntity<Profile> registerUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(service.saveUser(userRequest));
    }

    @PostMapping(path = "/login")
    public Profile loginUser(@RequestBody LoginRequest loginRequest,HttpServletRequest request) {
        System.out.println("Enter in login");
        System.out.println("Auth " + request.getHeader("Authorization"));
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