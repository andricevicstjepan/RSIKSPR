package com.hr.fer.rsikspr.FirstAssignment.controllers;

import com.hr.fer.rsikspr.FirstAssignment.models.User;
import com.hr.fer.rsikspr.FirstAssignment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id){
        return userRepository.findById(id).map(user -> new ResponseEntity<>(user,HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return new ResponseEntity<>(userRepository.save(user),HttpStatus.ACCEPTED);
    }
}
