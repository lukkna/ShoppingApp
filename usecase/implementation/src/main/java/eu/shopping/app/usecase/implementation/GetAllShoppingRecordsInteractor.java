package eu.shopping.app.usecase.implementation;

import eu.shopping.app.gateway.api.ShoppingGateway;
import eu.shopping.app.usecase.api.GetAllShoppingRecordsUseCase;
import eu.shopping.app.usecase.api.entity.BoundaryShoppingRecord;
import eu.shopping.app.usecase.implementation.converter.ShoppingRecordD2B;

import java.util.List;

public class GetAllShoppingRecordsInteractor implements GetAllShoppingRecordsUseCase {
    private final ShoppingGateway gateway;
    private final ShoppingRecordD2B converter;

    public GetAllShoppingRecordsInteractor(ShoppingGateway gateway, ShoppingRecordD2B converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public List<BoundaryShoppingRecord> run() {
        return converter.convert(gateway.get());
    }
}