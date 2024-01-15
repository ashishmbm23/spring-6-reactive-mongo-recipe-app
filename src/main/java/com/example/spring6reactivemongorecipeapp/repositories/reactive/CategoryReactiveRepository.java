package com.example.spring6reactivemongorecipeapp.repositories.reactive;

import com.example.spring6reactivemongorecipeapp.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {
    Mono<Category> findByDescription(String description);
}
