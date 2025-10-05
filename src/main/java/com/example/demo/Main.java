package com.example.demo;

import com.example.demo.utils.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage stage)  {
        SceneManager.switchScene(stage, "/com/example/demo/login.fxml");
//        SceneManager.switchScene(stage, "/com/example/demo/studentDetail.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}