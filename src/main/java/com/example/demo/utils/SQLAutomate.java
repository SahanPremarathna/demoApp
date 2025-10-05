package com.example.demo.utils;

import java.io.IOException;

public class SQLAutomate {
    public static void startMySQL() {
        try {
            ProcessBuilder builder = new ProcessBuilder("C:\\xampp\\mysql_start.bat");
            builder.start();
            System.out.println("✅ MySQL started automatically.");
        } catch (IOException e) {
            System.out.println("❌ Failed to start MySQL automatically.");
            e.printStackTrace();
        }
    }

    public static void stopMySQL() {
        try {
            ProcessBuilder builder = new ProcessBuilder("C:\\xampp\\mysql_stop.bat");
            builder.start();
            System.out.println("🛑 MySQL stopped automatically.");
        } catch (IOException e) {
            System.out.println("❌ Failed to stop MySQL automatically.");
            e.printStackTrace();
        }
    }
}
