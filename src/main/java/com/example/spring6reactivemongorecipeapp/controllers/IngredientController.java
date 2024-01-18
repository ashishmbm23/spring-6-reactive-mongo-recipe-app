package com.example.spring6reactivemongorecipeapp.controllers;

import com.example.spring6reactivemongorecipeapp.commands.IngredientCommand;
import com.example.spring6reactivemongorecipeapp.commands.RecipeCommand;
import com.example.spring6reactivemongorecipeapp.commands.UnitOfMeasureCommand;
import com.example.spring6reactivemongorecipeapp.services.IngredientService;
import com.example.spring6reactivemongorecipeapp.services.RecipeService;
import com.example.spring6reactivemongorecipeapp.services.UnitOfMeasureService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by jt on 6/28/17.
 */
@Slf4j
@Controller
public class IngredientController {

    public static final String RECIPE_INGREDIENT_INGREDIENTFORM = "recipe/ingredient/ingredientform";
    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe( @PathVariable String recipeId, Model model){

        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId).block();
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return RECIPE_INGREDIENT_INGREDIENTFORM;
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return RECIPE_INGREDIENT_INGREDIENTFORM;
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@Valid @ModelAttribute("ingredient") IngredientCommand ingredient,
                               BindingResult result,
                               Model model){
        if( result.hasErrors() ){
            result.getAllErrors().forEach( objectError -> {
                log.debug( objectError.toString() );
            });
            model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
             return RECIPE_INGREDIENT_INGREDIENTFORM;
        }

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredient).block();

        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id){

        log.debug("deleting ingredient id:" + id);
        ingredientService.deleteById(recipeId, id).block();

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
