package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255), lastName VARCHAR(255), age TINYINT)");

            logger.log(Level.INFO, "Таблица users создана");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Проблема при создании таблицы", e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) {

            statement.execute("DROP TABLE IF EXISTS users");

            logger.log(Level.INFO, "Таблица удалена");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Проблема при удалении таблицы", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();

            logger.log(Level.INFO, "Пользователь добавлен");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Проблема при добавлении пользователя", e);
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            statement.executeUpdate();

            logger.log(Level.INFO, "Пользователь удален");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Проблема при удалении пользователя", e);
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                userList.add(user);
            }

            logger.log(Level.INFO, "Пользователи получены");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Проблема при получении пользователей", e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
                Statement statement = connection.createStatement()) {

            statement.execute("DELETE FROM users");

            logger.log(Level.INFO, "Таблица очищена");
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Проблема при очистке таблицы", e);
        }

    }
}
