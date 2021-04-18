package com.aneto.todolist.task.dto;

import com.aneto.todolist.task.domain.Task;
import com.aneto.todolist.task.domain.TaskStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UpdateTaskRequest {

    private String description;

    private String summary;

    private String status;

    public Task toModel(){
        Task taskToBeUpdated = new Task(description, summary);
        taskToBeUpdated.setStatus(TaskStatus.valueOf(status.toUpperCase()));
        return taskToBeUpdated;
    }
}
