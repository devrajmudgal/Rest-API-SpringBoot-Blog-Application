package com.example.blogApplication.service;

import com.example.blogApplication.payload.PostDto;
import com.example.blogApplication.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNum, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePostById(PostDto postDto,Long id);

    void DeletePostById(Long id);
}
