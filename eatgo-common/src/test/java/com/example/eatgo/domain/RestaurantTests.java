package com.example.eatgo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RestaurantTests {

    @Test
    public void creation(){
      //  Restaurant restaurant = new Restaurant("Bob zip");
        Restaurant restaurant = Restaurant.builder()
                .name("Bob zip")
                .build()
                ;
        Assertions.assertEquals(restaurant.getName(), "Bob zip");
    }

    @Test
    public void information() {
        //Restaurant restaurant = new Restaurant("Bob zip", "Seoul", 1004L);
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .place("Seoul")
                .build()
                ;

        Assertions.assertEquals(restaurant.getInformation(), "Bob zip in Seoul");
    }

}