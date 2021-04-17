package com.aneto.todolist.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "db_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    private final Date creationTime = new Date();

    @NotBlank
    private String summary;

    @NotBlank
    private String description;

    @NotBlank
    private TaskStatus status = TaskStatus.PENDING;

    private Date updateTime;


    public Task(@Valid String summary, @Valid String description) {
        this.summary = summary;
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
