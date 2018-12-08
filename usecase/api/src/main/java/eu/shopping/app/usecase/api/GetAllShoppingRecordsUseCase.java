package eu.shopping.app.usecase.api;

import eu.shopping.app.usecase.api.entity.BoundaryShoppingRecord;

import java.util.List;

public interface GetAllShoppingRecordsUseCase {
    List<BoundaryShoppingRecord> run();
}