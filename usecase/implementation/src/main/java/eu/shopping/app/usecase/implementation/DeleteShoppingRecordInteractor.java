package eu.shopping.app.usecase.implementation;

import eu.shopping.app.gateway.api.ShoppingGateway;
import eu.shopping.app.usecase.api.DeleteShoppingRecordUseCase;

public class DeleteShoppingRecordInteractor implements DeleteShoppingRecordUseCase {
    private final ShoppingGateway gateway;

    public DeleteShoppingRecordInteractor(ShoppingGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void run(long id) {
        gateway.delete(id);
    }
}