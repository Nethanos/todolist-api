package com.aneto.todolist.core.security;

import com.aneto.todolist.user.domain.User;
import com.aneto.todolist.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsComponent implements UserDetailsService {

    private final UserService userService;

    public UserDetailsComponent(UserService userService){
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userService.findByUsername(username));
        /**
         * TODO: Transformar em constante
         */
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Deu ruim"));

        return new ApplicationUserDetails(user);
    }
}
