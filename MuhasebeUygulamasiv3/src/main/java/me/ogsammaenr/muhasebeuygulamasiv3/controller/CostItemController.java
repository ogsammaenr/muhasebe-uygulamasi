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
    @FXML
    private TextField tfCostName;

    @FXML
    private TextField tfUnitPrice;

    @FXML
    private TextField tfQuantity;

    @FXML
    private Label lblTotal;

    @FXML
    private Button btnRemove;

    private AddProductController parentController;
    private VBox parentContainer;
    private String costType; // "material" veya "labor"

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Birim fiyat veya miktar değişince toplam otomatik hesaplanır
        tfUnitPrice.textProperty().addListener((obs, oldVal, newVal) -> updateTotal());
        tfQuantity.textProperty().addListener((obs, oldVal, newVal) -> updateTotal());

        // Sil butonu
        btnRemove.setOnAction(event -> removeSelf());
    }

    public void setParentController(AddProductController parentController) {
        this.parentController = parentController;
    }

    public void setParentContainer(VBox parentContainer) {
        this.parentContainer = parentContainer;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getCostType() {
        return costType;
    }

    private void updateTotal() {
        try {
            double unitPrice = Double.parseDouble(tfUnitPrice.getText().isEmpty() ? "0" : tfUnitPrice.getText());
            double quantity = Double.parseDouble(tfQuantity.getText().isEmpty() ? "0" : tfQuantity.getText());
            double total = unitPrice * quantity;

            lblTotal.setText(String.format("%.2f ₺", total));

            // Ana controller'da hesaplamaları güncelle
            if (parentController != null) {
                parentController.updateCostCalculations();
            }
        } catch (NumberFormatException e) {
            lblTotal.setText("0,00 ₺");
        }
    }

    private void removeSelf() {
        if (parentContainer != null) {
            parentContainer.getChildren().remove(parentContainer.getChildren().stream()
                    .filter(node -> node.getUserData() == this)
                    .findFirst()
                    .orElse(null));

            // Hesaplamaları güncelle
            if (parentController != null) {
                parentController.updateCostCalculations();
            }
        }
    }

    public double getTotalCost() {
        try {
            double unitPrice = Double.parseDouble(tfUnitPrice.getText().isEmpty() ? "0" : tfUnitPrice.getText());
            double quantity = Double.parseDouble(tfQuantity.getText().isEmpty() ? "0" : tfQuantity.getText());
            return unitPrice * quantity;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public double getUnitPrice() {
        try {
            return Double.parseDouble(tfUnitPrice.getText().isEmpty() ? "0" : tfUnitPrice.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public String getCostName() {
        return tfCostName.getText();
    }

    public void setCostName(String name) {
        tfCostName.setText(name);
    }

    public void setUnitPrice(double price) {
        tfUnitPrice.setText(String.valueOf(price));
    }

    public void setQuantity(double quantity) {
        tfQuantity.setText(String.valueOf(quantity));
    }

    /**
     * MaterialDefinition veya LaborDefinition kullanarak formu otomatik doldurur
     * @param definition MaterialDefinition veya LaborDefinition nesnesi
     */
    public void populateFromDefinition(ProductDefinition.CostDefinition definition) {
        if (definition != null) {
            // Malzeme veya işçilik adını ayarla
            tfCostName.setText(definition.getName());

            // Birim fiyatını ayarla
            tfUnitPrice.setText(String.valueOf(definition.getBasePrice()));

            // Varsayılan miktar olarak 1 ayarla (kullanıcı gerekirse değiştirebilir)
            if (tfQuantity.getText().isEmpty()) {
                tfQuantity.setText("1");
            }

            // Toplam maliyeti güncelle
            updateTotal();
        }
    }

    /**
     * MaterialDefinition kullanarak formu doldurur
     * @param materialDef MaterialDefinition nesnesi
     */
    public void populateFromMaterialDefinition(ProductDefinition.MaterialDefinition materialDef) {
        populateFromDefinition(materialDef);
    }

    /**
     * LaborDefinition kullanarak formu doldurur
     * @param laborDef LaborDefinition nesnesi
     */
    public void populateFromLaborDefinition(ProductDefinition.LaborDefinition laborDef) {
        populateFromDefinition(laborDef);
    }
}

