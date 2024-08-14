package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        // Работа с Hibernate
        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 19);
        userService.saveUser("Александр", "Лебедев", (byte) 28);
        userService.saveUser("Олег", "Петров", (byte) 31);
        userService.saveUser("NAme", "LastNAme", (byte) 38);
        userService.removeUserById(1);

        List<User> userList = userService.getAllUsers();
        System.out.println("Список всех пользователей:");
        for (User user : userList) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeSessionFactory();


        //        userService.createUsersTable();
//
//        userService.saveUser("Иван", "Иванов", (byte) 19);
//        userService.saveUser("Александр", "Лебедев", (byte) 28);
//        userService.saveUser("Олег", "Петров", (byte) 31);
//        userService.saveUser("NAme", "LastNAme", (byte) 38);
//
//        userService.removeUserById(1);
//
//        List<User> users = userService.getAllUsers();
//        System.out.println("Список всех пользователей:");
//        for (User user : users) {
//            System.out.println(user);
//        }
//
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
