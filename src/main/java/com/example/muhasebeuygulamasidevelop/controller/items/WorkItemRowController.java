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
    private ComboBox<String> unitComboBox;
    @FXML
    private Label unitLabel;
    @FXML
    private Button actionButton;
    @FXML
    private Label nameLabel;

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
            // Kullanıcı kaydet'e bastığında
            if(nameField.getText().isEmpty() && unitComboBox.getSelectionModel().getSelectedItem() == null){
                ((HBox) actionButton.getParent()).getChildren().clear();
                return;
            }
            saved = true;

            // İsim -> Label'a dönüşsün
            String name = nameField.getText();
            nameField.setDisable(true);
            nameLabel.setDisable(false);
            nameLabel.setText(name);
            ((HBox) nameField.getParent()).getChildren().set(0, nameLabel);

            // Fiyat -> TextField aktif olsun
            priceField.setDisable(false);

            // ComboBox -> Label'a dönüşsün
            String selected = unitComboBox.getSelectionModel().getSelectedItem();
            unitLabel.setText(selected != null ? selected : "");
            unitComboBox.setVisible(false);
            unitLabel.setVisible(true);

            // Kaydet -> Sil butonuna dönüşsün
            actionButton.setText("Sil");
        } else {
            // Sil butonuna basıldığında
            ((HBox) actionButton.getParent()).getChildren().clear();
        }
    }
}
