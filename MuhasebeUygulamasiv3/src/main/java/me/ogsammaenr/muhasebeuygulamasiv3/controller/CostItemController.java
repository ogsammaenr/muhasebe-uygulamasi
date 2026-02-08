package me.ogsammaenr.muhasebeuygulamasiv3.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import me.ogsammaenr.muhasebeuygulamasiv3.model.ProductDefinition;

import java.net.URL;
import java.util.ResourceBundle;

public class CostItemController implements Initializable {
    @FXML private TextField tfCostName;
    @FXML private TextField tfUnitPrice;
    @FXML private TextField tfQuantity;
    @FXML private Label lblQuantityType;
    @FXML private Label lblTotal;

    private ProductDefinition.CostDefinition definition;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Görüntüleme amaçlı olduğu için alanları kilitliyoruz
        tfCostName.setEditable(false);
        tfUnitPrice.setEditable(false);
        tfQuantity.setEditable(false);
    }

    public void setUnitPriceEditable(boolean bool) {
        tfUnitPrice.setEditable(bool);
    }

    /**
     * Controller'ı bir maliyet tanımına bağlar ve değerleri hesaplar
     * @param definition Tanım nesnesi (Material veya Labor)
     * @param quantity Miktar (Alan m² veya Süre dk)
     * @param dollarRate Mevcut dolar kuru
     */
    public void bind(ProductDefinition.CostDefinition definition, double quantity, double dollarRate) {
        this.definition = definition;

        // 1. Birim Fiyatı Belirle (USD ise TL'ye çevir)
        double basePrice = definition.getBasePrice();
        if ("USD".equalsIgnoreCase(definition.getCurrency())) {
            basePrice *= dollarRate;
        }

        // 2. Çarpanı Kontrol Et (Sadece Hammaddeler için)
        double multiplier = 1.0;
        if (definition instanceof ProductDefinition.MaterialDefinition) {
            multiplier = ((ProductDefinition.MaterialDefinition) definition).getMultiplier();
        }

        // 3. Toplam Maliyeti Hesapla
        // Hammadde: miktar * (birimFiyat * çarpan)
        // İşçilik: miktar * birimFiyat
        double totalCost = quantity * basePrice * multiplier;

        // 4. UI Güncelle
        tfCostName.setText(definition.getName());
        tfUnitPrice.setText(String.format("%.2f ₺", basePrice * multiplier));
        tfQuantity.setText(String.format("%.4f", quantity));
        lblQuantityType.setText(definition.getUnitType().toString());
        lblTotal.setText(String.format("%.2f ₺", totalCost));

        // Eğer miktar 0 ise görsel olarak sönük yapabilirsin
        if (quantity <= 0) {
            lblTotal.setStyle("-fx-text-fill: gray;");
        } else {
            lblTotal.setStyle("-fx-text-fill: #2196f3; -fx-font-weight: bold;");
        }
    }
}

