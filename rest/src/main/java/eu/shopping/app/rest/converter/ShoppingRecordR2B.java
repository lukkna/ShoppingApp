package eu.shopping.app.rest.converter;

import eu.shopping.app.rest.entity.RestShoppingRecord;
import eu.shopping.app.usecase.api.entity.BoundaryShoppingRecord;
import eu.shopping.app.utility.Converter;

public class ShoppingRecordR2B extends Converter<RestShoppingRecord, BoundaryShoppingRecord> {
    @Override
    protected BoundaryShoppingRecord process(RestShoppingRecord data) {
        return new BoundaryShoppingRecord(data.getItem(), data.getPrice(), data.getBuyer());
    }
}