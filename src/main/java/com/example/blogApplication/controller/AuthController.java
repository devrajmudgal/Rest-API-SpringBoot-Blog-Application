package com.example.blogApplication.controller;

import com.example.blogApplication.payload.AuthDto;
import com.example.blogApplication.payload.RegisterDto;
import com.example.blogApplication.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login","/sign-in"})
    public ResponseEntity<String> login(@Valid @RequestBody AuthDto authDto){
        String response = authService.login(authDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = {"/register","sign-up"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return ResponseEntity.ok(response);
    }
}
