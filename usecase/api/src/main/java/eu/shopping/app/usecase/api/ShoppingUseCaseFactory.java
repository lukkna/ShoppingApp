package eu.shopping.app.usecase.api;

public interface ShoppingUseCaseFactory {
    AddShoppingRecordUseCase addShoppingRecordUseCase();

    GetAllShoppingRecordsUseCase getAllShoppingRecordsUseCase();
}