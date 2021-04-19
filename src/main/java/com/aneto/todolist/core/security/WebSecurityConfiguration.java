package com.aneto.todolist.core.security;

import com.aneto.todolist.core.filters.AuthFilter;
import com.aneto.todolist.core.services.JwtTokenService;
import com.aneto.todolist.user.service.UserService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableConfigurationProperties
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        private final JwtTokenService jwtTokenService;
        private final UserService userService;

        private final UserDetailsComponent userDetailsComponent;

        public WebSecurityConfiguration(JwtTokenService jwtTokenService, UserService userService, UserDetailsComponent userDetailsComponent) {
                this.jwtTokenService = jwtTokenService;
                this.userService = userService;
                this.userDetailsComponent = userDetailsComponent;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

                http
                        .authorizeRequests()
                        .antMatchers("/task/all")
                        .hasRole("ADMIN")
                        .antMatchers("/task/**")
                        .hasRole("USER")
                        .antMatchers("/").permitAll()
                        .and()
                        .csrf().disable()
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .addFilterBefore(new AuthFilter(jwtTokenService, userService), UsernamePasswordAuthenticationFilter.class);
        }

        @Override
        @Bean
        protected AuthenticationManager authenticationManager () throws Exception {
                return super.authenticationManager();
        }


        @Override
        protected void configure(AuthenticationManagerBuilder builder) throws Exception {
                builder
                        .userDetailsService(userDetailsComponent)
                        .passwordEncoder(new BCryptPasswordEncoder());
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
                web
                        .ignoring()
                        .antMatchers("/h2-console/**")
                        .antMatchers("/auth/**")
                        .antMatchers("/actuator/**")
                        .antMatchers("/swagger-ui**", "/csrf/**")
                        .antMatchers("/webjars/**")
                        .antMatchers("/swagger-resources/**")
                        .antMatchers("/v2/api-docs/**");
        }


}
