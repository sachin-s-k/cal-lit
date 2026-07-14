package com.example.cal_lit_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain  securityFilterChain(HttpSecurity httpSecurity){
        //stateless session (token based authentication)
        // Disable CSRF
        // authorize

        httpSecurity.
                sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                csrf(AbstractHttpConfigurer::disable).
                authorizeHttpRequests(AbstractRequestMatcherRegistry::anyRequest);

        return httpSecurity.build();

    }
}
