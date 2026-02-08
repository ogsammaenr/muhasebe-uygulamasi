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

    private double quantity;
    private double multiplier = 1.0;
    private double currentTotalCost = 0;
    private Runnable onPriceChangedListener;

    private ProductDefinition.CostDefinition definition;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Görüntüleme amaçlı olduğu için alanları kilitliyoruz
        tfCostName.setEditable(false);
        tfUnitPrice.setEditable(false);
        tfQuantity.setEditable(false);

        tfUnitPrice.textProperty().addListener((obs, oldVal, newVal) -> {
            calculateTotal();
            if (onPriceChangedListener != null) {
                onPriceChangedListener.run(); // Ana sayfaya haber ver
            }
        });
    }

    public void setUnitPriceEditable(boolean bool) {
        tfUnitPrice.setEditable(bool);
        if (bool) {
            // Düzenlenebilir olanların rengini hafif belli edelim
            tfUnitPrice.setStyle("-fx-text-fill: #000; -fx-background-color: #fff; -fx-border-color: #ccc;");
        }
    }

    public void setOnPriceChanged(Runnable listener) {
        this.onPriceChangedListener = listener;
    }

    public double getTotalCost() {
        return currentTotalCost;
    }

    /**
     * Controller'ı bir maliyet tanımına bağlar ve değerleri hesaplar
     * @param definition Tanım nesnesi (Material veya Labor)
     * @param quantity Miktar (Alan m² veya Süre dk)
     * @param dollarRate Mevcut dolar kuru
     */
    public void bind(ProductDefinition.CostDefinition definition, double quantity, double dollarRate) {
        this.definition = definition;
        this.quantity = quantity;

        // Çarpanı belirle
        this.multiplier = 1.0;
        if (definition instanceof ProductDefinition.MaterialDefinition) {
            this.multiplier = ((ProductDefinition.MaterialDefinition) definition).getMultiplier();
        }

        // Birim Fiyatı Belirle (Varsayılan)
        double basePrice = definition.getBasePrice();
        if ("USD".equalsIgnoreCase(definition.getCurrency())) {
            basePrice *= dollarRate;
        }
        double totalCost = basePrice * multiplier * quantity;

        // UI Doldur
        tfCostName.setText(definition.getName());
        lblQuantityType.setText(definition.getUnitType().toString());
        tfQuantity.setText(String.format("%.4f", quantity));

        // Fiyatı kutuya yaz (Bu tetikleme listener'ı çalıştırıp calculateTotal'i çağıracak)
        tfUnitPrice.setText(String.format("%.2f", basePrice).replace(",", "."));

        // Görsel ayarlama
        if (quantity <= 0) {
            lblTotal.setStyle("-fx-text-fill: gray;");
        } else {
            lblTotal.setStyle("-fx-text-fill: #2196f3; -fx-font-weight: bold;");
        }
    }

    private void calculateTotal() {
        try {
            // Virgül/Nokta dönüşümü yaparak sayıyı al
            String priceText = tfUnitPrice.getText().replace(",", ".");
            double unitPrice = priceText.isEmpty() ? 0 : Double.parseDouble(priceText);

            this.currentTotalCost = unitPrice * quantity * multiplier;

            lblTotal.setText(String.format("%.2f ₺", currentTotalCost));
        } catch (NumberFormatException e) {
            this.currentTotalCost = 0;
            lblTotal.setText("0.00 ₺");
        }
    }
}

