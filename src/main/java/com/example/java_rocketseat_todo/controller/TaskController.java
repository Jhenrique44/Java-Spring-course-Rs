package com.example.java_rocketseat_todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_rocketseat_todo.model.Task;
import com.example.java_rocketseat_todo.view.repositories.TaskRepository;

@RestController
@RequestMapping("/task")
public class TaskController {
    
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    public Task createTask(@RequestBody Task task){
        return this.taskRepository.save(task);
    }
    @GetMapping("/")
    public void getTasks(){
        System.out.println("List of tasks");
    }

}