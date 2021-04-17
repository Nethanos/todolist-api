package com.aneto.todolist.task.services;


import com.aneto.todolist.task.domain.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class TaskService {

    @PersistenceContext
    private final EntityManager em;

    public TaskService(EntityManager em) {
        this.em = em;
    }

    public Task getTasks() {
        return new Task("This is a Summary", "this is a description");
    }

    @Transactional
    public String createTask(Task task){
        em.persist(task);

        Assert.notNull(task.getId(), "Deu ruim");

        return task.getId();
    }
}
