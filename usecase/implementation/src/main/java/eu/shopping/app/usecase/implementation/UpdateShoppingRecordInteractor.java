package eu.shopping.app.usecase.implementation;

import eu.shopping.app.domain.ShoppingRecord;
import eu.shopping.app.gateway.api.ShoppingGateway;
import eu.shopping.app.usecase.api.UpdateShoppingRecordUseCase;
import eu.shopping.app.usecase.api.entity.BoundaryShoppingRecord;
import eu.shopping.app.usecase.implementation.converter.ShoppingRecordB2D;
import eu.shopping.app.usecase.implementation.converter.ShoppingRecordD2B;
import eu.shopping.app.usecase.implementation.util.StorageExceptionRethrower;

public class UpdateShoppingRecordInteractor implements UpdateShoppingRecordUseCase {
    private final ShoppingGateway gateway;
    private final ShoppingRecordD2B converterD2B;
    private final ShoppingRecordB2D converterB2D;

    public UpdateShoppingRecordInteractor(ShoppingGateway gateway,
                                          ShoppingRecordD2B converterD2B,
                                          ShoppingRecordB2D converterB2D) {
        this.gateway = gateway;
        this.converterD2B = converterD2B;
        this.converterB2D = converterB2D;
    }

    @Override
    public BoundaryShoppingRecord run(BoundaryShoppingRecord updated) {
        return StorageExceptionRethrower.run(() -> execute(updated));
    }

    private BoundaryShoppingRecord execute(BoundaryShoppingRecord updated) {
        ShoppingRecord input = converterB2D.convert(updated).orElse(null);
        ShoppingRecord output = gateway.update(input);
        return converterD2B.convert(output).orElse(null);
    }
}