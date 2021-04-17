package com.aneto.todolist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private final Date creationTime = new Date();

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
