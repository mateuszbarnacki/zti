package com.example.zti.repository;

import com.example.zti.model.Dish;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends ReactiveMongoRepository<Dish, String> {

}
