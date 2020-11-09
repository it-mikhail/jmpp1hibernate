package jm.task.core.jdbc;

import java.util.List;
import java.util.logging.Level;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("User1", "LastName1", (byte) 1);
        userService.saveUser("User2", "LastName2", (byte) 2);
        userService.saveUser("User3", "LastName3", (byte) 3);
        userService.saveUser("User4", "LastName4", (byte) 4);

        List<User> users = userService.getAllUsers();

        for (User el : users) {
            System.out.println(el);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
