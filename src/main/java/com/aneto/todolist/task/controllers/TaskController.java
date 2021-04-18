package com.aneto.todolist.task.controllers;


import com.aneto.todolist.task.domain.Task;
import com.aneto.todolist.task.dto.NewTaskRequest;
import com.aneto.todolist.task.dto.TaskResponse;
import com.aneto.todolist.task.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/task")
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }


    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks(@RequestParam(name = "status", required = false) String status){
        List<Task> taskList = taskService.getTasks(status);

        ArrayList<TaskResponse> taskResponseList = new ArrayList<>();

        taskList.forEach((task) -> taskResponseList.add(new TaskResponse(task)));

        return ResponseEntity.ok(taskResponseList);
    }

    @PostMapping
    public ResponseEntity<String> newTask(@RequestBody NewTaskRequest newTaskRequest) {

        Task newTask = newTaskRequest.toModel();

        String taskId = taskService.createTask(newTask);

        return ResponseEntity.ok().body(taskId);
    }




}
