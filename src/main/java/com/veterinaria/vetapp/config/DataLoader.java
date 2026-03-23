package com.veterinaria.vetapp.config;

import com.veterinaria.vetapp.model.User;
import com.veterinaria.vetapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {

            if (userRepository.count() == 0) {
                userRepository.save(new User("admin", "admin123"));
                userRepository.save(new User("vet", "vet123"));
                userRepository.save(new User("recepcion", "recepcion123"));
            }
        };
    }
}
