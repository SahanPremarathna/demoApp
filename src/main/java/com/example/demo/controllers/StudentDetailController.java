package com.example.demo.controllers;

import com.example.demo.utils.AlertUser;
import com.example.demo.utils.DatabaseConnection;
import com.example.demo.utils.ImageHandler;
import com.example.demo.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.*;

public class StudentDetailController extends BaseController {

    @FXML
    private Button btnAdd, btnDelete, btnUpdate;

    @FXML
    private TableColumn<Student, String> contactNumberCol, courseCol, nameCol, studentIdCol;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TextField txtContactNumber, txtCourse, txtName;

    @FXML
    private ImageView imageContainer;

    int myIndex;
    int id;

    DatabaseConnection connectionNow = new DatabaseConnection();
    Connection connectDB = connectionNow.getConnection();

    @FXML
    public void initialize() {
        table();
    }

    @FXML
    void actionAddImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(imageContainer.getScene().getWindow());
        if (selectedFile != null) {
            imageContainer.setImage(new Image(selectedFile.toURI().toString()));
        }
    }

    @FXML
    void actionAdd(ActionEvent event) {
        String name = txtName.getText();
        String cNumber = txtContactNumber.getText();
        String course = txtCourse.getText();

        String imagePath = null;
        try {
            imagePath = ImageHandler.saveImage(imageContainer.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String insertQuery = "INSERT INTO `studentdata` (`name`, `contact_number`, `course`, `image_path`) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connectDB.prepareStatement(insertQuery)) {
            ps.setString(1, name);
            ps.setString(2, cNumber);
            ps.setString(3, course);
            ps.setString(4, imagePath);

            ps.executeUpdate();
            table();
            clearTextAll();
            AlertUser.showStudentAddedAlert(name, cNumber, course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void actionDelete(ActionEvent event) {
        myIndex = studentTable.getSelectionModel().getSelectedIndex();
        if (myIndex < 0) return;

        id = Integer.parseInt(studentTable.getItems().get(myIndex).getId());

        try {
            String deleteQuery = "DELETE FROM `studentdata` WHERE id = ?";
            try (PreparedStatement ps = connectDB.prepareStatement(deleteQuery)) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            table();
            clearTextAll();
            AlertUser.showStudentDeletedAlert();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void actionUpdate(ActionEvent event) {
        myIndex = studentTable.getSelectionModel().getSelectedIndex();
        if (myIndex < 0) return;

        id = Integer.parseInt(studentTable.getItems().get(myIndex).getId());
        String stName = txtName.getText();
        String mobile = txtContactNumber.getText();
        String course = txtCourse.getText();

        String imagePath = null;
        try {
            imagePath = ImageHandler.saveImage(imageContainer.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String updateQuery = "UPDATE `studentdata` SET name = ?, contact_number = ?, course = ?, image_path = ? WHERE id = ?";
        try (PreparedStatement ps = connectDB.prepareStatement(updateQuery)) {
            ps.setString(1, stName);
            ps.setString(2, mobile);
            ps.setString(3, course);
            ps.setString(4, imagePath);
            ps.setInt(5, id);

            ps.executeUpdate();
            table();
            clearTextAll();
            AlertUser.showStudentUpdateAlert(stName, mobile, course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearTextAll() {
        txtName.clear();
        txtCourse.clear();
        txtContactNumber.clear();
        imageContainer.setImage(null);
    }

    public void table() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        try {
            String tableQuery = "SELECT id, name, contact_number, course, image_path FROM studentdata";
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(tableQuery);

            while (queryResult.next()) {
                Student st = new Student();
                st.setId(queryResult.getString("id"));
                st.setName(queryResult.getString("name"));
                st.setMobile(queryResult.getString("contact_number"));
                st.setCourse(queryResult.getString("course"));
                st.setImagePath(queryResult.getString("image_path")); // store image path only
                students.add(st);
            }

            studentTable.setItems(students);
            studentIdCol.setCellValueFactory(f -> f.getValue().idProperty());
            nameCol.setCellValueFactory(f -> f.getValue().nameProperty());
            contactNumberCol.setCellValueFactory(f -> f.getValue().mobileProperty());
            courseCol.setCellValueFactory(f -> f.getValue().courseProperty());

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Preview image when row is selected
        studentTable.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    myIndex = studentTable.getSelectionModel().getSelectedIndex();
                    Student selected = studentTable.getItems().get(myIndex);

                    id = Integer.parseInt(selected.getId());
                    txtName.setText(selected.getName());
                    txtContactNumber.setText(selected.getMobile());
                    txtCourse.setText(selected.getCourse());

                    // Preview image
                    imageContainer.setImage(ImageHandler.loadImage(selected.getImagePath()));
                }
            });
            return row;
        });
    }
}
