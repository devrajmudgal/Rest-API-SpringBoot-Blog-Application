package com.example.blogApplication.service.impl;

import com.example.blogApplication.entity.Post;
import com.example.blogApplication.payload.PostDto;
import com.example.blogApplication.repository.PostRepository;
import com.example.blogApplication.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = convertToPost(postDto);

        Post newPost = postRepository.save(post);

        PostDto postResponse = convertToPostDTO(newPost);
        
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }

    private PostDto convertToPostDTO(Post newPost){
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());
        return postResponse;
    }

    private Post convertToPost(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
