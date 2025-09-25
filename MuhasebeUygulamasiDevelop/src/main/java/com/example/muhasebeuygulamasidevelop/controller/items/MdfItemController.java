package com.example.muhasebeuygulamasidevelop.controller.items;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MdfItemController {
    @FXML
    private Label mdfNameLabel,
            mdfQuantityLabel,
            mdfPriceLabel,
            mdfTotalPriceLabel,
            mdfPatternTimeLabel,
            mdfDrillTimeLabel,
            mdfTotalTimeLabel;

    public void setMdfName(String name) {
        mdfNameLabel.setText(name);
    }

    public void setMdfQuantity(String quantity) {
        mdfQuantityLabel.setText(quantity);
    }

    public void setMdfPrice(String price) {
        mdfPriceLabel.setText(price);
    }

    public void setMdfTotalPrice(String total) {
        mdfTotalPriceLabel.setText(total);
    }

    public void setMdfPatternTime(String time) {
        String a = mdfPatternTimeLabel.getText();
        mdfPatternTimeLabel.setText(a.replace("-", time));
    }

    public void setMdfDrillTime(String time) {
        String a = mdfDrillTimeLabel.getText();
        mdfDrillTimeLabel.setText(a.replace("-", time));
    }

    public void setMdfTotalTime(String totalTime) {
        mdfTotalTimeLabel.setText(totalTime);
    }
}
