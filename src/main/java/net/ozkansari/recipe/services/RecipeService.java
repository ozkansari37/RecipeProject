package net.ozkansari.recipe.services;

import net.ozkansari.recipe.commands.RecipeCommand;
import net.ozkansari.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
