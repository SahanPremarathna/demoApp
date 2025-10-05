package com.example.demo.utils;

import javafx.scene.control.Alert;

public class AlertUser {

    public static void showInfo(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showError(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showStudentUpdateAlert(String name, String contact, String course) {
        StringBuilder content = new StringBuilder();
        content.append("Details\n\n")
                .append("Name: ").append(name).append("\n")
                .append("Contact: ").append(contact).append("\n")
                .append("Course: ").append(course);

        showInfo(
                "Student Detail Update",
                "Student " + name + " updated successfully.",
                content.toString()
        );
    }

    public static void showStudentAddedAlert(String name, String contact, String course) {
        StringBuilder details = new StringBuilder();
        details.append("Details\n\n")
                .append("Name: ").append(name).append("\n")
                .append("Contact: ").append(contact).append("\n")
                .append("Course: ").append(course);

        showInfo(
                "Student Registration",
                "Student " + name + " added successfully.",
                details.toString()
        );
    }

    public static void showStudentDeletedAlert() {
        showInfo(
                "Student Deletion",
                "Deleted successfully.",
                "The selected student record has been removed."
        );
    }

    public static void clearDatabase(){
        showInfo("Database Prompt",
                "All data cleared successfully.",
                " ");

    }

    public static void error(String msg){
        showError("Error",
                msg,
                " ");

    }


}
