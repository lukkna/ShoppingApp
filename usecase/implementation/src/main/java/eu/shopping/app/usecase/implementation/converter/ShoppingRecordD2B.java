package eu.shopping.app.usecase.implementation.converter;

import eu.shopping.app.domain.ShoppingRecord;
import eu.shopping.app.usecase.api.entity.BoundaryShoppingRecord;
import eu.shopping.app.utility.Converter;

public class ShoppingRecordD2B extends Converter<ShoppingRecord, BoundaryShoppingRecord> {
    @Override
    protected BoundaryShoppingRecord process(ShoppingRecord data) {
        return new BoundaryShoppingRecord(data.getItem(), data.getPrice(), data.getBuyer(), data.getTimestamp());
    }
}