package net.ozkansari.recipe.converters;

import lombok.Synchronized;
import net.ozkansari.recipe.commands.RecipeCommand;
import net.ozkansari.recipe.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryCommandToCategory;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandToCategory, IngredientCommandToIngredient ingredientCommandToIngredient, NotesCommandToNotes notesCommandToNotes) {
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.notesCommandToNotes = notesCommandToNotes;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand != null) {
            final Recipe recipe = new Recipe();
            recipe.setId(recipeCommand.getId());
            recipe.setCookTime(recipeCommand.getCookTime());
            recipe.setPrepTime(recipeCommand.getPrepTime());
            recipe.setDescription(recipeCommand.getDescription());
            recipe.setDifficulty(recipeCommand.getDifficulty());
            recipe.setServings(recipeCommand.getServings());
            recipe.setSource(recipeCommand.getSource());
            recipe.setUrl(recipeCommand.getUrl());
            recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotesCommand()));

            if(recipeCommand.getCategoryCommands() != null && recipeCommand.getCategoryCommands().size() > 0) {
                recipeCommand.getCategoryCommands()
                        .forEach(categoryCommand -> recipe.getCategories().add(categoryCommandToCategory.convert(categoryCommand)));
            }

            if(recipeCommand.getIngredientCommands() != null && recipeCommand.getIngredientCommands().size() > 0) {
                recipeCommand.getIngredientCommands()
                        .forEach(ingredientCommand -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredientCommand)));
            }

            return recipe;
        }
        return null;
    }
}
