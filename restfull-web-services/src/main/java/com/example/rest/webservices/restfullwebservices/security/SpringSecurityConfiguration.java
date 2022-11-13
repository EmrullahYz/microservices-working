package com.example.rest.webservices.restfullwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        //1. all requests should be authenticated
        http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
        //2. if a request is not authenticated a web page is shown
        http.httpBasic(withDefaults());
        // csrf -> post, put
        http.csrf().disable();
        return http.build();
    }
}
