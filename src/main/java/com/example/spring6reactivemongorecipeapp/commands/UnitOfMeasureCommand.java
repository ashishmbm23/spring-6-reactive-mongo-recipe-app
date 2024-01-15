package com.example.spring6reactivemongorecipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by jt on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasureCommand {
    private String id;
    private String description;
}
