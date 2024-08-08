package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255), lastName VARCHAR(255), age TINYINT)");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.err.println("Оштбка создания таблицы" + e.getMessage());
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.err.println("Оштибка удаления таблицы" + e.getMessage());
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO users (name, lastName, age) VALUES ('" +
                    name + "', '" + lastName + "', " + age + ")");
            System.out.println("Пользователь добавлен");
        } catch (SQLException e) {
            System.err.println("Что-то пошло не так при добавлении" + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM users WHERE id = " + id);
            System.out.println("DELETED");
        } catch (SQLException e) {
            System.err.println("Не удалось удалить" + e.getMessage());
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                userList.add(user);
            }
            System.out.println("Список пользователей получен");
        } catch (SQLException e) {
            System.err.println("Ошибка получения полтьзователей" + e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM users");
            System.out.println("Cleaned");
        } catch (SQLException e) {
            System.err.println("not cleaned" + e.getMessage());
        }

    }
}
