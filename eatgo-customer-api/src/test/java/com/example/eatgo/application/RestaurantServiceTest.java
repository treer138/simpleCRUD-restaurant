package com.example.eatgo.application;

import com.example.eatgo.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;


    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);

        mockReviewRepository();

        mockMenuItemRepository();

        mockRestaurantRespository();

        restaurantService = new RestaurantService(restaurantRepository,
                                                    menuItemRepository,
                                                    reviewRepository);

    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuitems = new ArrayList<>();
        MenuItem menuItem = MenuItem.builder()
                .name("kim")
                .build()
                ;
        menuitems.add(menuItem);
        given(menuItemRepository.findByRestaurantId(1004L)).willReturn(menuitems);
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
        given(restaurantRepository.findAllByPlaceContainingAndCategoryId("Seoul", 1L)).willReturn(restaurants);

        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));


    }

    private void mockReviewRepository() {
        //todo
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                    .id(1004L)
                    .name("ByRyong")
                    .score(1)
                    .description("Bad")
                    .build());
        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);

    }


    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findByRestaurantId(any());

        Assertions.assertEquals(restaurant.getId(), 1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        Assertions.assertEquals(menuItem.getName(), "kim");
    }

    @Test
    public void getRestaurantsWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findByRestaurantId(eq(1004L));

        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        Assertions.assertEquals(restaurant.getId(), 1004L);

        MenuItem menuItem = restaurant.getMenuItems().get(0);

        Assertions.assertEquals(menuItem.getName(), "kim");

        Review review = restaurant.getReviews().get(0);

        Assertions.assertNotNull(review);

        Assertions.assertEquals(review.getDescription(), "Bad");

        Assertions.assertEquals(review.getId(), 1004L);

    }

    @Test
    public void getRestaurantsWithNotExisted() {
       // Restaurant restaurants = restaurantService.getRestaurant(404L);

        assertThrows(RestaurantNotFoundException.class, () ->
                restaurantService.getRestaurant(404L),
                "fucking shit");

    }

    @Test
    public  void addRestaurant(){

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

    @Test
    public void getRestaurants() {

        String region = "Seoul";

        List<Restaurant> restaurants = restaurantService.getRestaurantByRegion(region, 1L);

        Restaurant restaurant = restaurants.get(0);

        Assertions.assertEquals(restaurant.getPlace(), "Seoul");
    }
}