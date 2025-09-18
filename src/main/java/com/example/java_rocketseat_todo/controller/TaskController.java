package com.example.java_rocketseat_todo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_rocketseat_todo.model.Task;
import com.example.java_rocketseat_todo.utils.Utils;
import com.example.java_rocketseat_todo.view.repositories.TaskRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity createTask(@RequestBody Task task, HttpServletRequest request) {
        task.setUserId((UUID) request.getAttribute("idUser"));

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(task.getCreatedAt()) || currentDate.isAfter(task.getCompletedAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Task cannot be created in the past");
        }
        if (task.getCreatedAt().isAfter(task.getCompletedAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Task cannot be completed in the past");
        }
        var response = this.taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/")
    public List<Task> getTasks(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public Task update(@RequestBody Task task, HttpServletRequest request, @PathVariable UUID id) {
        var idUser = request.getAttribute("idUser");

        var attask = this.taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        Utils.copyNonNullProperties(task, attask);

        return this.taskRepository.save(task);
    }

}