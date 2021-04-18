package com.aneto.todolist.task.dto;

import com.aneto.todolist.task.domain.Task;
import com.aneto.todolist.task.domain.TaskStatus;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TaskResponse {

    private String summary;

    private String description;

    private TaskStatus status;

    private String id;

    private Date creationTime;

    private Date updateTime;

    public TaskResponse(Task task) {
            this.summary = task.getSummary();
            this.description = task.getDescription();
            this.status = task.getStatus();
            this.id = task.getId();
            this.creationTime = task.getCreationTime();
            this.updateTime = task.getUpdateTime();
    }

}
