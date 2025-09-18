package com.example.java_rocketseat_todo.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity(name="tb_users")
public class User {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    

    @Column(unique = true, nullable = false)
    private String name;
    
    private String email;
    
    private String password; 

    @CreationTimestamp
    private LocalDateTime createdAt;

}
