package com.example.zti.controller.api;

import com.example.zti.dto.DishDto;
import com.example.zti.model.Dish;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface DishController {
    @GetMapping(
            path = "/menu/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<DishDto> getDish(@PathVariable("id") String id);

    @GetMapping(
            path = "/menu",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Flux<Dish> getMenu();

    @PostMapping(
            path = "/menu",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    Mono<DishDto> addDish(@RequestBody DishDto dish);

    @DeleteMapping(path = "/menu/{id}")
    Mono<ServerResponse> deleteDish(@PathVariable String id);
}
