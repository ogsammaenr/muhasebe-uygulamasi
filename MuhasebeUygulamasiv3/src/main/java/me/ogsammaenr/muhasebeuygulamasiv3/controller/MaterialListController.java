package me.ogsammaenr.muhasebeuygulamasiv3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.ogsammaenr.muhasebeuygulamasiv3.manager.DefinitionManager;
import me.ogsammaenr.muhasebeuygulamasiv3.model.ProductDefinition;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MaterialListController {

    @FXML private VBox materialListRoot;
    @FXML private TextField tfSearch;
    @FXML private ComboBox<String> cbDateFilter;
    @FXML private ListView<ProductDefinition.MaterialDefinition> lvMaterials;

    @FXML private Label lblEmptyState;
    @FXML private VBox vbMaterialInfo;
    @FXML private Label lblDetailName;
    @FXML private Label lblDetailUnit;
    @FXML private Label lblDetailPrice;
    @FXML private Label lblDetailCurrency;
    @FXML private Label lblDetailCurrencyName;
    @FXML private TextField tfNewPrice;
    @FXML private Button btnCancel;
    @FXML private Button btnSave;

    private ProductDefinition.MaterialDefinition selectedMaterial;
    private DefinitionManager definitionManager;

    @FXML
    public void initialize() {
        definitionManager = DefinitionManager.getInstance();

        // Başlangıç tarih filtresini populate et
        setupDateFilter();

        // Hammaddeleri listele
        loadMaterials();

        // ListView Cell Factory'sini ayarla
        setupListViewCellFactory();

        // ListView seçim dinleyicisini ayarla
        lvMaterials.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                displayMaterialDetails(newVal);
            } else {
                clearDetails();
            }
        });

        // Buton event'lerini ayarla
        btnSave.setOnAction(event -> saveMaterialPrice());
        btnCancel.setOnAction(event -> cancelEdit());

        // Arama yeteneğini ayarla
        tfSearch.textProperty().addListener((obs, oldVal, newVal) -> filterMaterials());
    }

    private void setupDateFilter() {
        ArrayList<String> dates = new ArrayList<>();
        dates.add("Bugün");
        dates.add("Geçmiş Tarihleri Gör");

        // Daha sonra gerçek tarihler eklenebilir
        cbDateFilter.getItems().addAll(dates);
        cbDateFilter.valueProperty().addListener((obs, oldVal, newVal) -> filterMaterials());
    }

    private void loadMaterials() {
        lvMaterials.getItems().clear();
        lvMaterials.getItems().addAll(definitionManager.getMaterials());
    }

    private void setupListViewCellFactory() {
        lvMaterials.setCellFactory(param -> new MaterialListCell());
    }

    private void filterMaterials() {
        String searchText = tfSearch.getText().toLowerCase();
        ArrayList<ProductDefinition.MaterialDefinition> filtered = new ArrayList<>();

        for (ProductDefinition.MaterialDefinition material : definitionManager.getMaterials()) {
            if (material.getName().toLowerCase().contains(searchText)) {
                filtered.add(material);
            }
        }

        lvMaterials.getItems().clear();
        lvMaterials.getItems().addAll(filtered);
    }

    private void displayMaterialDetails(ProductDefinition.MaterialDefinition material) {
        selectedMaterial = material;

        // Detay bilgilerini güncelle
        lblDetailName.setText(material.getName());
        lblDetailUnit.setText(material.getUnitType().toString());
        lblDetailPrice.setText(String.format("%.2f", material.getBasePrice()));
        lblDetailCurrency.setText(material.getCurrency() != null ? material.getCurrency() : "TL");
        lblDetailCurrencyName.setText(material.getCurrency() != null ? material.getCurrency() : "Türk Lirası");

        // UI öğelerini göster/gizle
        lblEmptyState.setVisible(false);
        lblEmptyState.setManaged(false);
        vbMaterialInfo.setVisible(true);
        vbMaterialInfo.setManaged(true);

        // Fiyat alanını temizle
        tfNewPrice.clear();
    }

    private void clearDetails() {
        selectedMaterial = null;
        lblEmptyState.setVisible(true);
        lblEmptyState.setManaged(true);
        vbMaterialInfo.setVisible(false);
        vbMaterialInfo.setManaged(false);
    }

    private void saveMaterialPrice() {
        if (selectedMaterial == null) {
            return;
        }

        try {
            String priceText = tfNewPrice.getText().trim();
            if (priceText.isEmpty()) {
                showError("Lütfen yeni fiyatı giriniz!");
                return;
            }

            double newPrice = Double.parseDouble(priceText);
            if (newPrice < 0) {
                showError("Fiyat negatif olamaz!");
                return;
            }

            // Fiyatı güncelle (Not: Gerçek uygulamada veritabanına kaydedilir)
            lblDetailPrice.setText(String.format("%.2f", newPrice));
            tfNewPrice.clear();

            showSuccess("Fiyat başarıyla güncellendi!");

        } catch (NumberFormatException e) {
            showError("Geçersiz fiyat formatı!");
        }
    }

    private void cancelEdit() {
        tfNewPrice.clear();
    }

    private void showError(String message) {
        // Daha sonra uygun UI ile gösterilecek
        System.err.println(message);
    }

    private void showSuccess(String message) {
        // Daha sonra uygun UI ile gösterilecek
        System.out.println(message);
    }

    // İç Sınıf: Material List Cell
    private class MaterialListCell extends ListCell<ProductDefinition.MaterialDefinition> {
        private HBox cellContent;
        private Label lblMaterialName;
        private Label lblUnitType;
        private Label lblPrice;

        public MaterialListCell() {
            try {
                FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/material-list-item.fxml")
                );
                cellContent = loader.load();

                // FXML öğelerini bul
                lblMaterialName = (Label) cellContent.lookup("#lblMaterialName");
                lblUnitType = (Label) cellContent.lookup("#lblUnitType");
                lblPrice = (Label) cellContent.lookup("#lblPrice");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void updateItem(ProductDefinition.MaterialDefinition item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                lblMaterialName.setText(item.getName());
                lblUnitType.setText(item.getUnitType().toString());
                lblPrice.setText(String.format("%.2f %s", item.getBasePrice(), item.getCurrency() != null ? item.getCurrency() : "TL"));
                setGraphic(cellContent);
            }
        }
    }
}

