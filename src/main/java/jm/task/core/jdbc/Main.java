package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        System.out.println("Таблица users создана");

        userService.saveUser("Иван", "Иванов", (byte) 19);
        userService.saveUser("Александр", "Лебедев", (byte) 28);
        userService.saveUser("Олег", "Петров", (byte) 31);;
        userService.saveUser("NAme", "LastNAme", (byte) 38);
        System.out.println("Пользователт добавлены");

        userService.removeUserById(1);
        System.out.println("id 1 удален");

        List<User> users = userService.getAllUsers();
        System.out.println("Список всех пользователей:");
        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        System.out.println("Таблица очищена");

        userService.dropUsersTable();
        System.out.println("Таблица удалена");

    }
}
