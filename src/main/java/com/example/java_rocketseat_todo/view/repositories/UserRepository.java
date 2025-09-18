package com.example.java_rocketseat_todo.view.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.java_rocketseat_todo.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByName(String name);   
}
