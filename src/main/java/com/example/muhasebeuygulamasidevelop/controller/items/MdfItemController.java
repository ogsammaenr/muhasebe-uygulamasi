package com.example.muhasebeuygulamasidevelop.controller.items;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MdfItemController {
    @FXML
    private Label mdfNameLabel;

    @FXML
    private Label mdfQuantityLabel;

    @FXML
    private Label mdfPriceLabel;

    @FXML
    private Label mdfTotalPriceLabel;

    public void setMdfName(String name) {
        mdfNameLabel.setText(name);
    }

    public void setMdfQuantity(String quantity) {
        mdfQuantityLabel.setText("Kullanım: " + quantity);
    }

    public void setMdfPrice(String price) {
        mdfPriceLabel.setText("m² Fiyatı: " + price);
    }

    public void setMdfTotalPrice(String total) {
        mdfTotalPriceLabel.setText("Toplam Fiyat: " + total);
    }
}
