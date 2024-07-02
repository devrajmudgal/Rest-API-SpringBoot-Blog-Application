package com.example.blogApplication.service.impl;

import com.example.blogApplication.entity.Roles;
import com.example.blogApplication.entity.User;
import com.example.blogApplication.exception.BlogAPIException;
import com.example.blogApplication.payload.AuthDto;
import com.example.blogApplication.payload.RegisterDto;
import com.example.blogApplication.repository.RoleRepository;
import com.example.blogApplication.repository.UserRepository;
import com.example.blogApplication.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(AuthDto authDto) {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUserNameOrEmail(),authDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        return "Logged-In Successfully!.";
    }

    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogAPIException("Email already exists!", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new UsernameNotFoundException("User does not exist");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Roles> roles = new HashSet<>();
        Roles role = roleRepository.findByName("ROLE_USER").get();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        return "Registered Successfully";

    }
}
