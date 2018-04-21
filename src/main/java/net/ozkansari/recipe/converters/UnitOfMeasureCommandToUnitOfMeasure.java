package net.ozkansari.recipe.converters;

import lombok.Synchronized;
import net.ozkansari.recipe.commands.UnitOfMeasureCommand;
import net.ozkansari.recipe.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if(unitOfMeasureCommand != null) {
            final UnitOfMeasure uom = new UnitOfMeasure();
            uom.setId(unitOfMeasureCommand.getId());
            uom.setDescription(unitOfMeasureCommand.getDescription());
            return uom;
        }
        return null;
    }
}
