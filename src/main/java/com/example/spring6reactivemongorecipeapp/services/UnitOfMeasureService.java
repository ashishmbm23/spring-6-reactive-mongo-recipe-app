package com.example.spring6reactivemongorecipeapp.services;

import com.example.spring6reactivemongorecipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by jt on 6/28/17.
 */
public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
