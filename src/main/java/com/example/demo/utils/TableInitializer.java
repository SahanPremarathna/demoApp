package com.example.demo.utils;

import java.sql.Connection;
import java.sql.Statement;

public class TableInitializer {

    public static void initialize() {
        String createStudentTable = """
                CREATE TABLE IF NOT EXISTS studentdata (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        contact_number TEXT,
                        course TEXT,
                        image_path TEXT
                    );
                """;

        String createUserTable = """
                CREATE TABLE IF NOT EXISTS useraccounts (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    Username TEXT NOT NULL UNIQUE,
                    Password TEXT NOT NULL
                );
                """;

        String insertDefaultUser = """
                INSERT OR IGNORE INTO useraccounts (Username, Password)
                VALUES ('root', 'toor');
                """;

        try (Connection conn = new SQLiteDBConnection().getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createStudentTable);
            stmt.execute(createUserTable);
            stmt.execute(insertDefaultUser);
            System.out.println("✅ Tables checked/created");

        } catch (Exception e) {
            System.out.println("❌ Table creation error: " + e.getMessage());
        }
    }
}
