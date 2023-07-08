package com.sussel.recipe.Controller;

import com.sussel.recipe.DAO.RecipeRepository;
import com.sussel.recipe.Model.Recipe;
import com.sussel.recipe.Service.RecipeService;
import com.sussel.recipe.View.RecipeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeView recipeView;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeView recipeView) {
        this.recipeService = recipeService;
        this.recipeView = recipeView;
    }

    @GetMapping("/listAll")
    public void displayAllRecipes() {
        recipeView.displayAllRecipes();
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        if (recipe != null) {
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        if(recipe != null) {
            recipeService.deleteRecipe(recipe);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Recipe> saveRecipe(@RequestParam("name") String name) {
        Recipe recipe = recipeService.saveRecipe(new Recipe(name));
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

}
