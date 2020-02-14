package com.example.eatgo.application;

public class EmailNotExistedException extends RuntimeException {

    public EmailNotExistedException() {
        super("Email is Not registered");
    }
}
