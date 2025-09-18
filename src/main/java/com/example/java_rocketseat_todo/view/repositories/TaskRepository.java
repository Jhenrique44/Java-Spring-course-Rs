package com.example.java_rocketseat_todo.view.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.java_rocketseat_todo.model.Task;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    
}
