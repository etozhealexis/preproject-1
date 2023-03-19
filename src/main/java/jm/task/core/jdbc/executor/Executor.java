package jm.task.core.jdbc.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void executeUpdate(String update){
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(update);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
