package com.example.blogApplication.service;

import com.example.blogApplication.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    PostDto updatePostById(PostDto postDto,Long id);
}
