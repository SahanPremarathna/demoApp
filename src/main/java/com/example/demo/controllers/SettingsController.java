package com.example.demo.controllers;

import com.example.demo.utils.AlertUser;
import com.example.demo.utils.DatabaseConnection;
import com.example.demo.utils.SQLiteDBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SettingsController extends BaseController {

    @FXML
    private Button btnClear;

    SQLiteDBConnection connectionNow = new SQLiteDBConnection();
    Connection connectDB = connectionNow.getConnection();

    @FXML
    void actionClearData(ActionEvent event) {
        String clearQuery = "DELETE FROM " + SQLiteDBConnection.DATA_TABLE_NAME + ";";
        String resetIncrementQuery = "DELETE FROM sqlite_sequence WHERE name='" + SQLiteDBConnection.DATA_TABLE_NAME + "';";

        try (PreparedStatement ps = connectDB.prepareStatement(clearQuery)) {
            ps.executeUpdate();

            // Reset the autoincrement counter
            try (PreparedStatement resetPs = connectDB.prepareStatement(resetIncrementQuery)) {
                resetPs.executeUpdate();
            }

            // Delete all images in the images folder
            File imageFolder = new File("images");  // adjust path if needed
            if (imageFolder.exists() && imageFolder.isDirectory()) {
                File[] files = imageFolder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            boolean deleted = file.delete();
                            if (!deleted) {
                                System.out.println("Failed to delete: " + file.getName());
                            }
                        }
                    }
                }
            }

            AlertUser.clearDatabase();

        } catch (Exception e) {
            AlertUser.error("Something went wrong clearing the data");
            e.printStackTrace();
        }
    }


}
