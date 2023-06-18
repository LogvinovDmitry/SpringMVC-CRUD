package logvinov.springcourse.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSpringMVSQRUDSchema {

    public static final String NAME_USER = "root";
    public static final String PASSWORD = "345680qwer";
    public static final String URL = "jdbc:mysql://localhost:3306/mysql";

    private Connection connection;

    public ConnectionSpringMVSQRUDSchema() {
        try {
            connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e + "Не удалось подключиться к серверу");
        }
    }

    public Connection getConnection() {
        return connection;
    }

}

