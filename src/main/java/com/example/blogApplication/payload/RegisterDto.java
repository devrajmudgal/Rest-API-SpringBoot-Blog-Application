package com.example.blogApplication.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty
    @Size(min = 2,message = "Name should be at least 2 Character")
    private String name;
    @NotEmpty
    @Size(min = 5,message = "Message should be at least 5 character")
    private String username;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8,message = "Password length should be 8 of length at least")
    private String password;
}
