package com.example.blogApplication.payload;

import java.util.Date;

public class ErrorDetails {
    private Date timeStamps;
    private String message;
    private String details;

    public ErrorDetails(Date timeStamps, String message, String details) {
        this.timeStamps = timeStamps;
        this.message = message;
        this.details = details;
    }

    public Date getTimeStamps() {
        return timeStamps;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
