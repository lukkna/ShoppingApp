package eu.shopping.app.usecase.api;

import eu.shopping.app.usecase.api.entity.BoundaryShoppingRecord;

public interface AddShoppingRecordUseCase {
    void run(BoundaryShoppingRecord record);
}