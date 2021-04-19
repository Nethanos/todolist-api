package com.aneto.todolist.user.controllers;


import com.aneto.todolist.core.services.JwtTokenService;
import com.aneto.todolist.user.dto.LoginFormRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }
    @ApiOperation(value = "Authenticate a User")
    @PostMapping("")
    public ResponseEntity<String> login(@RequestBody LoginFormRequest loginFormRequest){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = loginFormRequest.toAuthToken();
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário incorreto ou não encontrado. Por favor, verifique as credenciais");
        }

        String token = jwtTokenService.generateFullToken(authentication);
        return ResponseEntity.ok(token);
    }
}
