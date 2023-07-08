package com.sussel.recipe.Controller;

import com.sussel.recipe.DAO.RecipeRepository;
import com.sussel.recipe.Model.Recipe;
import com.sussel.recipe.View.RecipeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final RecipeView recipeView;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, RecipeView recipeView) {
        this.recipeRepository = recipeRepository;
        this.recipeView = recipeView;
    }

    @GetMapping("/listAll")
    public void displayAllRecipes() {
        recipeView.displayAllRecipes();
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe receita = recipeRepository.findById(id).orElse(null);
        if (receita != null) {
            return new ResponseEntity<>(receita, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Long id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if(recipe != null) {
            recipeRepository.delete(recipe);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Recipe> saveRecipe(@RequestParam("name") String name) {
        Recipe recipe = recipeRepository.save(new Recipe(name));
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }

}
