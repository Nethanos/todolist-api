package com.aneto.todolist.task.services;

import com.aneto.todolist.core.providers.LoggedUserProvider;
import com.aneto.todolist.task.domain.Task;
import com.aneto.todolist.task.domain.TaskStatus;
import com.aneto.todolist.task.domain.Task_;
import com.aneto.todolist.user.domain.User;
import com.aneto.todolist.user.domain.User_;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

    @PersistenceContext
    private final EntityManager em;

    public TaskService(EntityManager em) {
        this.em = em;
    }

    public List<Task> getTasks(String taskStatus){
        String loggedUserId = LoggedUserProvider.getLoggedUserId();

        CriteriaQuery<Task> criteria = getTaskCriteriaQuery(taskStatus, loggedUserId);

        List<Task> taskList = em.createQuery(criteria).getResultList();

        return taskList;

    }

    private CriteriaQuery<Task> getTaskCriteriaQuery(String taskStatus, String userId) {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Task> criteria = builder.createQuery( Task.class );

        Root<Task> root = criteria.from(Task.class);
        
        ArrayList<Predicate> predicateList = new ArrayList<Predicate>();

        predicateList.add(builder.equal(root.get(Task_.user).get(User_.id), userId));
        if(Objects.nonNull(taskStatus)){
            predicateList.add(builder.equal(root.get(Task_.status), TaskStatus.valueOf(taskStatus)));
        }

        criteria.where(predicateList.toArray(new Predicate[predicateList.size()]));

        criteria.orderBy(builder.asc(root.get(Task_.status)));
        return criteria;
    }


    @Transactional
    public String createTask(Task task){

        String loggedUserId = LoggedUserProvider.getLoggedUserId();

        User user = em.getReference(User.class, loggedUserId);

        task.setUser(user);

        em.persist(task);

        //TODO: Ajustar exception
        Assert.notNull(task.getId(), "Deu ruim");

        return task.getId();
    }
}
