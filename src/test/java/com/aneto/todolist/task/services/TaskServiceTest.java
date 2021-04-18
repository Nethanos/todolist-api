package com.aneto.todolist.task.services;

import com.aneto.todolist.task.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskServiceTest {


    @Autowired
    TaskService taskService;


    @Test
    public void saveNewTask(){
        String taskId = taskService.createTask(createTask());

        assertNotNull(taskId);
    }


    private Task createTask(){
        return new Task("this is a summary", "this is a description");
    }
}
