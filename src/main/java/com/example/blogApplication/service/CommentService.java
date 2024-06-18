package com.example.blogApplication.service;

import com.example.blogApplication.entity.Comment;
import com.example.blogApplication.payload.CommentDto;
import com.example.blogApplication.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {

    CommentDto createComment(long PostId,CommentDto commentDto);

    List<CommentDto> getAllCommentsByPostId(Long post_Id);

    List<CommentDto> getAllComments();

    CommentDto getCommentById(Long post_id, Long comment_id);

    CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentDto);

    void deleteComment(Long postId,Long commentId);
}
