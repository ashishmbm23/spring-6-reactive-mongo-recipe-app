package com.example.spring6reactivemongorecipeapp.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by jt on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private String id;
    private String recipeId;
    @NotBlank
    private String description;
    @Min(1)
    @NotNull
    private BigDecimal amount;
    @NotNull
    private UnitOfMeasureCommand uom;
}
