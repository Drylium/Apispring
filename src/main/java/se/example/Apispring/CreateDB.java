package se.example.Apispring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

    public CreateDB() throws SQLException {
        this.create();
    }

    private void create() throws SQLException {
//        String dbUrl = System.getenv("DATABASE_URL");
//        String dbUser = System.getenv("DATABASE_USER");
//        String dbPassword = System.getenv("DATABASE_PASSWORD");
//        Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//
//        try (Statement statement = connection.createStatement()) {
//            // statement.execute("DROP TABLE IF EXISTS account");
//            statement.execute("CREATE TABLE IF NOT EXISTS account (" +
//                    "id SERIAL PRIMARY KEY," +
//                    "balance NUMERIC)");
//        }
    }
}
