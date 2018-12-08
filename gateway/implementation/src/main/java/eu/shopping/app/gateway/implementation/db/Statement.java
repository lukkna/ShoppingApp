package eu.shopping.app.gateway.implementation.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Statement {
    private final String query;
    private final Connection connection;

    public Statement(String query, Connection connection) {
        this.query = query;
        this.connection = connection;
    }

    public <R> List<R> runForList(SqlConsumer<Parameter> parameter, SqlFunction<R> resultsConsumer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            parameter.accept(new Parameter(preparedStatement));
            return executeForList(resultsConsumer, preparedStatement);
        } catch (SQLException e) {
//            throw new StorageException(e);
            return null;

        }
    }

    public <R> List<R> runForList(SqlFunction<R> resultsConsumer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return executeForList(resultsConsumer, preparedStatement);
        } catch (SQLException e) {
//            throw new StorageException(e);
            return null;
        }
    }

    private <R> List<R> executeForList(SqlFunction<R> resultsConsumer, PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            List<R> results = new ArrayList<>();
            while (resultSet.next())
                results.add(resultsConsumer.apply(resultSet));
            return results;
        }
    }

    public <R> Optional<R> run(SqlConsumer<Parameter> parameter, SqlFunction<R> resultsConsumer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            parameter.accept(new Parameter(preparedStatement));
            return executeForSingle(resultsConsumer, preparedStatement);
        } catch (SQLException e) {
//            throw new StorageException(e);
            return Optional.empty();
        }
    }

    private <R> Optional<R> executeForSingle(SqlFunction<R> resultsConsumer, PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next()
                    ? Optional.ofNullable(resultsConsumer.apply(resultSet))
                    : Optional.empty();
        }
    }

    public void run(SqlConsumer<Parameter> parameter) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            parameter.accept(new Parameter(preparedStatement));
            preparedStatement.execute();
        } catch (SQLException e) {
//            throw new StorageException(e);
        }
    }

    public int update(SqlConsumer<Parameter> parameter) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            parameter.accept(new Parameter(preparedStatement));
            preparedStatement.executeUpdate();
            return executeForUpdate(preparedStatement);
        } catch (SQLException e) {
//            throw new StorageException(e);
            return 0;

        }
    }

    private int executeForUpdate(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next())
                return (int) generatedKeys.getLong(1);
//            throw new StorageException("No update key");
            return 0;

        }
    }

    public class Parameter {
        private final PreparedStatement preparedStatement;
        private final AtomicInteger index;

        private Parameter(PreparedStatement preparedStatement) {
            this.preparedStatement = preparedStatement;
            index = new AtomicInteger(1);
        }

        public Parameter integer(int param) {
            try {
                preparedStatement.setInt(index.getAndIncrement(), param);
            } catch (SQLException e) {
//                throw new StorageException(e);
            }
            return this;
        }

        public Parameter string(String param) {
            try {
                preparedStatement.setString(index.getAndIncrement(), param);
            } catch (SQLException e) {
//                throw new StorageException(e);
            }
            return this;
        }

        public Parameter doubleVal(Double param) {
            try {
                preparedStatement.setDouble(index.getAndIncrement(), param);
            } catch (SQLException e) {
//                throw new StorageException(e);
            }
            return this;
        }

        public Parameter timestamp(Timestamp param) {
            try {
                preparedStatement.setTimestamp(index.getAndIncrement(), param);
            } catch (SQLException e) {
//                throw new StorageException(e);
            }
            return this;
        }

        public Parameter stringArray(String[] param) {
            try {
                preparedStatement.setArray(
                        index.getAndIncrement(),
                        connection.createArrayOf("VARCHAR", param)
                );
            } catch (SQLException e) {
//                throw new StorageException(e);
            }
            return this;
        }
    }

    @FunctionalInterface
    public interface SqlConsumer<T> {
        void accept(T result) throws SQLException;
    }

    @FunctionalInterface
    public interface SqlFunction<R> {
        R apply(ResultSet result) throws SQLException;
    }
}