package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.executor.Executor;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    private final Executor executor;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
        this.executor = new Executor(connection);
    }

    public void createUsersTable() {
        executor.executeUpdate("CREATE TABLE IF NOT EXISTS users\n" +
                "(\n" +
                "    id         bigint generated always as identity\n" +
                "        constraint id\n" +
                "            primary key,\n" +
                "    name       varchar(40) not null,\n" +
                "    \"lastname\" varchar(40) not null,\n" +
                "    age        smallint       not null\n" +
                ");\n" +
                "\n" +
                "alter table public.users\n" +
                "    owner to postgres;");
    }

    public void dropUsersTable() {
        executor.executeUpdate("DROP TABLE IF EXISTS users");
    }

    public void saveUser(String name, String lastName, byte age) {
        executor.executeUpdate("INSERT INTO users (name, lastname, age) VALUES ('" + name
                + "', '" + lastName + "', '" + age + "')");
    }

    public void removeUserById(long id) {
        executor.executeUpdate("DELETE FROM users WHERE id='" + id + "'");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery("SELECT * FROM users");
            while (res.next()) {
                User user = new User(
                        res.getString("name"),
                        res.getString("lastname"),
                        res.getByte("age")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        executor.executeUpdate("TRUNCATE TABLE users");
    }
}
