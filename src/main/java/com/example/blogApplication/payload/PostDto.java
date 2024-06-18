package com.example.blogApplication.payload;

import com.example.blogApplication.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;

    @NotEmpty
    @Size(min = 5,message = "Title Size should be atleast 5 characters")
    private String title;

    @NotEmpty
    @Size(min = 15,message = "Description should be atleast 15 characters long")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
