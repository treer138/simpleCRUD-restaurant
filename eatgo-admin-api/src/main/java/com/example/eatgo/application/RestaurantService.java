package com.example.eatgo.application;

import com.example.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;

    }

    public Restaurant getRestaurant(Long id) {

        Restaurant res = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));

        return res;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

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
