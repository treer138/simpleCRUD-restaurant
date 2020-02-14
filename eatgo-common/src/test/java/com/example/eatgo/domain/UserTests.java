package com.example.eatgo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {

    @Test
    public void creation() {
        User user = User.builder()
                .email("test@eaxmple.com")
                .name("tester")
                .level(100L)
                .build()
                ;

        Assertions.assertEquals(user.getName(), "tester");
        Assertions.assertEquals(user.isAdmin(), true);

        user.deactivate();
        Assertions.assertEquals(user.isAdmin(), false);

    }

}