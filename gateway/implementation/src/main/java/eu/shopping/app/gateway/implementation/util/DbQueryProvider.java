package eu.shopping.app.gateway.implementation.util;

import static eu.shopping.app.gateway.implementation.util.DbConstantsProvider.*;

public class DbQueryProvider {
    public static final String CREATE;
    public static final String INSERT;
    public static final String SELECT_ALL;
    public static final String DELETE;

    static {
        CREATE = TableBuilder.create(SHOPPING_TABLE)
                .varchar(ITEM_NAME)
                .varchar(BUYER_NAME)
                .doubl(ITEM_PRICE)
                .timestamp(TIMESTAMP)
                .build();

        INSERT = QueryBuilder.insert(SHOPPING_TABLE, ITEM_NAME, BUYER_NAME, ITEM_PRICE, TIMESTAMP)
                .build();

        SELECT_ALL = QueryBuilder.select().from(SHOPPING_TABLE)
                .orderBy(TIMESTAMP)
                .build();

        DELETE = QueryBuilder.delete().from(SHOPPING_TABLE)
                .where(TIMESTAMP, "=?")
                .build();
    }
}