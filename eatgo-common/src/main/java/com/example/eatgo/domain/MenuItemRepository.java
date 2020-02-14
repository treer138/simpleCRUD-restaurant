package com.example.eatgo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

    List<MenuItem> findByRestaurantId(Long id);

    void deleteById(Long id);

}