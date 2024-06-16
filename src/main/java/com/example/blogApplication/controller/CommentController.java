package com.example.blogApplication.controller;

import com.example.blogApplication.payload.CommentDto;
import com.example.blogApplication.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("posts/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllCommentsByPostID(@PathVariable("postId") long Id){
        return commentService.getAllCommentsByPostId(Id);
    }

    @GetMapping("/comments")
    public List<CommentDto> getAllComments(){
        return commentService.getAllComments();
    }
}
