package com.example.eatgo.interfaces;

import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.Region;
import com.example.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurantsAll")
    public List<Restaurant> listAll() {

        return restaurantService.getRestaurantNotRegion();
    }

    @GetMapping("/restaurants")
    public List<Restaurant> listByRegion(@RequestParam("region") String region,
                                         @RequestParam("category") Long categoryId
    ) {

        return restaurantService.getRestaurantByRegion(region, categoryId);
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {


        Restaurant res = restaurantService.getRestaurant(id);

        return res;

    }
}
