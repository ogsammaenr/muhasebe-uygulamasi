package com.example.muhasebeuygulamasidevelop.controller;

import com.example.muhasebeuygulamasidevelop.controller.items.MdfInputRowController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MdfInputViewController implements Initializable {
    @FXML
    private CheckBox chkOzel;

    @FXML
    private ComboBox<String> cmbKalinlik2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chkOzel.setOnAction(e -> {
            boolean secildi = chkOzel.isSelected();
            cmbKalinlik2.setVisible(secildi);
        });
    }
}
