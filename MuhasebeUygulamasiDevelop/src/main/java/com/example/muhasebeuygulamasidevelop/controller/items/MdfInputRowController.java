package com.example.muhasebeuygulamasidevelop.controller.items;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MdfInputRowController{
    @FXML
    private TextField widthField
            , heightField
            , patternTimeField
            , holeTimeField;
    @FXML
    private ComboBox<String> thicknessCombo;
    @FXML
    private Label totalTimeLabel;
    @FXML
    private Button removeButton;

    private HBox root;

    public MdfInputRowController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/items/MDF-input-row.fxml"));
        loader.setController(this);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HBox getRoot() {
        return root;
    }

    @FXML
    public void initialize() {
        ChangeListener<String> listener =
                (ObservableValue<? extends String> obs, String oldVal, String newVal) -> updateTotalTime();

        patternTimeField.textProperty().addListener(listener);
        holeTimeField.textProperty().addListener(listener);

        removeButton.setOnAction(e -> {
            // Parent VBox'dan kendini sil
            VBox parent = (VBox) root.getParent();
            if (parent != null) {
                parent.getChildren().remove(root);
            }
        });
    }

    private void updateTotalTime() {
        try {
            double pattern = Double.parseDouble(patternTimeField.getText());
            double hole = Double.parseDouble(holeTimeField.getText());
            double total = pattern + hole;
            totalTimeLabel.setText(String.format("%.1f dk", total));
        } catch (NumberFormatException e) {
            totalTimeLabel.setText("-- dk");
        }
    }

    public String getWidth() {
        return widthField.getText();
    }

    public String getHeight() {
        return heightField.getText();
    }

    public String getThickness() {
        return thicknessCombo.getValue();
    }

    public double getPatternTime() {
        return parseDouble(patternTimeField.getText());
    }

    public double getHoleTime() {
        return parseDouble(holeTimeField.getText());
    }

    public double getTotalTime() {
        return getPatternTime() + getHoleTime();
    }

    private double parseDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
