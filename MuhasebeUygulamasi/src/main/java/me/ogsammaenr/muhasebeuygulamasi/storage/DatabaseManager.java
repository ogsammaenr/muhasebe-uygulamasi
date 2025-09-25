package me.ogsammaenr.muhasebeuygulamasi.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_PATH = "database.db";
    private static final String URL = "jdbc:sqlite:" + DB_PATH;

    private static Connection connection;

    public static void connect() {
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Veritabanı bağlantısı başarılı: " + DB_PATH);

            // Tabloları oluştur
            createTables();
        } catch (SQLException e) {
            System.err.println("Veritabanı bağlantı hatası: " + e.getMessage());
        }
    }

    private static void createTables() {
        String sql = """
                    CREATE TABLE IF NOT EXISTS clients (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        company_name TEXT NOT NULL,
                        registration_date TEXT,
                        last_action_date TEXT,
                        email TEXT,
                        telephone TEXT,
                        notes TEXT
                    );
                """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Tablo oluşturulurken hata oluştu: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connect();
        }
        return connection;
    }

    // Bağlantıyı kapat
    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Veritabanı bağlantısı kapatıldı.");
            }
        } catch (SQLException e) {
            System.err.println("Bağlantı kapatma hatası: " + e.getMessage());
        }
    }

}
