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

    @FXML private TextField tfLength;
    @FXML private TextField tfWidth;
    @FXML private ComboBox<String> cbThickness;
    @FXML private TextField tfCncHoleTime;
    @FXML private TextField tfCncDesignTime;
    @FXML private Label lblArea;
    @FXML private Button btnDelete;
    @FXML private VBox vbRoot;

    private Runnable onDataChanged;  // Veri değiştiğinde parent'ı bilgilendir

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ölçü alanlarında değişiklik dinle
        tfLength.textProperty().addListener((obs, oldVal, newVal) -> updateArea());
        tfWidth.textProperty().addListener((obs, oldVal, newVal) -> updateArea());
        cbThickness.valueProperty().addListener((obs, oldVal, newVal) -> updateArea());

        // CNC süresi alanlarında değişiklik dinle
        tfCncHoleTime.textProperty().addListener((obs, oldVal, newVal) -> notifyDataChanged());
        tfCncDesignTime.textProperty().addListener((obs, oldVal, newVal) -> notifyDataChanged());

        // Sil butonu
        btnDelete.setOnAction(event -> {
            vbRoot.getParent(); // Silinecek
            notifyDataChanged();
        });
    }

    /**
     * Alan (m²) hesapla
     * Alan = (Uzunluk * Genişlik) / 1000000
     */
    private void updateArea() {
        try {
            // Virgülleri noktaya çevirerek hata oluşmasını engelle
            String lenStr = tfLength.getText().replace(",", ".");
            String widStr = tfWidth.getText().replace(",", ".");

            double length = Double.parseDouble(lenStr.isEmpty() ? "0" : lenStr);
            double width = Double.parseDouble(widStr.isEmpty() ? "0" : widStr);

            double area = (length * width) / 1000000.0;
            lblArea.setText(String.format("%.4f m²", area));
        } catch (NumberFormatException e) {
            lblArea.setText("0,0000 m²");
        } finally {
            // Hata olsa bile (örneğin alan boşaltıldığında) parent'ı bilgilendir ki
            // maliyetler 0 olarak güncellensin.
            notifyDataChanged();
        }
    }

    /**
     * Seçilen kalınlığın string değerinden mm değerini çıkar
     * "8 mm" -> "8"
     */
    private int getThicknessValue() {
        if (cbThickness.getValue() != null) {
            return Integer.parseInt(cbThickness.getValue().split(" ")[0]);
        }
        return 0;
    }

    /**
     * MDF türünü kalınlığa göre döndür (örn: "MDF 08")
     */
    public String getMdfType() {
        int thickness = getThicknessValue();
        return "MDF " + String.format("%02d", thickness);
    }

    /**
     * MDF alanını (m²) döndür
     */
    public double getArea() {
        try {
            String areaText = lblArea.getText().replace(" m²", "").replace(",", ".");
            return Double.parseDouble(areaText);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Toplam CNC süresini döndür (delik süresi + desen süresi)
     */
    public double getTotalCncTime() {
        try {
            double holeTime = Double.parseDouble(tfCncHoleTime.getText().replace(",", ".").isEmpty() ? "0" : tfCncHoleTime.getText().replace(",", "."));
            double designTime = Double.parseDouble(tfCncDesignTime.getText().replace(",", ".").isEmpty() ? "0" : tfCncDesignTime.getText().replace(",", "."));
            return holeTime + designTime;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Veri değiştiğinde parent controller'ı bilgilendir
     */
    private void notifyDataChanged() {
        if (onDataChanged != null) {
            onDataChanged.run();
        }
    }

    /**
     * Veri değişikliği callback'i ayarla
     */
    public void setOnDataChanged(Runnable callback) {
        this.onDataChanged = callback;
    }
}

