package com.aneto.todolist.task.domain;

import com.aneto.todolist.core.domain.DomainEntity;
import com.aneto.todolist.user.domain.User;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "db_task")
public class Task extends DomainEntity {

    @NotNull
    private final Date creationTime = new Date();

    @NotBlank
    private String summary;

    @NotBlank
    private String description;

    /**
     * TODO: Find a way to create userFK in DB
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /**
     * @deprecated do not use. Framework usage only;
     */
    @Deprecated(forRemoval = false)
    public Task() {

    }

    /**
     * TODO put not blank to work
     */
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDING;

    private Date updateTime;


    public Task(@Valid String summary, @Valid String description) {
        this.summary = summary;
        this.description = description;
    }

    @PreUpdate
    public void updateTime(){
        this.updateTime = new Date();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

}
