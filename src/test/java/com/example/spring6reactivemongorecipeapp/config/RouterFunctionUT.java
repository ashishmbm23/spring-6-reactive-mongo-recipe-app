package com.example.spring6reactivemongorecipeapp.config;

import com.example.spring6reactivemongorecipeapp.domain.Recipe;
import com.example.spring6reactivemongorecipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;

@ExtendWith(MockitoExtension.class)
public class RouterFunctionUT {

    @Mock
    RecipeService recipeService;

    WebTestClient webTestClient;
    @BeforeEach
    public void setUp(){
        WebConfig webConfig = new WebConfig();
        RouterFunction<?> routerFunction = webConfig.routes(recipeService);
        webTestClient = WebTestClient.bindToRouterFunction(routerFunction).build();
    }

    @Test
    public void testGetRecipe(){
        Mockito.when( recipeService.getRecipes() ).thenReturn(Flux.just(new Recipe()));

        webTestClient.get().uri("/api/recipes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Recipe.class);
    }
}
