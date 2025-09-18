package com.example.java_rocketseat_todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_rocketseat_todo.model.User;
import com.example.java_rocketseat_todo.view.repositories.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody User user) {
        this.userRepository.findByName(user.getName());
        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");

        }
        user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));
        var response = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/")
    public void getUsers(){
        System.out.println("List of users");
    }
}
