package com.example.blogApplication.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {
    @NotEmpty
    private String userNameOrEmail;
    @NotEmpty
    private String password;
}
