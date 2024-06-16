package com.example.blogApplication.controller;

import com.example.blogApplication.payload.PostDto;
import com.example.blogApplication.payload.PostResponse;
import com.example.blogApplication.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize
    ){
        return new ResponseEntity<>(postService.getAllPosts(pageNo,pageSize),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long ID){
        return ResponseEntity.ok(postService.getPostById(ID));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDtoObject,@PathVariable(name = "id") Long postId){
        return new ResponseEntity<>(postService.updatePostById(postDtoObject,postId),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id){
        postService.DeletePostById(id);
        String message = "Post with Id: "+id+" Successfully Deleted";
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

}
