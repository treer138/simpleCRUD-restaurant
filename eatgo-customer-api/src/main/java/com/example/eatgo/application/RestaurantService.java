package com.example.eatgo.application;

import com.example.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuItemRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuItemRepository menuItemRepository,
                             ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.reviewRepository = reviewRepository;
    }

    public Restaurant getRestaurant(Long id) {

        Restaurant res = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));

        List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(id);
        res.setMenuItems(menuItems);

        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
        res.setReviews(reviews);


        return res;
    }

    public List<Restaurant> getRestaurantNotRegion() {

        List<Restaurant> restaurants =
                restaurantRepository.findAll();

        return restaurants;
    }

    public List<Restaurant> getRestaurantByRegion(String region, Long categoryId) {
        //Todo region filltering

        List<Restaurant> restaurants =
                restaurantRepository.findAllByPlaceContainingAndCategoryId(region, categoryId);

        return restaurants;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {

        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant updateRestaurant(String name, String place, long id) {
        //Todo update Restaurant gg

       return restaurantRepository.findById(id)
                .map(restaurant1 -> {
                    restaurant1.setName(name);
                    restaurant1.setPlace(place);
                    return restaurant1;
                })
                .orElse(null);

    }
}
