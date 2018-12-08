package eu.shopping.app.usecase.implementation.converter;

import eu.shopping.app.domain.ShoppingRecord;
import eu.shopping.app.usecase.api.entity.BoundaryShoppingRecord;
import eu.shopping.app.utility.Converter;

public class ShoppingRecordB2D extends Converter<BoundaryShoppingRecord, ShoppingRecord> {
    @Override
    protected ShoppingRecord process(BoundaryShoppingRecord data) {
        return new ShoppingRecord(data.getItem(), data.getPrice(), data.getBuyer());
    }
}