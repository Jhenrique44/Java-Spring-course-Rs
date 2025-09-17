package com.example.java_rocketseat_todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_rocketseat_todo.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/")
    public void createUser(@RequestBody User user) {
        System.out.println("User created: " + user.getName() + ", " + user.getEmail());
    }

    @GetMapping("/")
    public void getUsers(){
        System.out.println("List of users");
    }
}
