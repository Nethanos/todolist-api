package com.aneto.todolist.core.security;

import com.aneto.todolist.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class ApplicationUserDetails implements UserDetails {

    private String username;

    private String password;

    private String userId;

    private Collection<SimpleGrantedAuthority> authorities; //ROLES

    public ApplicationUserDetails(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();

        this.userId = user.getId();

        authorities = new ArrayList<SimpleGrantedAuthority>();

        user.getRoles().forEach(role -> {
            this.authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserId() {
        return userId;
    }
}
