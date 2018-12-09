package eu.shopping.app.usecase.implementation;

import eu.shopping.app.gateway.api.ShoppingGateway;
import eu.shopping.app.usecase.api.*;
import eu.shopping.app.usecase.implementation.converter.ShoppingRecordB2D;
import eu.shopping.app.usecase.implementation.converter.ShoppingRecordD2B;

public class ShoppingUseCaseFactoryImpl implements ShoppingUseCaseFactory {
    private final ShoppingGateway gateway;
    private final ShoppingRecordD2B converterD2B;
    private final ShoppingRecordB2D converterB2D;

    public ShoppingUseCaseFactoryImpl(ShoppingGateway gateway,
                                      ShoppingRecordD2B converterD2B,
                                      ShoppingRecordB2D converterB2D) {
        this.gateway = gateway;
        this.converterD2B = converterD2B;
        this.converterB2D = converterB2D;
    }

    @Override
    public AddShoppingRecordUseCase addShoppingRecordUseCase() {
        return new AddShoppingRecordInteractor(gateway, converterB2D);
    }

    @Override
    public GetAllShoppingRecordsUseCase getAllShoppingRecordsUseCase() {
        return new GetAllShoppingRecordsInteractor(gateway, converterD2B);
    }

    @Override
    public UpdateShoppingRecordUseCase updateShoppingRecordUseCase() {
        return new UpdateShoppingRecordInteractor(gateway, converterD2B, converterB2D);
    }

    @Override
    public DeleteShoppingRecordUseCase removeShoppingRecordUseCase() {
        return new DeleteShoppingRecordInteractor(gateway);
    }
}