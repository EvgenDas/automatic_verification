package com.evgenygroupproject.spring.springboot.automatic_verification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@EnableWebSecurity
@Configuration
public class ApplicationConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)// отключает базовую аутентификацию
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/login", "/error", "/webjars/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .anyRequest().authenticated()
        )
        .oauth2Login(oauth2 -> oauth2
            .loginPage("/oauth2/authorization/github")
            .defaultSuccessUrl("/main", true)
        );

    return http.build();
  }

//  @Bean
//  public AuthenticationFailureHandler customAuthenticationFailureHandler() {
//    return (request, response, exception) -> {
//      response.sendRedirect("/?error");
//    };
//  }
}