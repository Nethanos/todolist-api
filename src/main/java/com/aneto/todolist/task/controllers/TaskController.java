package com.aneto.todolist.task.controllers;


import com.aneto.todolist.task.domain.Task;
import com.aneto.todolist.task.dto.NewTaskRequest;
import com.aneto.todolist.task.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/task")
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }


    @PostMapping("/")
    public ResponseEntity<String> newTask(@RequestBody NewTaskRequest newTaskRequest) {

        Task newTask = newTaskRequest.toModel();

        String taskId = taskService.createTask(newTask);

        return ResponseEntity.ok().body(taskId);
    }




}
