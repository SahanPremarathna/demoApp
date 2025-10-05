package com.example.demo.controllers;

import com.example.demo.utils.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public abstract class BaseController {

    @FXML
    protected void actionStudentRecords(ActionEvent event) {
        SceneManager.switchScene(event, "/com/example/demo/studentDetail.fxml");
    }

    @FXML
    protected void actionLogout(ActionEvent event) {
        SceneManager.switchScene(event, "/com/example/demo/login.fxml");
    }

    @FXML
    protected void actionHome(ActionEvent event) {
        SceneManager.switchScene(event, "/com/example/demo/home.fxml");
    }

    @FXML
    protected void actionSettings(ActionEvent event) {
        SceneManager.switchScene(event, "/com/example/demo/settings.fxml");
    }

    @FXML
    protected void actionShutdown(ActionEvent event){
        SceneManager.exitScene(event);
    }
}