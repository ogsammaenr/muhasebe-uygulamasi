package com.example.muhasebeuygulamasidevelop.controller.items;

import com.example.muhasebeuygulamasidevelop.model.MdfData;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicBoolean;

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

    @FXML
    private Label mdfCountLabel;

    private MdfData mdfData;

    public void setData(MdfData data) {
        this.mdfData = data;

        // Karttaki kısa bilgiler
        mdfThicknessLabel.setText(data.getThickness() + " mm");
        mdfSizeLabel.setText(data.getWidth() + " x " + data.getHeight() + " mm");
        mdfTotalTimeLabel.setText(data.getTotalTime() + " sn");
        mdfTotalPriceLabel.setText(String.format("%.2f ₺", data.getTotalPrice()));
        mdfCountLabel.setText(data.getCount() + "");

        // Tooltip (mouse üzerine gelince tüm veriler)
        String tooltipText =
                        "🪵 MDF Detayları\n" +
                        "------------------------------------\n" +
                        "Kalınlık: " + data.getThickness() + " mm\n" +
                        "Ebat: " + data.getWidth() + " x " + data.getHeight() + " mm\n" +
                        "Adet: " + data.getCount() + "\n" +
                        "Toplam Süre: " + data.getTotalTime() + " sn\n" +
                        "Toplam Fiyat: " + String.format("%.2f ₺", data.getTotalPrice()) + "\n" +
                        "------------------------------------\n" +
                        "Desen Süresi: " + data.getPatternTime() + " sn\n" +
                        "Delik Süresi: " + data.getDrillTime() + " sn\n" +
                        "Birim Alan: " + (data.getArea()/1000000) + "m2\n" +
                        "Toplam Alan: " + (data.getTotalArea()/1000000) + " m2\n" +
                        "Birim Fiyat: " + String.format("%.2f ₺", data.getUnitPrice());

        Popup popup = new Popup();
        Label tooltipLabel = new Label(tooltipText);
        tooltipLabel.getStyleClass().add("custom-tooltip");
        popup.getContent().add(tooltipLabel);
        popup.setAutoHide(false);

        AtomicBoolean hoverOnPopup = new AtomicBoolean(false);

        PauseTransition hideDelay = new PauseTransition(Duration.millis(120));
        hideDelay.setOnFinished(event -> {
            if (!infoIcon.isHover() &&  !hoverOnPopup.get()) {
                popup.hide();
            }
        });

        // Mouse olayları
        infoIcon.setOnMouseEntered(e -> {
            if (!popup.isShowing()) {
                Bounds bounds = infoIcon.localToScreen(infoIcon.getBoundsInLocal());
                double x = bounds.getMinX() + bounds.getWidth() / 2;
                double y = bounds.getMinY() + bounds.getHeight() / 2;
                popup.show(infoIcon, x + 3, y + 3);
            } else {
                // zaten gösteriliyorsa, olası gizleme gecikmesini iptal et
                hideDelay.stop();
            }
        });
        infoIcon.setOnMouseExited(e -> hideDelay.playFromStart());

        Node popupContent = popup.getContent().getFirst();
        popupContent.setOnMouseEntered(e -> {
            hoverOnPopup.set(true);
            hideDelay.stop();
        });

        popupContent.setOnMouseExited(e -> {
            hoverOnPopup.set(false);
            hideDelay.playFromStart();
        });

    }
}
