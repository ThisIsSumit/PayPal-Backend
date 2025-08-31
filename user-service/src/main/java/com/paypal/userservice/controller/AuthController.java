package com.paypal.userservice.controller;

import com.paypal.userservice.dto.JwtResponse;
import com.paypal.userservice.dto.LoginRequest;
import com.paypal.userservice.dto.SignupRequest;
import com.paypal.userservice.entity.User;
import com.paypal.userservice.repository.UserRepository;
import com.paypal.userservice.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  JWTUtil jwtUtil;



    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request){
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("⚠️ User already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole("ROLE_USER");  // Normal users only!
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        // Save the new user
        User savedUser = userRepository.save(user);

        return ResponseEntity.ok("✅ User registered successfully");
    }





    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("❌ User not found");
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("❌ Invalid credentials");
        }

        // Generate token with claims
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());

        return ResponseEntity.ok(new JwtResponse(token));
    }

}