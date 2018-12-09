package eu.shopping.app.gateway.api;

import eu.shopping.app.domain.ShoppingRecord;

import java.util.List;

public interface ShoppingGateway {
    void add(ShoppingRecord record);

    List<ShoppingRecord> get();

    void delete(long id);

    ShoppingRecord update(ShoppingRecord updated);
}