package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getMySQLConnection;

public class UserDaoJDBCImpl implements UserDao {

    private Connection conn;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            conn = getMySQLConnection();
            Statement statement = conn.createStatement();

            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "`id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT," +
                    "`name` TINYTEXT," +
                    "`lastName` TINYTEXT," +
                    "`age` TINYINT UNSIGNED," +
                    "PRIMARY KEY(`id`));";
            statement.execute(query);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            conn = getMySQLConnection();
            Statement statement = conn.createStatement();

            String query = "DROP TABLE IF EXISTS `users`;";
            statement.execute(query);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            conn = getMySQLConnection();
            String query = "INSERT INTO `users` (`name`, `lastName`, `age`) VALUES (?,?,?);";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("User с именем – " + name + " добавлен в базу данных");
            }

            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            conn = getMySQLConnection();
            String query = "DELETE FROM `users` WHERE id = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try {
            conn = getMySQLConnection();
            Statement statement = conn.createStatement();

            String query = "SELECT * FROM `users`;";
            ResultSet result = statement.executeQuery(query);

            List<User> resultList = new ArrayList<>();

            while (result.next()) {
                User newUser = new User();

                newUser.setId(result.getLong("id"));
                newUser.setName(result.getString("name"));
                newUser.setLastName(result.getString("lastName"));
                newUser.setAge(result.getByte("age"));

                resultList.add(newUser);
            }

            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            conn = getMySQLConnection();
            String query = "DELETE FROM `users`;";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
