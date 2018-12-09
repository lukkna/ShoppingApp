package eu.shopping.app.gateway.implementation.db;

import eu.shopping.app.gateway.api.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.util.Objects.isNull;

public class ConnectionProvider {
    private Connection connection;

    private ConnectionProvider() {
    }

    public static ConnectionProvider create() {
        return new ConnectionProvider();
    }

    public Connection getDbConnection() {
        if (isNull(connection))
            createNewConnectionToDb();
        return connection;
    }

    private void createNewConnectionToDb() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:file:./shopping", "", "");
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}