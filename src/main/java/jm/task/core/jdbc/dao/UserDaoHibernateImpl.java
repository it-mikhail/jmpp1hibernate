package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.security.auth.login.AppConfigurationEntry;

import java.util.*;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT," +
                "`name` TINYTEXT," +
                "`lastName` TINYTEXT," +
                "`age` TINYINT UNSIGNED," +
                "PRIMARY KEY(`id`));";

        session.createSQLQuery(query).executeUpdate();
        tx.commit();
        session.close();
        factory.close();
    }

    @Override
    public void dropUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        String query = "DROP TABLE IF EXISTS `users`;";

        session.createSQLQuery(query).executeUpdate();
        tx.commit();
        session.close();
        factory.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(new User(name, lastName, age));

        tx.commit();
        session.close();
        factory.close();
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "delete from User where id = :userId";
        session.createQuery(hql)
                .setParameter("userId", id)
                .executeUpdate();

        tx.commit();
        session.close();
        factory.close();
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "from User";
        List usersList = session.createQuery(hql).list();

        tx.commit();
        session.close();
        factory.close();

        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        String query = "DELETE FROM `users`;";

        session.createSQLQuery(query).executeUpdate();
        tx.commit();
        session.close();
        factory.close();
    }
}
