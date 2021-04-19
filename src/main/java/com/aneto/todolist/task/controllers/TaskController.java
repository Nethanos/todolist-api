package com.aneto.todolist.task.controllers;

import com.aneto.todolist.task.domain.Task;
import com.aneto.todolist.task.dto.NewTaskRequest;
import com.aneto.todolist.task.dto.TaskResponse;
import com.aneto.todolist.task.dto.UpdateTaskRequest;
import com.aneto.todolist.task.services.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/task")
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @ApiOperation(value = "Retrieve one task")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> retrieveTask(@PathVariable String id){

        return ResponseEntity.ok(new TaskResponse(taskService.retrieveTask(id)));
    }

    @ApiOperation(value = "Get a list of tasks")
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks(@RequestParam(name = "status", required = false) String status){
        List<Task> taskList = taskService.getTasks(status);

        ArrayList<TaskResponse> taskResponseList = new ArrayList<>();

        taskList.forEach((task) -> taskResponseList.add(new TaskResponse(task)));

        return ResponseEntity.ok(taskResponseList);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskResponse>> getAllTasks(@RequestParam(name = "status", required = false) String status){
        List<Task> taskList = taskService.getAllTasks(status);

        ArrayList<TaskResponse> taskResponseList = new ArrayList<>();

        taskList.forEach((task) -> taskResponseList.add(new TaskResponse(task)));

        return ResponseEntity.ok(taskResponseList);
    }

    @ApiOperation(value = "Create a new Task")
    @PostMapping
    public ResponseEntity<String> newTask(@Valid @RequestBody NewTaskRequest newTaskRequest) {

        Task newTask = newTaskRequest.toModel();

        String taskId = taskService.createTask(newTask);

        return ResponseEntity.status(HttpStatus.CREATED).body(taskId);
    }

    @ApiOperation(value = "Update a task")
    @PutMapping("/{id}")
    public ResponseEntity updateTask(@RequestBody UpdateTaskRequest updateTaskRequest, @PathVariable String id){
        taskService.updateTask(updateTaskRequest.toModel(), id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value = "Delete a task")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable String id){
        taskService.deleteTask(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}