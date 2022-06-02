package com.example.zti.mapper;

import com.example.zti.dto.DishDto;
import com.example.zti.model.Dish;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class DishMapper {
    private final Function<Mono<DishDto>, Mono<Dish>> transformToEntity =
            dto -> dto.flatMap(dish -> Mono.just(new Dish(dish.getName(), dish.getPrice())));

    private final Function<Mono<Dish>, Mono<DishDto>> transformToDto =
            entity -> entity.flatMap(dish ->
                    Mono.just(DishDto.builder()
                            .name(dish.getName())
                            .price(dish.getPrice())
                            .build()));

    public Mono<Dish> mapDtoToEntity(Mono<DishDto> dto) {
        return dto.transform(transformToEntity);
    }

    public Mono<DishDto> mapEntityToDto(Mono<Dish> entity) {
        return entity.transform(transformToDto);
    }
}
