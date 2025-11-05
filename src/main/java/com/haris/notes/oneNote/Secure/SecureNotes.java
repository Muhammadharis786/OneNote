package com.haris.notes.oneNote.Secure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import javax.sql.DataSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecureNotes {


    @Autowired
UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://127.0.0.1:5500")); // your frontend
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        // FIX: Use a wildcard to ensure all requests to this path are permitted
                        .requestMatchers("/api/user/Register").permitAll()
                        .requestMatchers("/api/user/getuser").permitAll()
                        .anyRequest().authenticated())


                .httpBasic(withDefaults())
                .formLogin(withDefaults());


        return http.build();


    }

//    @Bean
//    UserDetailsService userDetailsService() {
//
//      InMemoryUserDetailsManager manager  = new InMemoryUserDetailsManager();
//
//
//        if (!manager.userExists("haris")) {
//            manager.createUser(
//                    User.withUsername("haris")
//                            .password("{noop}haris123")
//                            .roles("USER")
//                            .build()
//
//            );
//        }
//        if (!manager.userExists("admin")) {
//
//
//            manager.createUser(
//                    User.withUsername("admin")
//                            .password("{noop}admin123")
//                            .roles("ADMIN")
//                            .build()
//
//            );
//        }
//
//        return manager;
//
//    }

//
//    @Bean
//    UserDetailsService userDetailsService(DataSource dataSource) {
//
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//
//
//        if (!manager.userExists("haris")) {
//            manager.createUser(
//                    User.withUsername("haris")
//                            .password("{noop}haris123")
//                            .roles("USER")
//                            .build()
//
//            );
//        }
//        if (!manager.userExists("admin")) {
//
//
//            manager.createUser(
//                    User.withUsername("admin")
//                            .password("{noop}admin123")
//                            .roles("ADMIN")
//                            .build()
//
//            );
//        }
//
//        return manager;
//
//    }





    @Bean
    public AuthenticationProvider authenticationProvider (){
        DaoAuthenticationProvider provider =  new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }





}
