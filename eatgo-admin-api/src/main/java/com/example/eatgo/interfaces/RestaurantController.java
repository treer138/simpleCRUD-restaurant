package com.example.eatgo.interfaces;

import com.example.eatgo.application.RestaurantService;
import com.example.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {

        return restaurantService.getRestaurants();
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        Restaurant res = restaurantService.getRestaurant(id);

        return res;

    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant rescource) throws URISyntaxException {


        Restaurant restaurant = Restaurant.builder()
                .name(rescource.getName())
                .place(rescource.getPlace())
                .menuItems(rescource.getMenuItems())
                .reviews(rescource.getReviews())
                .id(rescource.getId())
                .build()
                ;

        restaurantService.addRestaurant(restaurant);

        URI location = new URI("/restaurants/"+restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/restaurants/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestBody Restaurant restaurant) {
        String name = restaurant.getName();
        String place =restaurant.getPlace();

        restaurantService.updateRestaurant(name, place, id);

        return"{}";
    }
}
