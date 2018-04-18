package net.ozkansari.recipe.services;

import net.ozkansari.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
