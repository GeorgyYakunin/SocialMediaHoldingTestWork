package com.example.socialmediaholdingtestwork;

public class Message {
    String message;
//    User sender;
    String createdAt;
    boolean isReceived;

    public Message(String message, String createdAt, boolean isReceived){
        this.message = message;
//        this.sender = sender;
        this.createdAt = createdAt;
        this.isReceived = isReceived;
    }

    public String getMessage () {
        return message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean getIsReceived() {
        return isReceived;
    }
}
