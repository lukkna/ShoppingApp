package eu.shopping.app.usecase.implementation;

import eu.shopping.app.gateway.api.ShoppingGateway;
import eu.shopping.app.usecase.api.AddShoppingRecordUseCase;
import eu.shopping.app.usecase.api.entity.BoundaryShoppingRecord;
import eu.shopping.app.usecase.implementation.converter.ShoppingRecordB2D;

public class AddShoppingRecordInteractor implements AddShoppingRecordUseCase {
    private final ShoppingGateway gateway;
    private final ShoppingRecordB2D converter;

    public AddShoppingRecordInteractor(ShoppingGateway gateway, ShoppingRecordB2D converter) {
        this.gateway = gateway;
        this.converter = converter;
    }

    @Override
    public void run(BoundaryShoppingRecord record) {
        converter.convert(record).ifPresent(gateway::add);
        //todo response about unsuccessful op
    }
}