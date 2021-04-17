package com.aneto.todolist.controllers;


import com.aneto.todolist.services.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/task")
@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }


    @GetMapping("/")
    public String getTasks() {
        return "tasks";
    }


}
