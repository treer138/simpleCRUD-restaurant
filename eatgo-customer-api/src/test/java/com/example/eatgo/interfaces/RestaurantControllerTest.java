package com.example.eatgo.interfaces;

import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.MenuItem;
import com.example.eatgo.domain.Restaurant;
import com.example.eatgo.domain.RestaurantNotFoundException;
import com.example.eatgo.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;


    @Test
    public void list() throws Exception {
        List<Restaurant> restaurant = new ArrayList<>();

        Restaurant res = Restaurant.builder()
                .name("Bob zip")
                .categoryId(1L)
                .place("Seoul")
                .id(1004L)
                .build()
                ;
        restaurant.add(res);

        given(restaurantService.getRestaurantByRegion("Seoul", 1L))
                .willReturn(restaurant);

       mvc.perform(get("/restaurants?region=Seoul&category=1"))
               .andExpect(status().isOk())
               .andExpect(content().string(
                       containsString("\"name\":\"Bob zip\"")));
    }

    @Test
    public void detail() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .name("Bob zip")
                .place("Seoul")
                .id(1004L)
                .build()
                ;

        MenuItem menuItem = MenuItem.builder()
                .name("kim")
                .build()
                ;

        restaurant.setMenuItems(Arrays.asList(menuItem));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")))
                .andExpect(content().string(
                        containsString("kim")
                ))
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ));
    }

    @Test
    public void detailWithExisted() throws  Exception{
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Joker House")
                .place("Seoul")
                .build()
                ;
        MenuItem menuItem = MenuItem.builder()
                .name("KimChi")
                .build()
                ;
        restaurant.setMenuItems(Arrays.asList(menuItem));
        Review review = Review.builder()
                .name("Joker")
                .score(5)
                .description("Great!")
                .build()
                ;

        restaurant.setReviews(Arrays.asList(review));


        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("KimChi")
                ))
                .andExpect(content().string(
                        containsString("Great!")
                ));
    }

    @Test
    public void detailWithNotExisted() throws Exception{
        given(restaurantService.getRestaurant(404L))
                .willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));

    }
}