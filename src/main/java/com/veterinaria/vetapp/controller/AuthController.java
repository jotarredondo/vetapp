package com.veterinaria.vetapp.controller;

import com.veterinaria.vetapp.jwt.JWTUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final JWTUtil jwtUtil;

    public AuthController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        if (username.equals("admin") && password.equals("admin1234")) {
            return jwtUtil.generateToken(username);
        }

        return "LOGIN FAIL";
    }
}
