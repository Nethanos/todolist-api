package com.aneto.todolist.task.dto;

import com.aneto.todolist.task.domain.Task;

public class TaskResponse {

    private String summary;

    private String description;

    public TaskResponse(Task task) {
            this.summary = task.getSummary();
            this.description = task.getDescription();
    }

    public String getDescription() {
        return description;
    }


    public String getSummary() {
        return summary;
    }
}
