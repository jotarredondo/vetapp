package com.veterinaria.vetapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager users(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin1234"))
                .roles("ADMIN")
                .build();

        UserDetails vet = User.withUsername("vet")
                .password(encoder.encode("vet1234"))
                .roles("VET")
                .build();

        UserDetails recepcion = User.withUsername("rep")
                .password(encoder.encode("rep1234"))
                .roles("RECEPCION")
                .build();

        return new InMemoryUserDetailsManager(admin, vet, recepcion);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/css/**").permitAll()
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/patients/**").hasAnyRole("ADMIN", "RECEPCION", "VET")
                        .requestMatchers("/appointments/**").hasAnyRole("ADMIN", "RECEPCION", "VET")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
