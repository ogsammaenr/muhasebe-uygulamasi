package com.example.muhasebeuygulamasidevelop.controller.items;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class WorkItemRowController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private Button actionButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Label totalPriceLabel;

    private HBox root;

    private boolean saved = false;

    public WorkItemRowController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/items/work-item-row.fxml"));
        fxmlLoader.setController(this);

        try {
            root = fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HBox getRoot() {
        return root;
    }

    @FXML
    private void initialize() {
        // İlk durumda priceField kapalı, sadece label var

        actionButton.setOnAction(event -> onActionClicked());
    }

    private void onActionClicked() {
        if (!saved) {
            saved = true;

            // İsim -> Label'a dönüşsün
            String name = nameField.getText();
            nameField.setVisible(false);
            nameField.setManaged(false);

            nameLabel.setVisible(true);
            nameLabel.setManaged(true);
            nameLabel.setText(name);
            ((HBox) nameField.getParent()).getChildren().set(0, nameLabel);

            // Fiyat -> TextField aktif olsun
            priceField.setVisible(true);
            priceField.setManaged(true);

            // Fiyat -> Label aktif olsun
            totalPriceLabel.setVisible(true);
            totalPriceLabel.setManaged(true);

            // Kaydet -> Sil butonuna dönüşsün
            actionButton.setText("Sil");
        } else {
            // Sil butonuna basıldığında
            ((HBox) actionButton.getParent()).getChildren().clear();
        }
    }
}
