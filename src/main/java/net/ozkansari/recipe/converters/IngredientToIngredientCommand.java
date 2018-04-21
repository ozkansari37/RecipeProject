package net.ozkansari.recipe.converters;

import lombok.Synchronized;
import net.ozkansari.recipe.commands.IngredientCommand;
import net.ozkansari.recipe.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient != null) {
            IngredientCommand ingredientCommand = new IngredientCommand();
            ingredientCommand.setId(ingredient.getId());
            ingredientCommand.setAmount(ingredient.getAmount());
            ingredientCommand.setDescription(ingredient.getDescription());
            ingredientCommand.setUnitOfMeasureCommand(unitOfMeasureToUnitOfMeasureCommand.convert(ingredient.getUom()));
            return ingredientCommand;
        }
        return null;
    }
}
