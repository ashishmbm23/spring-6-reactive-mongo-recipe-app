package com.example.spring6reactivemongorecipeapp.repositories.reactive;

import com.example.spring6reactivemongorecipeapp.domain.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String> {
}
