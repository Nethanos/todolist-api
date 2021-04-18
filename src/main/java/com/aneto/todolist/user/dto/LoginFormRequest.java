package com.aneto.todolist.user.dto;

import com.aneto.todolist.utils.JsonDefaultConfig;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@JsonDefaultConfig
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LoginFormRequest {

    private String username;

    private String password;

    public UsernamePasswordAuthenticationToken toAuthToken() {
        return new UsernamePasswordAuthenticationToken(this.username, this.password);
    }

}
