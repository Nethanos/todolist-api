package com.aneto.todolist.services;


import com.aneto.todolist.domain.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    public Task getTasks() {
        return new Task("This is a Summary", "this is a description");
    }
}
