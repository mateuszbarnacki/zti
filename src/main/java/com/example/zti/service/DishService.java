package com.example.zti.service;

import com.example.zti.dto.DishDto;
import com.example.zti.handlers.ErrorHandler;
import com.example.zti.mapper.DishMapper;
import com.example.zti.model.Dish;
import com.example.zti.repository.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class DishService {
    private DishRepository dishRepository;
    private DishMapper dishMapper;
    private ErrorHandler errorHandler;
    private final Function<Mono<Dish>, Mono<DishDto>> mapEntityToDto = dish -> dishMapper.mapEntityToDto(dish);
    private final Function<Mono<DishDto>, Mono<Dish>> mapDtoToEntity = entity -> dishMapper.mapDtoToEntity(entity);

    public Mono<DishDto> addDish(Mono<DishDto> dto) {
        return dto.transform(mapDtoToEntity)
                .flatMap(dishRepository::save)
                .transform(mapEntityToDto)
                .onErrorReturn(new DishDto());
    }

    public Mono<DishDto> getDish(Mono<String> id) {
        return dishRepository.findById(id)
                .transform(mapEntityToDto)
                .onErrorReturn(new DishDto());
    }

    public Flux<Dish> getMenu() {
        return dishRepository.findAll();
    }

    public Mono<ServerResponse> deleteDish(Mono<String> id) {
        return dishRepository.deleteById(id)
                .transform(this::buildVoidResponse)
                .onErrorResume(errorHandler::throwableError);
    }

    private Mono<ServerResponse> buildVoidResponse(final Mono<Void> voidMono) {
        return voidMono.transform(this::voidServerResponse);
    }

    private Mono<ServerResponse> voidServerResponse(final Mono<Void> voidMono) {
        return voidMono.flatMap(obj ->
                ServerResponse.ok().body(Mono.just(new DishDto()), DishDto.class));
    }
}
