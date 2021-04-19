package com.aneto.todolist.user.services;

import com.aneto.todolist.task.domain.Task;
import com.aneto.todolist.user.domain.User;
import com.aneto.todolist.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    EntityManager em;

    @Mock
    TypedQuery<User> userTypedQuery;

    @Test
    @WithUserDetails("johnDoe")
    public void findByUsername() {

        when(em.createQuery(any(String.class), eq(User.class))).thenReturn(userTypedQuery);
        when(userTypedQuery.getSingleResult()).thenReturn(new User());

        User johnDoe = userService.findByUsername("johnDoe");

        Assert.assertNotNull(johnDoe);
    }

    @Test
    @WithUserDetails("johnDoe")
    public void findById() {

        when(em.find(User.class, "someId")).thenReturn(new User());

        User user = userService.findById("someId");

        Assert.assertNotNull(user);
    }
}
