package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author AYA
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/medical_office";
    private static final String USER = "root";
    private static final String PASSWORD = "VOTRE_MOT_DE_PASSE_MYSQL_LOCAL"; //A modifié

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}
