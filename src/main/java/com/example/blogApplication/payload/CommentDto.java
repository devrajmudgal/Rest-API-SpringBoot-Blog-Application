package com.example.blogApplication.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Name must not be Null or Empty")
    private String name;
    @NotEmpty(message = "Email must not be Null or Empty")
    @Email
    private String email;
    @NotEmpty(message = "Comment Body must not be Null or Empty")
    @Size(min = 10,message = "Comment Body Must Be at least 10 Characters long")
    private String body;
}
