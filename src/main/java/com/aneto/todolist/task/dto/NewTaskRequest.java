package com.aneto.todolist.task.dto;

import com.aneto.todolist.task.domain.Task;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NewTaskRequest {

    private String description;

    private String summary;

    public Task toModel(){
        return new Task(this.summary, this.description);
    }
}
