package com.example.eatgo.interfaces;

import com.example.eatgo.application.ReviewService;
import com.example.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(
            @Valid @RequestBody Review recourse,
            @PathVariable("restaurantId") Long id) throws URISyntaxException {

        Review reveiw = reviewService.addReview(id, recourse);
        String url = "/restaurants/"+id+"/reviews/"+reveiw.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
