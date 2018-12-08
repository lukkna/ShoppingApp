package eu.shopping.app.gateway.implementation;

import eu.shopping.app.domain.ShoppingRecord;
import eu.shopping.app.gateway.api.ShoppingGateway;
import eu.shopping.app.gateway.implementation.db.StatementExecutor;
import eu.shopping.app.gateway.implementation.util.DbQueryProvider;

import java.sql.Timestamp;
import java.util.List;

import static eu.shopping.app.gateway.implementation.util.DbConstantsProvider.*;
import static eu.shopping.app.gateway.implementation.util.DbQueryProvider.CREATE;
import static eu.shopping.app.gateway.implementation.util.DbQueryProvider.INSERT;

public class H2ShoppingGateway implements ShoppingGateway {
    private final StatementExecutor executor;

    public H2ShoppingGateway(StatementExecutor executor) {
        this.executor = executor;
        executor.execute(CREATE);
    }

    @Override
    public void add(ShoppingRecord record) {
        executor.statement(INSERT).run(p -> p
                .string(record.getItem())
                .string(record.getBuyer())
                .doubleVal(record.getPrice())
                .timestamp(new Timestamp(System.currentTimeMillis())));
    }

    @Override
    public List<ShoppingRecord> get() {
        return executor.statement(DbQueryProvider.SELECT_ALL)
                .runForList(rs ->
                        new ShoppingRecord(
                                rs.getString(ITEM_NAME),
                                rs.getDouble(ITEM_PRICE),
                                rs.getString(BUYER_NAME)
                        ));
    }
}