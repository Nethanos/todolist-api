package com.aneto.todolist.task.services;

import com.aneto.todolist.task.domain.Task;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration
public class TaskServiceTest {


    @Mock
    TaskService taskService;


    @Test
    @WithUserDetails("johnDoe")
    public void saveNewTask(){
       

    }

    @Test
    @WithUserDetails("johnDoe")
    public void getAllTasks(){

    }


    private Task createTask(){
        return new Task("this is a summary", "this is a description");
    }
}
