package com.example.blogApplication.service.impl;

import com.example.blogApplication.entity.Post;
import com.example.blogApplication.exception.ResourceNotFoundException;
import com.example.blogApplication.payload.PostDto;
import com.example.blogApplication.payload.PostResponse;
import com.example.blogApplication.repository.PostRepository;
import com.example.blogApplication.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public PostResponse getAllPosts(int page_no, int page_size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PostResponse postResponse = new PostResponse();
        Pageable pageable = PageRequest.of(page_no,page_size,sort);
        Page<Post> post = postRepository.findAll(pageable);

        List<Post> postList = post.getContent();
        List<PostDto> content = postList.stream().map(postnew->convertToPostDTO(postnew)).collect(Collectors.toList());
        postResponse.setContent(content);
        postResponse.setPageNo(post.getNumber());
        postResponse.setPageSize(post.getSize());
        postResponse.setTotalPages(post.getTotalPages());
        postResponse.setTotalElements(post.getTotalElements());
        postResponse.setLast(post.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post postById = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));
        return convertToPostDTO(postById);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","Id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        PostDto newPostDTOResponse = convertToPostDTO(updatedPost);
        return newPostDTOResponse;
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

    @Override
    public void DeletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Id",id));
        postRepository.delete(post);
    }
}
