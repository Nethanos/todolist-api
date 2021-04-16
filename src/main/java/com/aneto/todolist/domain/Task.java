package com.aneto.todolist.domain;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Task {

    private Date creationTime = new Date();

    private String summary;

    private String description;

    private TaskStatus status;

    private Date updateTime;


    public Task(String summary, String description) {
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
