package com.javamaster.spring_crud.controller;

import com.javamaster.spring_crud.entity.User;
import com.javamaster.spring_crud.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthServiceImpl authService;

    @PostMapping(value = "/signIn", consumes = "application/json", produces = "application/json")
    public ResponseEntity signIn(@RequestBody AuthRequest request) {
        try {
            return new ResponseEntity<>(authService.signIn(request), HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping(value = "/signUp", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> signUp(@RequestBody AuthRequest request) {
        User user = authService.signUp(request);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}