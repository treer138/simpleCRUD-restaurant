package com.example.eatgo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    List<Restaurant> findAllByPlaceContaining(String region);

    List<Restaurant> findAllByPlaceContainingAndCategoryId(String region, long categoryId);

    Restaurant save(Restaurant restaurant);


}
