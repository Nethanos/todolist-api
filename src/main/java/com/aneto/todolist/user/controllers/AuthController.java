package com.aneto.todolist.user.controllers;


import com.aneto.todolist.core.services.JwtTokenService;
import com.aneto.todolist.user.dto.LoginFormRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }
    @PostMapping("/")
    public ResponseEntity<String> login(@RequestBody LoginFormRequest loginFormRequest){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = loginFormRequest.toAuthToken();

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = jwtTokenService.generateFullToken(authentication);
        return ResponseEntity.ok(token);
    }
}
