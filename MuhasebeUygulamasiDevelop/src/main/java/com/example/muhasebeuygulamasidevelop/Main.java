package com.example.muhasebeuygulamasidevelop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Muhasebeuygulamasidevelop");
        stage.setMinHeight(600);
        stage.setHeight(870);
        stage.setMinWidth(800);
        stage.setWidth(1200);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}
