package me.ogsammaenr.muhasebeuygulamasiv3.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MdfDimensionItemController implements Initializable {
    @FXML
    private TextField tfLength;

    @FXML
    private TextField tfWidth;

    @FXML
    private ComboBox<String> cbThickness;

    @FXML
    private TextField tfCncHoleTime,
            tfCncDesignTime;

    @FXML
    private Button btnDelete;

    @FXML
    private Label lblArea;

    @FXML
    private VBox vbRoot;

    private AddProductController parentController;
    private VBox parentContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Kalınlık değerleri zaten FXML'de tanımlandı
        cbThickness.setValue("18 mm");

        // TextFields değiştiğinde alan hesaplaması yap
        tfLength.textProperty().addListener((obs, oldVal, newVal) -> calculateArea());
        tfWidth.textProperty().addListener((obs, oldVal, newVal) -> calculateArea());
        cbThickness.valueProperty().addListener((obs, oldVal, newVal) -> calculateArea());

        // CNC süresi değiştiğinde parent'ı bildir
        tfCncHoleTime.textProperty().addListener((obs, oldVal, newVal) -> {
            if (parentController != null) {
                parentController.updateTotalCncTime();
            }
        });
        tfCncDesignTime.textProperty().addListener((obs, oldVal, newVal) -> {
            if (parentController != null) {
                parentController.updateTotalCncTime();
            }
        });

        // Sil butonu
        btnDelete.setOnAction(event -> deleteThis());
    }

    public void setParentController(AddProductController parentController) {
        this.parentController = parentController;
    }

    public void setParentContainer(VBox parentContainer) {
        this.parentContainer = parentContainer;
    }

    private void calculateArea() {
        try {
            double length = Double.parseDouble(tfLength.getText().isEmpty() ? "0" : tfLength.getText());
            double width = Double.parseDouble(tfWidth.getText().isEmpty() ? "0" : tfWidth.getText());

            // mm'den m²'ye dönüşüm (mm² / 1,000,000)
            double areaM2 = (length * width) / 1_000_000;
            lblArea.setText(String.format("%.4f m²", areaM2));

            // Parent controller'ın toplam m² hesaplamasını güncelle
            if (parentController != null) {
                parentController.updateTotalM2();
            }
        } catch (NumberFormatException e) {
            lblArea.setText("0,00 m²");
        }
    }

    private void deleteThis() {
        // Bu item'ı parent VBox'tan kaldır
        if (parentContainer != null && vbRoot != null) {
            parentContainer.getChildren().remove(vbRoot);
            if (parentController != null) {
                parentController.updateTotalM2();
                parentController.updateTotalCncTime();
            }
        }
    }

    public double getArea() {
        try {
            String areaText = lblArea.getText().replace(" m²", "").replace(",", ".");
            return Double.parseDouble(areaText);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public double getCncTime() {
        try {
            double holeTime = Double.parseDouble(tfCncHoleTime.getText().isEmpty() ? "0" : tfCncHoleTime.getText());
            double designTime = Double.parseDouble(tfCncDesignTime.getText().isEmpty() ? "0" : tfCncDesignTime.getText());
            return holeTime + designTime;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public String getThickness() {
        return cbThickness.getValue() != null ? cbThickness.getValue() : "0";
    }

    public double getLength() {
        try {
            return Double.parseDouble(tfLength.getText().isEmpty() ? "0" : tfLength.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public double getWidth() {
        try {
            return Double.parseDouble(tfWidth.getText().isEmpty() ? "0" : tfWidth.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}

