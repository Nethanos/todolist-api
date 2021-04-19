package com.aneto.todolist.task.services;

import com.aneto.todolist.task.domain.Task;
import com.aneto.todolist.task.domain.Task_;
import com.aneto.todolist.user.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.*;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration
public class TaskServiceTest {

    @InjectMocks
    TaskService taskService;
    @Mock
    EntityManager em;
    @Mock
    CriteriaBuilder cb;
    @Mock
    CriteriaQuery<Task> criteriaQuery;
    @Mock
    Root<Task> rootTask;
    @Mock
    Path<User> user;
    @Mock
    Path<String> id;

    @Mock
    TypedQuery<Task> typedQuery;

    private List<Task> taskList;

    @Test
    @WithUserDetails("johnDoe")
    public void saveNewTask(){

        doAnswer((invocation) -> {
            //TODO: Se houver tempo, usar reflection
            Task task = (Task) invocation.getArguments()[0];
            task.setId("5");
            return null;
        }).when(em).persist(any(Task.class));

        String taskId = taskService.createTask(createTask());

        Assert.assertNotNull(taskId);

    }

    @Test
    @WithUserDetails("johnDoe")
    public void retrieveTask() {
        when(em.find(Task.class, "someId")).thenReturn(createTask());

        Task task = taskService.retrieveTask("someId");

        Assert.assertNotNull(task);
    }

    @Test
    @WithUserDetails("johnDoe")
    public void updateTask() {

        Task taskToBeUpdated = taskFromForm();

        when(em.find(Task.class, "someId")).thenReturn(createTask());
        when(em.getReference(User.class,"someId")).thenReturn(new User());

        doAnswer((invocation) -> {
            Task task = (Task) invocation.getArguments()[0];
            task.setDescription("new description");
            return task;
        }).when(em).merge(any(Task.class));



        Task updatedTask = taskService.updateTask(taskToBeUpdated, "someId");

        Assert.assertEquals("new description", updatedTask.getDescription());

    }

    @Test
    @WithUserDetails("johnDoe")
    public void getAllTasks(){

        mockCriteriaQuery();


        List<Task> returnedTaskList = taskService.getTasks("pending");

        Assert.assertEquals(3, returnedTaskList.size());
    }

    @Test
    @WithUserDetails("johnDoe")
    public void deleteTask(){

        when(em.find(Task.class, "someId")).thenReturn(createTask());

        doNothing().when(em).remove(any(Task.class));

        Task task = taskService.retrieveTask(createTask().getId());

        taskService.deleteTask(task.getId());
    }


    @Test
    @WithUserDetails("admin")
    public void getTasksAsAdmin(){
        mockCriteriaQuery();

        taskService.getTasks("pending");

        List<Task> returnedTaskList = taskService.getAllTasks("pending");

        Assert.assertEquals(3, returnedTaskList.size());
    }

    private Task createTask(){
        Task task =  new Task("this is a summary", "this is a description");
        task.setId("someId");
        return task;
    }

    private Task taskFromForm(){
        return  new Task("this is a summary", "this is a description");
    }

    private List<Task> getTaskList(){
        if(taskList != null){
            return this.taskList;
        }
        this.taskList = new ArrayList<Task>();
        for(int i = 0; i < 3; i++){
            taskList.add(new Task("Summary number " + i, "Description number " + i));
        }
        return taskList;
    }


    private void mockCriteriaQuery(){
        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(cb.createQuery(Task.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Task.class)).thenReturn(rootTask);
        when(rootTask.get(Task_.user)).thenReturn(user);
        when(rootTask.get(Task_.id)).thenReturn(id);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(getTaskList());
    }


}
