package com.aneto.todolist.user.domain;

import com.aneto.todolist.core.domain.DomainEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "db_role")
public class Role extends DomainEntity {

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

}