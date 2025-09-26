package com.example.muhasebeuygulamasidevelop.controller.items;

import com.example.muhasebeuygulamasidevelop.model.MdfData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.util.Duration;

public class MdfItemController {
    @FXML
    private Label mdfThicknessLabel;

    @FXML
    private Label mdfSizeLabel;

    @FXML
    private Label mdfTotalTimeLabel;

    @FXML
    private Label mdfTotalPriceLabel;

    @FXML
    private Label infoIcon;

    private MdfData mdfData;

    public void setData(MdfData data) {
        this.mdfData = data;

        // Karttaki kısa bilgiler
        mdfThicknessLabel.setText(data.getThickness() + " mm");
        mdfSizeLabel.setText(data.getWidth() + " x " + data.getHeight() + " mm");
        mdfTotalTimeLabel.setText(data.getTotalTime() + " sn");
        mdfTotalPriceLabel.setText(String.format("%.2f ₺", data.getTotalPrice()));

        // Tooltip (mouse üzerine gelince tüm veriler)
        String tooltipText =
                "Kalınlık: " + data.getThickness() + " mm\n" +
                        "Genişlik: " + data.getWidth() + " mm\n" +
                        "Yükseklik: " + data.getHeight() + " mm\n" +
                        "Desen Süresi: " + data.getPatternTime() + " sn\n" +
                        "Delik Süresi: " + data.getDrillTime() + " sn\n" +
                        "Birim Fiyat: " + data.getUnitPrice() + " ₺\n" +
                        "Toplam Zaman: " + data.getTotalTime() + " sn\n" +
                        "Toplam Fiyat: " + String.format("%.2f ₺", data.getTotalPrice());

        Popup popup = new Popup();
        Label tooltipLabel = new Label(tooltipText);
        tooltipLabel.getStyleClass().add("custom-tooltip");
        popup.getContent().add(tooltipLabel);

        // Mouse olayları
        infoIcon.setOnMouseEntered(e -> {
            double x = infoIcon.localToScreen(infoIcon.getBoundsInLocal()).getMinX()
                    + infoIcon.getWidth()/2 - tooltipLabel.getWidth()/2;
            double y = infoIcon.localToScreen(infoIcon.getBoundsInLocal()).getMinY()
                    - tooltipLabel.getHeight() - 6;
            popup.show(infoIcon, x, y);
        });

        infoIcon.setOnMouseExited(e -> popup.hide());

    }
}
