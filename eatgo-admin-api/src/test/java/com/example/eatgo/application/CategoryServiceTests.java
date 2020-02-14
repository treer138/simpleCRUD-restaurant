package com.example.eatgo.application;

import com.example.eatgo.domain.Category;
import com.example.eatgo.domain.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CategoryServiceTests {


    @Autowired
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void serUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getCategory() {
        List<Category> mockcategories = new ArrayList<>();
        mockcategories.add(Category.builder().name("Korean Food").build());

        given(categoryRepository.findAll()).willReturn(mockcategories);

        List<Category> categories = categoryService.getCategories();

        Category category = categories.get(0);
        Assertions.assertEquals(category.getName(), "Korean Food");

    }

    @Test
    public void addCategory() {
        Category category = categoryService.addCategory("Korean Food");

        Assertions.assertEquals(category.getName(), "Korean Food");

        verify(categoryRepository).save(any());
    }

}