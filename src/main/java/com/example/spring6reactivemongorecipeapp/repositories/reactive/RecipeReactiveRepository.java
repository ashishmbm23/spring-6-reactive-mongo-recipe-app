package com.example.spring6reactivemongorecipeapp.repositories.reactive;

import com.example.spring6reactivemongorecipeapp.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
