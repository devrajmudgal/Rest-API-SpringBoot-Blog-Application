package com.example.blogApplication.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public BlogAPIException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public BlogAPIException(String message, String message1, HttpStatus httpStatus) {
        super(message);
        this.message = message1;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
