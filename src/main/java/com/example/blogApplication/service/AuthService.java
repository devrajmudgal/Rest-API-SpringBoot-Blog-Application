package com.example.blogApplication.service;

import com.example.blogApplication.payload.AuthDto;
import com.example.blogApplication.payload.RegisterDto;

public interface AuthService {
    String login(AuthDto authDto);
    String register(RegisterDto registerDto);
}
