package com.aneto.todolist.core.services;

import com.aneto.todolist.core.security.ApplicationUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenService {

    @Value("300000")
    private String expirationTimeInMillis;

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generate (Authentication authentication) {

        ApplicationUserDetails loggedUser = (ApplicationUserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + Long.parseLong(expirationTimeInMillis));

        String jws = Jwts.builder()
                .setIssuer("API todolist")
                .setSubject(loggedUser.getUserId())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(KEY)
                .compact();

        assertToken(jws);
        return jws;
    }

    public String generateFullToken(Authentication authentication){
       return "Bearer " + this.generate(authentication);
    }

    public boolean isValid(String token) {

            assertToken(token);
            return true;

    }

    private void assertToken(String token) {
        Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
    }

    public String getUserId (String token) {
        Claims body = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
        return body.getSubject();
    }
}
