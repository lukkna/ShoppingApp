package eu.shopping.app.gateway.implementation.util;

import static eu.shopping.app.gateway.implementation.util.DbConstantsProvider.*;

public class DbQueryProvider {
    public static final String CREATE;
    public static final String INSERT;
    public static final String SELECT_ALL;

    static {
        CREATE = TableBuilder.create(SHOPPING_TABLE)
                .varchar(ITEM_NAME)
                .varchar(BUYER_NAME)
                .doubl(ITEM_PRICE)
                .dateTime(DATE_TIME)
                .build();

        INSERT = QueryBuilder.insert(SHOPPING_TABLE, ITEM_NAME, BUYER_NAME, ITEM_PRICE, DATE_TIME)
                .build();

        SELECT_ALL = QueryBuilder.select().from(SHOPPING_TABLE)
                .build();
    }
}