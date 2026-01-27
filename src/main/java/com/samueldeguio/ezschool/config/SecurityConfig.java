package com.samueldeguio.ezschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .authorizeHttpRequests(authorize -> authorize
                    // allow free access to the home page, login page, and static resources
                    .requestMatchers("/", "/courses", "/business", "/mentors", "/contact", "/static/**", "/login", "/register").permitAll()
                    // any other request requires authentication
                    .anyRequest().authenticated()
                )
                .formLogin(configure -> configure
                        // on login success redirect to /, otherwise remain on /login with errors
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(configure -> configure
                    // on logout redirect to login page and invalidate the session
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .permitAll()
                );
        return http.build();
    }

}
