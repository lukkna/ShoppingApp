package eu.shopping.app.main.builder;

import eu.shopping.app.gateway.implementation.db.ConnectionProvider;
import eu.shopping.app.gateway.implementation.H2ShoppingGateway;
import eu.shopping.app.gateway.implementation.db.StatementExecutor;
import eu.shopping.app.usecase.api.ShoppingUseCaseFactory;
import eu.shopping.app.usecase.implementation.ShoppingUseCaseFactoryImpl;
import eu.shopping.app.usecase.implementation.converter.ShoppingRecordB2D;
import eu.shopping.app.usecase.implementation.converter.ShoppingRecordD2B;

import java.sql.Connection;

public class ShoppingUseCaseFactoryBuilder {

    private ShoppingUseCaseFactoryBuilder() {
    }

    public static ShoppingUseCaseFactoryBuilder create() {
        return new ShoppingUseCaseFactoryBuilder();
    }

    public ShoppingUseCaseFactory build() {
        Connection connection = ConnectionProvider.create().getDbConnection();
        StatementExecutor executor = new StatementExecutor(connection);
        return new ShoppingUseCaseFactoryImpl(
                new H2ShoppingGateway(executor),
                new ShoppingRecordD2B(),
                new ShoppingRecordB2D()
        );
    }
}