package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.List;

public class UserServiceImpl implements UserService {

//    private final Connection connection;
    private final Configuration configuration;
    public UserServiceImpl() {
//        this.connection = Util.getConnection();
        this.configuration = Util.getConfiguration();
    }
    /*
    *
    *
     */
    public void createUsersTable() {
//        UserDaoJDBCImpl dao = new UserDaoJDBCImpl(connection);
//        dao.createUsersTable();


        UserDaoHibernateImpl daoh = new UserDaoHibernateImpl(configuration);
        daoh.createUsersTable();
//        sessionFactory.close();
    }

    public void dropUsersTable() {
//        UserDaoJDBCImpl dao = new UserDaoJDBCImpl(connection);
//        dao.dropUsersTable();


        UserDaoHibernateImpl daoh = new UserDaoHibernateImpl(configuration);
        daoh.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
//        UserDaoJDBCImpl dao = new UserDaoJDBCImpl(connection);
//        dao.saveUser(name, lastName, age);


        UserDaoHibernateImpl daoh = new UserDaoHibernateImpl(configuration);
        daoh.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
//        UserDaoJDBCImpl dao = new UserDaoJDBCImpl(connection);
//        dao.removeUserById(id);


        UserDaoHibernateImpl daoh = new UserDaoHibernateImpl(configuration);
        daoh.removeUserById(id);

    }

    public List<User> getAllUsers() {
//        UserDaoJDBCImpl dao = new UserDaoJDBCImpl(connection);
//        return dao.getAllUsers();


        UserDaoHibernateImpl daoh = new UserDaoHibernateImpl(configuration);
        return daoh.getAllUsers();
    }

    public void cleanUsersTable() {
//        UserDaoJDBCImpl dao = new UserDaoJDBCImpl(connection);
//        dao.cleanUsersTable();

        UserDaoHibernateImpl daoh = new UserDaoHibernateImpl(configuration);
        daoh.cleanUsersTable();
    }
}
