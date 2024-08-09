package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/shemakata";
    private static final String USERNAME = "ezforjk";
    private static final String PASSWORD = "x1x2x3x3";
    private static final Logger logger = Logger.getLogger(Util.class.getName());

    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        try  {
            logger.log(Level.INFO, "Подключение к БД...");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Не удалось подключиться к БД", e);
            throw new RuntimeException(e);
        }
    }
}
