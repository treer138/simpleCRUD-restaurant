package com.example.eatgo.application;

import com.example.eatgo.domain.*;
import com.example.eatgo.domain.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);

        mockRestaurantRespository();

        restaurantService = new RestaurantService(restaurantRepository);

    }

    private void mockRestaurantRespository() {
        List<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .categoryId(1L)
                .place("Seoul")
                .build()
                ;
        restaurants.add(restaurant);
        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));

    }

    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        Assertions.assertEquals(restaurant.getId(), 1004L);
    }

    @Test
    public void getRestaurantsWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        Assertions.assertEquals(restaurant.getId(), 1004L);
    }

    @Test
    public void getRestaurantsWithNotExisted() {
       // Restaurant restaurants = restaurantService.getRestaurant(404L);

        assertThrows(RestaurantNotFoundException.class, () ->
                restaurantService.getRestaurant(404L),
                "fuck shit");

    }

    @Test
    public  void addRestaurant(){
//       Restaurant createdRestaurant =  restaurantService.addRestaurant(new Restaurant("BeRyong", "Seoul"));
  //     Restaurant saved =  restaurantService.addRestaurant(new Restaurant("BeRyong", "Seoul"));



        //Restaurant createdRestaurant = new Restaurant("BeRyong", "Seoul");


        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1234L);
            return restaurant;
        });



        Restaurant createdRestaurant = Restaurant.builder()
                .name("BeRyong")
                .place("Seoul")
                .build();

        Restaurant saved = Restaurant.builder()
                .name("BeRyong")
                .place("Seoul")
                .id(1234L)
                .build()
                ;

      //  Restaurant saved = new Restaurant("BeRyong", "Seoul", 1234L);


        createdRestaurant = restaurantService.addRestaurant(createdRestaurant);

        Assertions.assertEquals(createdRestaurant.getId(), 1234L);

    }

    @Test void updateRestaurant() {

        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .place("Seoul")
                .build()
                ;

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant("Soul zip", "Busan", 1004L);

        Assertions.assertEquals(restaurant.getName(), "Soul zip");
        Assertions.assertEquals(restaurant.getPlace(), "Busan");
    }
}