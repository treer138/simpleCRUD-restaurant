package com.example.eatgo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTests {

    @Test
    public void creation() {
        Category category = Category.builder().name("Korean Food").build();

        Assertions.assertEquals(category.getName(), "Korean Food");
    }
}