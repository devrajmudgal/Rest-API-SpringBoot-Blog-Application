package com.example.blogApplication.service.impl;

import com.example.blogApplication.entity.Comment;
import com.example.blogApplication.entity.Post;
import com.example.blogApplication.exception.ResourceNotFoundException;
import com.example.blogApplication.payload.CommentDto;
import com.example.blogApplication.repository.CommentRepository;
import com.example.blogApplication.repository.PostRepository;
import com.example.blogApplication.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long id, CommentDto commentDto) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","Post_Id",id));
        Comment comment = CommentDtoToComment(commentDto);
        comment.setPost(post);
        Comment newComment =commentRepository.save(comment);
        return commentToCommentDto(newComment);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> newResponseComments = comments.stream().map(com->commentToCommentDto(com)).collect(Collectors.toList());
        return newResponseComments;
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(Long post_Id) {
        Post post = postRepository.findById(post_Id).orElseThrow(()->new ResourceNotFoundException("Post","Post_Id",post_Id));

        List<Comment> comments = commentRepository.findByPostId(post_Id);
        return comments.stream().map(com ->commentToCommentDto(com)).collect(Collectors.toList());
    }

    private CommentDto commentToCommentDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment CommentDtoToComment(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
