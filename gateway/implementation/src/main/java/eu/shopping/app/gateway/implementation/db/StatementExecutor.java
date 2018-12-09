package eu.shopping.app.gateway.implementation.db;

import eu.shopping.app.gateway.api.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementExecutor {
    private final Connection connection;

    public StatementExecutor(Connection connection) {
        this.connection = connection;
    }

    public void execute(String... queries) {
        for (String query : queries)
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new StorageException(e);
            }
    }

    public Statement statement(String query) {
        return new Statement(query, connection);
    }
}