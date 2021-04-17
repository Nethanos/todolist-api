package com.aneto.todolist.dto;

import com.aneto.todolist.domain.Task;

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
