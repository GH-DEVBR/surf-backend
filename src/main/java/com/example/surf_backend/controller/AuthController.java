package com.example.surf_backend.controller;

import com.example.surf_backend.model.User;
import com.example.surf_backend.repository.UserRepository;
import com.example.surf_backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        user.setSenha(encoder.encode(user.getSenha()));
        return ResponseEntity.ok(userRepo.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        var u = userRepo.findByEmail(user.getEmail()).orElseThrow();
        if (encoder.matches(user.getSenha(), u.getSenha())) {
            String token = jwtUtil.generateToken(u.getEmail());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).build();
    }
}

