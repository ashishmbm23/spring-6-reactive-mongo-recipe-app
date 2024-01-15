package com.example.spring6reactivemongorecipeapp.services;

import com.example.spring6reactivemongorecipeapp.commands.RecipeCommand;
import com.example.spring6reactivemongorecipeapp.domain.Recipe;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(String id);

    RecipeCommand findCommandById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(String idToDelete);
}
