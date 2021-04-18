package com.aneto.todolist.user;

import com.aneto.todolist.user.domain.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService {

    @PersistenceContext
    private final EntityManager em;

    public UserService(EntityManager em) {
        this.em = em;
    }


}
