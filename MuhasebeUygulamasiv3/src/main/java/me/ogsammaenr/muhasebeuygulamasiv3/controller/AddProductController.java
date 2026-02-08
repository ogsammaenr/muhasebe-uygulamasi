package me.ogsammaenr.muhasebeuygulamasiv3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import me.ogsammaenr.muhasebeuygulamasiv3.manager.DefinitionManager;
import me.ogsammaenr.muhasebeuygulamasiv3.model.ProductDefinition;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AddProductController implements Initializable {

    // === FXML ELEMENTS ===
    @FXML private TextField tfCompanyName;
    @FXML private TextField tfModelName;
    @FXML private VBox vbMdfDimensions;
    @FXML private Button btnAddDimension;
    @FXML private Label lblTotalM2;
    @FXML private Label lbCncTime;
    @FXML private TextField tfDollarRate;
    @FXML private VBox vbMaterials;
    @FXML private VBox vbLabor;
    @FXML private Label lblUnitPrice;
    @FXML private Label lblTotalPrice;
    @FXML private Label lblMaterialM2;
    @FXML private Label lblMaterialTotal;
    @FXML private Label lblLaborM2;
    @FXML private Label lblLaborTotal;
    @FXML private Label lblProfitRate;
    @FXML private Label lblProfitM2;
    @FXML private Label lblProfitTotal;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;
    @FXML private Button btnAddMaterial;
    @FXML private Button btnAddLabor;

    // === MERKEZI VERİ YÖNETİMİ ===
    private HashMap<String, Double> mdfMap = new HashMap<>();  // "MDF 08" -> 0.5 (m²)
    private DefinitionManager definitionManager;
    private List<MdfDimensionItemController> mdfControllers = new ArrayList<>();
    private MainController mainController;  // Dönüş için referans

    // === HESAPLANAN VERİLER (SAĞ TARAF) ===
    private double totalM2 = 0;
    private double totalCncTime = 0;
    private double totalMaterialCost = 0;
    private double totalLaborCost = 0;
    private double totalCost = 0;
    private double dollarRate = 40.0; // varsayılan

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        definitionManager = DefinitionManager.getInstance();

        // Ölçü ekleme butonu
        btnAddDimension.setOnAction(event -> addMdfDimension());

        // Hammadde ekle butonu
        btnAddMaterial.setOnAction(event -> generateMaterialCostItems());

        // İşçilik ekle butonu
        btnAddLabor.setOnAction(event -> generateLaborCostItems());

        // Dolar kuru değişikliğini dinle
        tfDollarRate.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                dollarRate = Double.parseDouble(newVal.replace(",", ".").isEmpty() ? "40" : newVal.replace(",", "."));
                updateAllCosts();
                // Dolar kuru değişince kalemlerin fiyatlarını da güncelle
                generateMaterialCostItems();
                generateLaborCostItems();
            } catch (NumberFormatException e) {
                dollarRate = 40.0;
            }
        });

        tfDollarRate.setText("40.00");
    }

    /**
     * MainController'ı ayarla (FXML loader'dan sonra çağrılır)
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Yeni MDF ölçüsü için item ekle
     */
    private void addMdfDimension() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/mdf-dimension-item.fxml"));
            Node itemNode = loader.load();
            MdfDimensionItemController controller = loader.getController();

            // Veri değiştiğinde callback
            controller.setOnDataChanged(this::onMdfDataChanged);

            mdfControllers.add(controller);
            vbMdfDimensions.getChildren().add(itemNode);

            // Sil butonunu uygun hale getir
            setupDeleteButton(controller, itemNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * MDF öğesi için sil butonu ayarla
     */
    private void setupDeleteButton(MdfDimensionItemController controller, Node itemNode) {
        Button deleteBtn = (Button) itemNode.lookup("#btnDelete");
        if (deleteBtn != null) {
            deleteBtn.setOnAction(event -> {
                vbMdfDimensions.getChildren().remove(itemNode);
                mdfControllers.remove(controller);
                onMdfDataChanged();
            });
        }
    }

    /**
     * MDF verisi değiştiğinde çağrıl
     * HashMapi güncelle ve maliyetleri yeniden hesapla
     */
    private void onMdfDataChanged() {
        // HashMap'i güncelle
        mdfMap.clear();
        totalM2 = 0;
        totalCncTime = 0;

        for (MdfDimensionItemController controller : mdfControllers) {
            String mdfType = controller.getMdfType();
            double area = controller.getArea();
            double cncTime = controller.getTotalCncTime();

            mdfMap.put(mdfType, mdfMap.getOrDefault(mdfType, 0.0) + area);
            totalM2 += area;
            totalCncTime += cncTime;
        }

        // UI güncelle
        updateMdfDisplay();
        updateAllCosts();
        generateMaterialCostItems();
        generateLaborCostItems();
    }

    /**
     * MDF ekranını güncelle (sol taraf)
     */
    private void updateMdfDisplay() {
        lblTotalM2.setText(String.format("%.4f m²", totalM2));
        lbCncTime.setText(String.format("%.1f dk", totalCncTime));
    }

    /**
     * Tüm maliyetleri yeniden hesapla ve UI güncellemeleri yap
     */
    private void updateAllCosts() {
        calculateMaterialCosts();
        calculateLaborCosts();

        double totalCost = totalMaterialCost + totalLaborCost;

        // Birim fiyatlar
        double unitMaterialCost = totalM2 > 0 ? totalMaterialCost / totalM2 : 0;
        double unitLaborCost = totalM2 > 0 ? totalLaborCost / totalM2 : 0;
        double unitTotalCost = totalM2 > 0 ? totalCost / totalM2 : 0;

        // Sağ taraf güncelleme
        lblMaterialM2.setText(String.format("%.2f ₺", unitMaterialCost));
        lblMaterialTotal.setText(String.format("%.2f ₺", totalMaterialCost));

        lblLaborM2.setText(String.format("%.2f ₺", unitLaborCost));
        lblLaborTotal.setText(String.format("%.2f ₺", totalLaborCost));

        lblUnitPrice.setText(String.format("%.2f ₺", unitTotalCost));
        lblTotalPrice.setText(String.format("%.2f ₺", totalCost));

        // KAR HESAPLAMASI (şimdilik 0 olarak göster, daha sonra kar oranı eklenecek)
        lblProfitRate.setText("0,00 %");
        lblProfitM2.setText("0,00 ₺");
        lblProfitTotal.setText("0,00 ₺");
    }

    /**
     * HAMMADDE MALİYETLERİNİ HESAPLA
     * Formül: (MDF'ye özel alan VEYA tüm alan) * birim fiyat * çarpan * dolar kuru
     */
    private void calculateMaterialCosts() {
        totalMaterialCost = 0;

        for (ProductDefinition.MaterialDefinition material : definitionManager.getMaterials()) {
            double area = 0;
            String materialName = material.getName();

            // MDF ise kendi m²'sini, diğer ise toplam m²'yi kullan
            if (materialName.startsWith("MDF")) {
                area = mdfMap.getOrDefault(materialName, 0.0);
            } else {
                area = totalM2;
            }

            // Maliyet hesapla: alan * birim fiyat * çarpan * dolar kuru
            double basePrice = material.getBasePrice();
            double multiplier = material.getMultiplier();

            if ("USD".equalsIgnoreCase(material.getCurrency())) {
                basePrice *= dollarRate;
            }

            double cost = area * basePrice * multiplier;
            totalMaterialCost += cost;
        }
    }

    /**
     * İŞÇİLİK MALİYETLERİNİ HESAPLA
     * Formül: birim fiyat * (unitType uygun miktarı)
     * - M2: toplam m²
     * - DAKIKA: toplam CNC süresi
     * - Diğerleri: 1 (sabit)
     */
    private void calculateLaborCosts() {
        totalLaborCost = 0;

        for (ProductDefinition.LaborDefinition labor : definitionManager.getLabors()) {
            double quantity = 0;
            ProductDefinition.UnitType unitType = labor.getUnitType();

            // UnitType'a göre miktar belirle
            if (unitType == ProductDefinition.UnitType.M2) {
                quantity = totalM2;
            } else if (unitType == ProductDefinition.UnitType.DAKIKA) {
                quantity = totalCncTime;
            } else if (unitType == ProductDefinition.UnitType.PLAKA ||
                       unitType == ProductDefinition.UnitType.ADET ||
                       unitType == ProductDefinition.UnitType.KG) {
                quantity = 1; // Sabit
            }

            // Maliyet hesapla
            double basePrice = labor.getBasePrice();

            if ("USD".equalsIgnoreCase(labor.getCurrency())) {
                basePrice *= dollarRate;
            }

            double cost = quantity * basePrice;
            totalLaborCost += cost;
        }
    }

    /**
     * Hammadde öğelerini sadece maliyeti olanlar için oluştur
     */
    private void generateMaterialCostItems() {
        vbMaterials.getChildren().clear();
        for (ProductDefinition.MaterialDefinition material : definitionManager.getMaterials()) {
            double area = 0;
            boolean isEditable = false;
            // Eğer MDF ise sadece o MDF'nin alanını al
            if (material.getName().startsWith("MDF")) {
                area = mdfMap.getOrDefault(material.getName(), 0.0);
            } else {
                // MDF değilse (Tutkal, Mebran vb.) toplam m2'yi al
                area = totalM2;
                isEditable = true;
            }

            // SADECE ALANI OLANLARI LİSTEYE EKLE
            if (area > 0) {
                vbMaterials.getChildren().add(createCostItemDisplay(material, area, isEditable));
            }
        }
    }

    /**
     * İşçilik öğelerini dinamik olarak oluştur ve görüntüle
     */
    private void generateLaborCostItems() {
        vbLabor.getChildren().clear();
        for (ProductDefinition.LaborDefinition labor : definitionManager.getLabors()) {
            double quantity = 0;
            switch (labor.getUnitType()) {
                case M2 -> quantity = totalM2;
                case DAKIKA -> quantity = totalCncTime;
                default -> quantity = 1;
            }

            // SADECE MİKTARI OLANLARI LİSTEYE EKLE
            if (quantity > 0) {
                vbLabor.getChildren().add(createCostItemDisplay(labor, quantity, true));
            }
        }
    }

    /**
     * Belirli bir malzemenin maliyetini hesapla
     */
    private double calculateMaterialItemCost(ProductDefinition.MaterialDefinition material) {
        double area = 0;

        if (material.getName().startsWith("MDF")) {
            area = mdfMap.getOrDefault(material.getName(), 0.0);
        } else {
            area = totalM2;
        }

        double basePrice = material.getBasePrice();
        double multiplier = material.getMultiplier();

        if ("USD".equalsIgnoreCase(material.getCurrency())) {
            basePrice *= dollarRate;
        }

        return area * basePrice * multiplier;
    }

    /**
     * cost-item.fxml'i yükler ve veriyi bind eder
     */
    private Node createCostItemDisplay(ProductDefinition.CostDefinition definition, double quantity, boolean isBasePriceEditable) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/cost-item.fxml"));
            CostItemController controller = new CostItemController();

            loader.setController(controller);
            Node node = loader.load();

            controller.bind(definition, quantity, dollarRate);
            controller.setUnitPriceEditable(isBasePriceEditable);

            return node;
        } catch (IOException e) {
            e.printStackTrace();
            return new Label("Hata: " + definition.getName());
        }
    }
}

