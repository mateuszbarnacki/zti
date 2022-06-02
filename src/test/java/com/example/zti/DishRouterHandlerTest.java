package com.example.zti;

import com.example.zti.dto.DishDto;
import com.example.zti.model.Dish;
import com.example.zti.repository.DishRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DishRouterHandlerTest {
    @Autowired
    private WebTestClient client;
    @Autowired
    private DishRepository repository;

    @Test
    public void routerPathTest() {
        Mono<Dish> repoDish = repository.findById("62976e4bc1b36fad206dc290");
        Flux<DishDto> routerDish = client.get().uri("/router/menu/62976e4bc1b36fad206dc290")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(DishDto.class).getResponseBody();

        Assert.assertEquals(repoDish.map(Dish::getName).block(), routerDish.map(DishDto::getName).blockFirst());
        Assert.assertEquals(repoDish.map(Dish::getPrice).block(), routerDish.map(DishDto::getPrice).blockFirst());
    }
}
