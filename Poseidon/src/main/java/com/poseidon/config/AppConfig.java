//package com.poseidon.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class AppConfig{
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//
//        httpSecurity
//                .authorizeHttpRequests(auth -> auth
//
//                    .requestMatchers("/login/**", "/oauth2/**").permitAll()
//                    .requestMatchers("/admin/**").hasAuthority("ADMIN")
//                    .requestMatchers("/user/**").hasRole("USER")
//                    .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .oauth2Login(oauth -> oauth
//                        .loginPage("/login")
//                );
//
//                return httpSecurity.build();
//    }
//}
