package com.security.demo.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/public/**", "/exception", "/login", "/oauth2").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .permitAll()
                )
                .logout((logout) -> logout
                        .permitAll()
//                ).exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/exception"))
//                ).oauth2Login((oauth2) -> oauth2.loginPage("/oauth2"))
//                .exceptionHandling(exceptions -> exceptions
//                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/oauth2/authorization/google"))
                )
                .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        List<UserDetails> registeredUsers = new ArrayList<>();
        registeredUsers.add(
                User.builder()
                        .username("user")
                        .password("{noop}user")
                        .roles("USER")
                        .build()
        );
        registeredUsers.add(
                User.builder()
                        .username("admin")
                        .password("{noop}admin")
                        .roles("ADMIN")
                        .build()
        );
        registeredUsers.add(
                User.builder()
                        .username("other")
                        .password("{noop}other")
                        .roles("USER")
                        .authorities("READ_THAT_AUTHORISED_PAGE")
                        .build()
        );

        return new InMemoryUserDetailsManager(registeredUsers
        );
    }
}
