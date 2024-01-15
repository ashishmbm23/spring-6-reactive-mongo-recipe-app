package com.example.spring6reactivemongorecipeapp.services;

import com.example.spring6reactivemongorecipeapp.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

/**
 * Created by jt on 6/28/17.
 */
public interface UnitOfMeasureService {

    Flux<UnitOfMeasureCommand> listAllUoms();
}
