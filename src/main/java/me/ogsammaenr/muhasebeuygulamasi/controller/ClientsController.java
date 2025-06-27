package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ClientsController {

    @FXML
    public void initialize() {

    }

    @FXML
    public void onNewClientClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/ogsammaenr/muhasebeuygulamasi/new-client-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 500, 500);
            Image icon = new Image(getClass().getResource("icons/logo.png").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(icon);
            stage.show();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button btn_newClient;
}
