package com.example.eatgo.domain;

import com.example.eatgo.domain.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    Review save(Review review);

    List<Review> findAllByRestaurantId(Long restaurantId);

    List<Review> findAll();
}
