package com.example.springbootjdbc.webConfig;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.PathMatcher;

@Configuration
public class SecurityConfigurer {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, PathMatcher mvcPathMatcher) throws Exception {

        http.csrf((csrf)->csrf.ignoringRequestMatchers("/saveMessage"))
                .authorizeHttpRequests((auth) -> auth.requestMatchers("/","/home").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // Allow static resources
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/message").permitAll()
                        .requestMatchers("/home/welcome").permitAll()
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/saveMessage").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/displayMessages").hasRole("ADMIN")
                        .requestMatchers("/closeMessage/**").hasRole("ADMIN"))
                .formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true").permitAll())
                .logout(logout->logout.logoutSuccessUrl("/home")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());

//        http.headers(headers -> headers
//                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
//        );

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetails(){
        User.UserBuilder  users = User.withDefaultPasswordEncoder();

        UserDetails user = users
                .username("user")
                .password("12345")
                .roles("USER")
                .build();

        UserDetails admin = users
                .username("admin")
                .password("123456")
                .roles("ADMIN")
                .build();

        return  new InMemoryUserDetailsManager(user,admin);
    }
}
