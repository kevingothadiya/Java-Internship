package com.example.securitydemo.configuration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class Security {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) {

        security.csrf(csrf -> csrf.disable());
        security.authorizeHttpRequests(
                auth ->
                auth.requestMatchers("/student/verify","/student/add-data").permitAll()
                .anyRequest().authenticated());
        security.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        security.formLogin(Customizer.withDefaults());
        security.httpBasic(Customizer.withDefaults());
        return security.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //@Bean
//    UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails user1 = User
//                .builder()
//                .username("parth")
//                .password(encoder.encode("pass"))
//                .build();
//        UserDetails user2 = User
//                .builder()
//                .username("nigam")
//                .password(encoder.encode("pass"))
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
}
