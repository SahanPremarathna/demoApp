package com.example.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDBConnection {

    private static final String DB_FILE_NAME = "appdata.db"; // You can name it demologinpage.db too
    private static final String URL = "jdbc:sqlite:" + DB_FILE_NAME;

    public static final String DATA_TABLE_NAME = "studentdata";
    protected static final String CREDENTIAL_TABLE_NAME = "useraccounts";

    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                // No Class.forName needed for SQLite (modern drivers auto-load)
                connection = DriverManager.getConnection(URL);
                System.out.println("✅ Connected to SQLite database: " + DB_FILE_NAME);
            } catch (SQLException e) {
                System.out.println("❌ SQLite connection failed: " + e.getMessage());
            }
        }
        return connection;
    }

    public static String getCredentialTableName() {
        return CREDENTIAL_TABLE_NAME;
    }
}