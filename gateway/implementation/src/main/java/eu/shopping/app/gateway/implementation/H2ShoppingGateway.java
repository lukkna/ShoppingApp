package eu.shopping.app.gateway.implementation;

import eu.shopping.app.domain.ShoppingRecord;
import eu.shopping.app.gateway.api.ShoppingGateway;
import eu.shopping.app.gateway.implementation.db.StatementExecutor;

import java.sql.Timestamp;
import java.util.List;

import static eu.shopping.app.gateway.implementation.util.DbConstantsProvider.*;
import static eu.shopping.app.gateway.implementation.util.DbQueryProvider.*;

public class H2ShoppingGateway implements ShoppingGateway {
    private final StatementExecutor executor;

    public H2ShoppingGateway(StatementExecutor executor) {
        this.executor = executor;
        executor.execute(CREATE);
    }

    @Override
    public void add(ShoppingRecord record) {
        add(record, new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public List<ShoppingRecord> get() {
        return executor.statement(SELECT_ALL)
                .runForList(rs ->
                        new ShoppingRecord(
                                rs.getString(ITEM_NAME),
                                rs.getDouble(ITEM_PRICE),
                                rs.getString(BUYER_NAME),
                                rs.getTimestamp(TIMESTAMP).getTime()
                        ));
    }

    @Override
    public void delete(long id) {
        executor.statement(DELETE)
                .run(p -> p.timestamp(new Timestamp(id)));
    }

    @Override
    public ShoppingRecord update(ShoppingRecord updated) {
        delete(updated.getTimestamp());
        add(updated, new Timestamp(updated.getTimestamp()));
        return updated;
    }

    private void add(ShoppingRecord record, Timestamp timestamp) {
        executor.statement(INSERT)
                .run(p -> p
                        .string(record.getItem())
                        .string(record.getBuyer())
                        .doubleVal(record.getPrice())
                        .timestamp(timestamp));
    }
}