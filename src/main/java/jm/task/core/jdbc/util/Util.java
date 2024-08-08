package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/shemakata";
    private static final String USERNAME = "ezforjk";
    private static final String PASSWORD = "x1x2x3x3";

    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        try  {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не могу подключить БД");
            throw new RuntimeException(e);
        }
    }
}
