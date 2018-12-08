package eu.shopping.app.rest.converter;

import eu.shopping.app.rest.entity.RestShoppingRecord;
import eu.shopping.app.usecase.api.entity.BoundaryShoppingRecord;
import eu.shopping.app.utility.Converter;

public class ShoppingRecordB2R extends Converter<BoundaryShoppingRecord, RestShoppingRecord> {
    @Override
    protected RestShoppingRecord process(BoundaryShoppingRecord data) {
        return new RestShoppingRecord(data.getItem(), data.getPrice(), data.getBuyer(), data.getId());
    }
}