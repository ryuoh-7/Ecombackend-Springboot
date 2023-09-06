package com.project225160694002.Ecomcombo.apis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConflict {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable()).cors(cors->cors.disable());
        http.authorizeHttpRequests(auth->auth.anyRequest().permitAll());
        return http.build();
    }
}
