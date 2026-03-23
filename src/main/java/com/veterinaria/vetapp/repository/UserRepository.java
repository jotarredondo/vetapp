package com.veterinaria.vetapp.repository;

import com.veterinaria.vetapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
