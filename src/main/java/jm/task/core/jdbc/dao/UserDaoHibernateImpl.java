package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;

    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.createSessionFactory(Util.getConfiguration());
    }


    @Override
    public void createUsersTable() {
        session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT generated always as identity constraint id PRIMARY KEY, " +
                "name VARCHAR(40) NOT NULL, lastname VARCHAR(40) NOT NULL, " +
                "age SMALLINT NOT NULL)";

        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS users";

        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new User(name, lastName, age));

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.openSession();
        session.beginTransaction();

        User user = (User) session.load(User.class, id);
        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);

        List<User> users = criteria.list();

        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "truncate table users";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
