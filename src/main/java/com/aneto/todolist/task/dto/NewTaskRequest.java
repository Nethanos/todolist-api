package com.aneto.todolist.task.dto;

import com.aneto.todolist.task.domain.Task;
import com.aneto.todolist.utils.JsonDefaultConfig;

@JsonDefaultConfig
public class NewTaskRequest {

    private String description;

    private String summary;

    public Task toModel(){
        return new Task(this.summary, this.description);
    }
}
