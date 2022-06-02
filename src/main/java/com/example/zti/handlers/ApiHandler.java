package com.example.zti.handlers;

import com.example.zti.dto.DishDto;
import com.example.zti.mapper.DishMapper;
import com.example.zti.model.Dish;
import com.example.zti.repository.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class ApiHandler {
    private DishRepository dishRepository;
    private DishMapper dishMapper;
    private ErrorHandler errorHandler;
    private static final String ID = "id";
    private final Function<Mono<Dish>, Mono<DishDto>> mapEntityToDto = dish -> dishMapper.mapEntityToDto(dish);
    private final Function<Mono<DishDto>, Mono<Dish>> mapDtoToEntity = entity -> dishMapper.mapDtoToEntity(entity);

    public Mono<ServerResponse> addDish(final ServerRequest request) {
        return request.bodyToMono(DishDto.class)
                .transform(mapDtoToEntity)
                .flatMap(dishRepository::save)
                .transform(mapEntityToDto)
                .transform(this::buildResponse)
                .onErrorResume(errorHandler::throwableError);
    }

    public Mono<ServerResponse> getDish(final ServerRequest request) {
        String id = request.pathVariable(ID);
        return dishRepository.findById(id)
                .transform(mapEntityToDto)
                .transform(this::buildResponse)
                .onErrorResume(errorHandler::throwableError);
    }

    public Mono<ServerResponse> deleteDish(final ServerRequest request) {
        String id = request.pathVariable(ID);
        return dishRepository.deleteById(id)
                .transform(this::buildVoidResponse)
                .onErrorResume(errorHandler::throwableError);
    }

    private Mono<ServerResponse> buildResponse(final Mono<DishDto> dishMono) {
        return dishMono.transform(this::serverResponse);
    }

    private Mono<ServerResponse> buildVoidResponse(final Mono<Void> voidMono) {
        return voidMono.transform(this::voidServerResponse);
    }

    private Mono<ServerResponse> serverResponse(final Mono<DishDto> dishMono) {
        return dishMono.flatMap(dish ->
                ServerResponse.ok().body(Mono.just(dish), DishDto.class));
    }

    private Mono<ServerResponse> voidServerResponse(final Mono<Void> voidMono) {
        return voidMono.flatMap(obj ->
                ServerResponse.ok().body(Mono.just(new DishDto()), DishDto.class));
    }
}
