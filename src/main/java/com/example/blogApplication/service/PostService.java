package com.example.blogApplication.service;

import com.example.blogApplication.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
