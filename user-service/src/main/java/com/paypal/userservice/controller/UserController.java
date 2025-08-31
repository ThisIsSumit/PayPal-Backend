package com.paypal.userservice.controller;

import com.paypal.userservice.entity.User;
import com.paypal.userservice.service.UserService;
import com.paypal.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<User> getUserId(@PathVariable Long id){
        return userService.getUserById(id).map(ResponseEntity ::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/all")
    public  ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
