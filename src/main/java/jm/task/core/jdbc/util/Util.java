package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static String hostName = "192.168.56.103";
    private static String port = "3306";
    private static String dbName = "jpp_task_1_1_3";
    private static String userName = "javadeveloper";
    private static String password = "javadeveloper";
    private static String disableSSL = "?useSSL=false";
    private static String connectionURL = "jdbc:mysql://" + hostName + ":" + port + "/" + dbName + disableSSL;

    private static String hibernateDialect = "org.hibernate.dialect.MySQLDialect";
    private static String hibernateDriver = "com.mysql.cj.jdbc.Driver";

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.connection.driver_class", hibernateDriver);
        properties.setProperty("hibernate.connection.username", userName);
        properties.setProperty("hibernate.connection.password", password);
        properties.setProperty("hibernate.connection.url", connectionURL);

        Configuration config = new Configuration()
                .addAnnotatedClass(User.class)
                .setProperties(properties);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build();

        return config.buildSessionFactory(serviceRegistry);
    }

    public static Connection getMySQLConnection() throws SQLException {
        String hostName = "192.168.56.103";
        String port = "3306";
        String dbName = "jpp_task_1_1_3";
        String userName = "javadeveloper";
        String password = "javadeveloper";
        String disableSSL = "?useSSL=false";
        String connectionURL = "jdbc:mysql://" + hostName + ":" + port + "/" + dbName + disableSSL;

        return DriverManager.getConnection(connectionURL, userName, password);
    }
}
