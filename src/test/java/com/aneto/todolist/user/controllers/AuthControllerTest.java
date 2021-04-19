package com.aneto.todolist.user.controllers;

import com.aneto.todolist.security.JwtTokenService;
import com.aneto.todolist.security.UserDetailsComponent;
import com.aneto.todolist.security.WebSecurityConfiguration;
import com.aneto.todolist.user.domain.User;
import com.aneto.todolist.user.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class AuthControllerTest {

    /*@Autowired
    private MockMvc mvc;

    //TODO: Verificar problema do contexto de security
    @Test
    public void authenticateUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        
        user.setUsername("johnDoe");
        user.setPassword("1234");

        String jsonUser = objectMapper.writeValueAsString(user);

        mvc.perform(post("/auth")
                .content(jsonUser)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }*/

    @Test
    public void testAuthenticationUser(){

    }

}
