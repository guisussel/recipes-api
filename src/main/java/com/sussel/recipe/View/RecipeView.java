package com.sussel.recipe.View;

import com.sussel.recipe.Model.Recipe;
import com.sussel.recipe.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeView {

    private final RecipeService recipeService;

    @Autowired
    public RecipeView(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public void displayAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        if (!recipes.isEmpty()) {
            System.out.println("Recipes list:");
            for (Recipe recipe : recipes) {
                System.out.println("ID: " + recipe.getId());
                System.out.println("Name: " + recipe.getName());
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("No recipe found in database.");
        }
    }

}
