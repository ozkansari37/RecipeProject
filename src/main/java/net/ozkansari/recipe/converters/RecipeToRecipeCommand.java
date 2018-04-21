package net.ozkansari.recipe.converters;

import lombok.Synchronized;
import net.ozkansari.recipe.commands.RecipeCommand;
import net.ozkansari.recipe.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {


    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryToCategoryCommand, IngredientToIngredientCommand ingredientToIngredientCommand, NotesToNotesCommand notesToNotesCommand) {
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.notesToNotesCommand = notesToNotesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe != null) {
            final RecipeCommand recipeCommand = new RecipeCommand();
            recipeCommand.setId(recipe.getId());
            recipeCommand.setDescription(recipe.getDescription());
            recipeCommand.setCookTime(recipe.getCookTime());
            recipeCommand.setPrepTime(recipe.getPrepTime());
            recipeCommand.setDifficulty(recipe.getDifficulty());
            recipeCommand.setDirections(recipe.getDirections());
            recipeCommand.setServings(recipe.getServings());
            recipeCommand.setSource(recipe.getSource());
            recipeCommand.setUrl(recipe.getUrl());
            recipeCommand.setNotesCommand(notesToNotesCommand.convert(recipe.getNotes()));

            if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
                recipe.getCategories()
                        .forEach(category -> recipeCommand.getCategoryCommands().add(categoryToCategoryCommand.convert(category)));
            }

            if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
                recipe.getIngredients()
                        .forEach(ingredient -> recipeCommand.getIngredientCommands().add(ingredientToIngredientCommand.convert(ingredient)));
            }

            return recipeCommand;
        }
        return null;
    }
}
