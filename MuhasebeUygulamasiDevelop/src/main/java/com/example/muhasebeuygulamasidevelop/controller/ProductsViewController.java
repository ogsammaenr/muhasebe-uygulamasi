package com.example.muhasebeuygulamasidevelop.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ProductsViewController {

    @FXML
    private VBox productsListContainer
            ,productDetailsContainer
            ,emptyProductStateContainer
            ,productDetailsContent
            ,mdfList;

    @FXML
    private Label productNameLabel;

    @FXML
    private Label cuttingCostLabel
            ,cncCostLabel
            ,sandingLaborLabel
            ,sandingCostLabel
            ,palletingLabel
            ,glueCostLabel
            ,pvcCostLabel
            ,printingLaborLabel
            ,packagingCostLabel
            ,shippingCostLabel
            ,extraLaborLabel;
    @FXML
    private Label cuttingCostTotalLabel
            ,cncCostTotalLabel
            ,sandingLaborTotalLabel
            ,sandingCostTotalLabel
            ,palletingTotalLabel
            ,glueCostTotalLabel
            ,pvcCostTotalLabel
            ,printingLaborTotalLabel
            ,packagingCostTotalLabel
            ,shippingCostTotalLabel
            ,extraLaborTotalLabel;

    @FXML
    private Label profitMarginValueLabel
            , totalProfitLabel
            ,unitSetPriceLabel
            , totalSetPriceLabel
            ,laborUnitLabel
            , laborTotalLabel
            ,rawMaterialUnitLabel
            , rawMaterialTotalLabel;
    @FXML
    private BorderPane productsView;



    @FXML
    public void initialize() {
        // Örnek ürünleri tıklanabilir yap
        for (Node node : productsListContainer.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setOnAction(e -> showProductDetails(((Button) node).getText()));
            }
        }


    }

    private void showProductDetails(String productName) {
        // Görsel değişim
        emptyProductStateContainer.setVisible(false);
        emptyProductStateContainer.setManaged(false);
        productDetailsContent.setVisible(true);
        productDetailsContent.setManaged(true);

        productNameLabel.setText(productName);

        // MDF listesini sıfırla
        mdfList.getChildren().clear();

        // Örnek MDF verileri (gerçek veride dinamik olarak gelmeli)
        addMdfItem("18mm Beyaz MDF", 5, 120.0, 500);
        addMdfItem("8mm Siyah MDF", 3, 135.5, 600.5);

        // Örnek maliyet verileri

    }

    private void addMdfItem(String name, int adet, double m2Price, double toplamFiyat) {
        VBox mdfItem = new VBox(3);
        mdfItem.getStyleClass().add("mdf-item");

        Label nameLabel = new Label("🔹 " + name);
        nameLabel.getStyleClass().add("mdf-name");

        Label quantityLabel = new Label("Adet: " + adet);
        quantityLabel.getStyleClass().add("mdf-quantity");

        Label priceLabel = new Label(String.format("m² Fiyatı: %.2f ₺", m2Price));
        priceLabel.getStyleClass().add("mdf-price");

        Label totalPriceLabel = new Label(String.format("Toplam Fiyat: %.2f ₺", toplamFiyat));
        totalPriceLabel.getStyleClass().add("mdf-total-price");

        mdfItem.getChildren().addAll(nameLabel, quantityLabel, priceLabel, totalPriceLabel);
        mdfList.getChildren().add(mdfItem);
    }
}