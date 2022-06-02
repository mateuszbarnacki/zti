package com.example.zti.controller.impl;

import com.example.zti.controller.api.DishController;
import com.example.zti.dto.DishDto;
import com.example.zti.model.Dish;
import com.example.zti.service.DishService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller")
@AllArgsConstructor
public class DishControllerImpl implements DishController {
    private DishService dishService;

    @Override
    public Mono<DishDto> getDish(String id) {
        return dishService.getDish(Mono.just(id));
    }

    @Override
    public Flux<Dish> getMenu() {
        return dishService.getMenu();
    }

    @Override
    public Mono<DishDto> addDish(DishDto dish) {
        return dishService.addDish(Mono.just(dish));
    }

    @Override
    public Mono<ServerResponse> deleteDish(String id) {
        return dishService.deleteDish(Mono.just(id));
    }
}
