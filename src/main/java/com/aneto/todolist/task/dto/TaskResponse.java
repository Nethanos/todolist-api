package com.aneto.todolist.task.dto;

import com.aneto.todolist.task.domain.Task;
import com.aneto.todolist.utils.JsonDefaultConfig;

@JsonDefaultConfig
public class TaskResponse {

    private String summary;

    private String description;

    public TaskResponse(Task task) {
            this.summary = task.getSummary();
            this.description = task.getDescription();
    }

}
