package com.aneto.todolist.user.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)public class LoginFormRequest {

    private String username;

    private String password;

    public UsernamePasswordAuthenticationToken toAuthToken() {
        return new UsernamePasswordAuthenticationToken(this.username, this.password);
    }

}
