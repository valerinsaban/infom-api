package gob.gt.infom.security;

import gob.gt.infom.security.filters.JwtAuthenticationFilter;
import gob.gt.infom.security.filters.JwtAuthorizationFilter;
import gob.gt.infom.security.jwt.JwtUtils;
import gob.gt.infom.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  JwtAuthorizationFilter authorizationFilter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager)
      throws Exception {

    JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
    jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
    jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");

    http
        .cors(Customizer.withDefaults())
        .csrf(config -> config.disable())
        .authorizeHttpRequests(auth -> {
          auth.requestMatchers("**").permitAll();
          auth.anyRequest().authenticated();
        })
        .sessionManagement(session -> {
          session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        })
        .addFilter(jwtAuthenticationFilter)
        .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager(HttpSecurity http,
      PasswordEncoder passwordEncoder)
      throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder)
        .and()
        .build();
  }
}
