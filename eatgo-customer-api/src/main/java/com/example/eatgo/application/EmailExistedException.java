package com.example.eatgo.application;

import org.apache.naming.factory.SendMailFactory;

public class EmailExistedException extends  RuntimeException {

    EmailExistedException(String email) {
        super("Email is already registered" + " " +email);
    }

}
