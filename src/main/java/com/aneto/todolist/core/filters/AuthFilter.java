package com.aneto.todolist.core.filters;

import com.aneto.todolist.core.exceptions.MissingTokenException;
import com.aneto.todolist.core.security.ApplicationUserDetails;
import com.aneto.todolist.core.services.JwtTokenService;
import com.aneto.todolist.user.domain.User;
import com.aneto.todolist.user.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    private JwtTokenService jwtTokenService;

    private UserService userService;

    public AuthFilter(JwtTokenService jwtTokenService, UserService userService) {
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {

            String token = this.retrieveToken(httpServletRequest);

            if (jwtTokenService.isValid(token)) {
                authenticateRequest(token);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        }catch (MissingTokenException e){
            /**
             * TODO: CHECK WHY MESSAGE IS NOT RETURNED TO CLIENT
             */
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }


    }

    private String retrieveToken (HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || token.isBlank() || !token.startsWith("Bearer ")) {
            throw new MissingTokenException("No JWT token found in request headers");
        }

        // Token without 'Bearer'
        return token.substring(7);
    }

    private void authenticateRequest(String token) {
        String userId = jwtTokenService.getUserId(token);
        User user = userService.findById(userId);

        ApplicationUserDetails userDetails = new ApplicationUserDetails(user);
        // Credentials are null because at this point of the code, the password was already checked
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
