package com.veterinaria.vetapp.controller;

import com.veterinaria.vetapp.model.User;
import com.veterinaria.vetapp.repository.UserRepository;
import com.veterinaria.vetapp.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {

        User user = userRepository.findByUsername(loginRequest.getUsername());

        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return jwtUtil.generateToken(user.getUsername());
        }

        return "LOGIN FAIL";
    }
}
