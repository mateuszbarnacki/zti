package com.example.zti.router;

import com.example.zti.handlers.ApiHandler;
import com.example.zti.handlers.ErrorHandler;
import com.example.zti.model.Dish;
import com.example.zti.repository.DishRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
@AllArgsConstructor
public class ApiRouter {
    private static final String API_PATH = "/router";
    private static final String MENU_PATH = "/menu";
    private static final String ARG = "/{id}";
    private static final String MENU_WITH_ARG_PATH = MENU_PATH + ARG;
    private DishRepository dishRepository;

    @Bean
    public RouterFunction<?> doRoute(final ApiHandler handler, final ErrorHandler errorHandler) {
        return nest(path(API_PATH),
                nest(accept(MediaType.APPLICATION_JSON),
                        route(GET(MENU_WITH_ARG_PATH), handler::getDish)
                                .andRoute(POST(MENU_PATH), handler::addDish)
                                .andRoute(DELETE(MENU_WITH_ARG_PATH), handler::deleteDish)
                                .andRoute(GET(MENU_PATH),
                                        req -> ServerResponse.ok().body(dishRepository.findAll(), Dish.class))
                ).andOther(route(RequestPredicates.all(), errorHandler::notFound)));
    }
}
