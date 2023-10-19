package dw.sabbracadabra.snakefx.util;

import java.sql.*;

public class DatabaseUtil {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection("jdbc:sqlite:highscores.db");
            createTable(connection);
        }
        return connection;
    }

    private static void createTable(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet rs = metaData.getTables(null, null, "highscores", null)) {
            if (!rs.next()) {
                String createTableQuery = "CREATE TABLE highscores (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            "score INTEGER, snakeLen INTEGER, timer INTEGER)";
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createTableQuery);
                }
            }
        }
    }
}
