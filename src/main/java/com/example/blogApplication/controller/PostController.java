package com.example.blogApplication.controller;

import com.example.blogApplication.payload.PostDto;
import com.example.blogApplication.payload.PostResponse;
import com.example.blogApplication.service.PostService;
import com.example.blogApplication.utils.BlogApplicationConstant;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = BlogApplicationConstant.DEFAULT_PAGE_NO,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = BlogApplicationConstant.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = BlogApplicationConstant.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = BlogApplicationConstant.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return new ResponseEntity<>(postService.getAllPosts(pageNo,pageSize,sortBy,sortDir),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long ID){
        return ResponseEntity.ok(postService.getPostById(ID));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDtoObject, @PathVariable(name = "id") Long postId){
        return new ResponseEntity<>(postService.updatePostById(postDtoObject,postId),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id){
        postService.DeletePostById(id);
        String message = "Post with Id: "+id+" Successfully Deleted";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
