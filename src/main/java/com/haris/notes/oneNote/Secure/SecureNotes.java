package com.haris.notes.oneNote.Secure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecureNotes {


    @Autowired
UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        // FIX: Use a wildcard to ensure all requests to this path are permitted
                        .requestMatchers("/api/Register**").permitAll()

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


    // In com.haris.notes.oneNote.Secure.SecureNotes.java

    @Bean
    public BCryptPasswordEncoder passwordEncoder (){
        // You can also return PasswordEncoder here, but let's stick to the concrete type
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider (){
        DaoAuthenticationProvider provider =  new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }





}
