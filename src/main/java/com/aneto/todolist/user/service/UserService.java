package com.aneto.todolist.user.service;

import com.aneto.todolist.user.domain.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Service
public class UserService {

    @PersistenceContext
    private final EntityManager em;

    public UserService(EntityManager em) {
        this.em = em;
    }

    public User findByUsername(String username){
        String sql = "select u from User u where u.username = :username";
        TypedQuery<User> query = em.createQuery(sql, User.class);

        query.setParameter("username", username);

        return query.getSingleResult();
    }

    public User findById(String id) {

        Optional<User> user = Optional.of(em.find(User.class, id));
        /**
         * TODO: Tratar da forma correta
         */
        return user.orElseThrow(() -> new RuntimeException("batata"));
    }


}
