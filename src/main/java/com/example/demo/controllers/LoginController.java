package com.example.demo.controllers;

import com.example.demo.utils.DatabaseConnection;
import com.example.demo.utils.SQLiteDBConnection;
import com.example.demo.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController extends BaseController {
    @FXML
    private Button loginButton;
    @FXML
    private  Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;


//    public void cancelButtonOnAction(ActionEvent e){
//        Stage stage = (Stage) cancelButton.getScene().getWindow();
//        stage.close();
//    }

    public void loginButtonOnAction(ActionEvent e){

        if(!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            validateLogin();
        }else if (!usernameTextField.getText().isBlank() && passwordPasswordField.getText().isBlank()) {
            loginMessageLabel.setText("Please enter the password");
        }else if (usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            loginMessageLabel.setText("Please enter the username");
        }else{
            loginMessageLabel.setText("Please enter username and password");
        }

    }

    public void validateLogin(){
        SQLiteDBConnection connectionNow = new SQLiteDBConnection();
        Connection connectDB = connectionNow.getConnection();

        String varifyLogin = "SELECT count(1) FROM "+DatabaseConnection.getCredentialTableName()+" WHERE Username = '"+usernameTextField.getText()+"' AND Password = '"+passwordPasswordField.getText()+"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(varifyLogin);

            while (queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Welcome");


                    Stage loginStage = (Stage) loginButton.getScene().getWindow();
                    SceneManager.switchScene(loginStage, "/com/example/demo/home.fxml");

                }else{
                    loginMessageLabel.setText("Invalid login, Please try again.");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            loginMessageLabel.setText("Login failed, Something went wrong.");
        }
    }
}