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
    @FXML
    private TextField tfCompanyName,
            tfModelName,
            tfDollarRate;

    @FXML
    private Label lblTotalM2,
            lblUnitPrice,
            lblTotalPrice,
            lblMaterialM2,
            lblMaterialTotal,
            lblLaborM2,
            lblLaborTotal,
            lblProfitRate,
            lblProfitM2,
            lblProfitTotal,
            lbCncTime;

    @FXML
    private VBox vbMdfDimensions,
            vbMaterials,
            vbLabor;

    @FXML
    private Button btnAddDimension,
            btnSave,
            btnCancel;

    private MainController mainController;
    private DefinitionManager definitionManager;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        definitionManager = DefinitionManager.getInstance();

        btnCancel.setOnAction(event -> handleCancel());
        btnSave.setOnAction(event -> handleSave());
        btnAddDimension.setOnAction(event -> handleAddDimension());

        // Definition'ları yükleme işlemini gecikmeli yap
        javafx.application.Platform.runLater(() -> {

            ProductDefinition.MaterialDefinition pvc = definitionManager.getMaterialByName("PVC");
                addMaterialRowFromDefinition(pvc);

            ProductDefinition.LaborDefinition kesim = definitionManager.getLaborByName("Kesim (Plaka)");
                addLaborRowFromDefinition(kesim);

            ProductDefinition.LaborDefinition cnc = definitionManager.getLaborByName("CNC (Dakika)");
                addLaborRowFromDefinition(cnc);

        });

        // İlk ölçü satırını ekle
        javafx.application.Platform.runLater(this::addDimensionRow);
    }


    /**
     * MaterialDefinition kullanarak otomatik olarak hammadde satırı eklenir
     * @param materialDef Eklenecek MaterialDefinition nesnesi
     */
    public void addMaterialRowFromDefinition(ProductDefinition.MaterialDefinition materialDef) {
        if (materialDef == null) {
            System.err.println("MaterialDefinition null olarak geldi");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/cost-item.fxml"));

            // Controller'ı oluştur ve loader'a sağla
            CostItemController itemController = new CostItemController();
            loader.setController(itemController);

            Node costItem = loader.load();


            itemController.setParentController(this);
            itemController.setParentContainer(vbMaterials);
            itemController.setCostType("Hammadde");

            // Definition'dan formu doldur
            itemController.populateFromMaterialDefinition(materialDef);

            // Root node'a controller referansını kaydet
            costItem.setUserData(itemController);

            vbMaterials.getChildren().add(costItem);
            updateCostCalculations();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Hammadde item yüklenemedi: " + e.getMessage());
        }
    }

    /**
     * LaborDefinition kullanarak otomatik olarak işçilik satırı eklenir
     * @param laborDef Eklenecek LaborDefinition nesnesi
     */
    public void addLaborRowFromDefinition(ProductDefinition.LaborDefinition laborDef) {
        if (laborDef == null) {
            System.err.println("LaborDefinition null olarak geldi");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/cost-item.fxml"));

            // Controller'ı oluştur ve loader'a sağla
            CostItemController itemController = new CostItemController();
            loader.setController(itemController);

            Node costItem = loader.load();


            itemController.setParentController(this);
            itemController.setParentContainer(vbLabor);
            itemController.setCostType("İşçilik");

            // Definition'dan formu doldur
            itemController.populateFromLaborDefinition(laborDef);

            // Root node'a controller referansını kaydet
            costItem.setUserData(itemController);

            vbLabor.getChildren().add(costItem);
            updateCostCalculations();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("İşçilik item yüklenemedi: " + e.getMessage());
        }
    }

    // Genel cost row ekleme metodu
    private void addCostRow(VBox container, String costType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/cost-item.fxml"));

            // Controller'ı oluştur ve loader'a sağla
            CostItemController itemController = new CostItemController();
            loader.setController(itemController);

            Node costItem = loader.load();

            itemController.setParentController(this);
            itemController.setParentContainer(container);
            itemController.setCostType(costType);

            // Root node'a controller referansını kaydet
            costItem.setUserData(itemController);

            container.getChildren().add(costItem);
            updateCostCalculations();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(costType + " item yüklenemedi: " + e.getMessage());
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void addDimensionRow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/mdf-dimension-item.fxml"));

            // Controller'ı oluştur ve loader'a sağla
            MdfDimensionItemController itemController = new MdfDimensionItemController();
            loader.setController(itemController);

            Node dimensionItem = loader.load();

            itemController.setParentController(this);
            itemController.setParentContainer(vbMdfDimensions);

            // Root node'a controller referansını kaydet
            dimensionItem.setUserData(itemController);

            vbMdfDimensions.getChildren().add(dimensionItem);
            updateTotalM2();
            updateTotalCncTime();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("MDF ölçü item yüklenemedi: " + e.getMessage());
        }
    }

    private void handleAddDimension() {
        addDimensionRow();
    }

    protected void updateTotalM2() {
        double totalM2 = 0.0;

        for (Node node : vbMdfDimensions.getChildren()) {
            // Her öğeden alanı al ve topla
            Object userData = node.getUserData();
            if (userData instanceof MdfDimensionItemController) {
                MdfDimensionItemController itemController = (MdfDimensionItemController) userData;
                totalM2 += itemController.getArea();
            }
        }

        lblTotalM2.setText(String.format("%.2f m²", totalM2));
    }

    protected void updateTotalCncTime() {
        double totalCncTime = 0.0;

        for (Node node : vbMdfDimensions.getChildren()) {
            // Her öğeden CNC süresini al ve topla
            Object userData = node.getUserData();
            if (userData instanceof MdfDimensionItemController) {
                MdfDimensionItemController itemController = (MdfDimensionItemController) userData;
                totalCncTime += itemController.getCncTime();
            }
        }

        lbCncTime.setText(String.format("%.1f", totalCncTime));
    }

    /**
     * MDF ölçüleri değiştiğinde hammadde listesini günceller
     * Tüm MDF ölçülerinden hangi kalınlıkların kullanıldığını tespit eder ve
     * karşılık gelen MDF hammaddelerini otomatik olarak ekler/günceller
     */
    public void updateMaterialFromDimension() {
        // Tüm MDF ölçülerinden hangi kalınlıkların kullanıldığını ve toplam m²'yi hesapla
        Map<String, Double> mdfThicknessMap = new HashMap<>();

        for (Node node : vbMdfDimensions.getChildren()) {
            Object userData = node.getUserData();
            if (userData instanceof MdfDimensionItemController) {
                MdfDimensionItemController itemController = (MdfDimensionItemController) userData;
                double area = itemController.getArea();
                String thickness = itemController.getThickness();

                // Kalınlık stringini "mm" cinsinden al (örn: "18 mm" -> "18")
                String thicknessNum = thickness.replaceAll(" mm", "");

                // Padding yap: "8" -> "08", "18" -> "18"
                if (thicknessNum.length() == 1) {
                    thicknessNum = "0" + thicknessNum;
                }

                String mdfKey = "MDF " + thicknessNum;

                // Aynı kalınlıktan birden fazla varsa toplam et
                mdfThicknessMap.put(mdfKey, mdfThicknessMap.getOrDefault(mdfKey, 0.0) + area);
            }
        }

        // Şu anda hammadde listesindeki tüm MDF'leri takip et
        Set<String> existingMdfMaterials = new HashSet<>();
        for (Node node : vbMaterials.getChildren()) {
            Object userData = node.getUserData();
            if (userData instanceof CostItemController) {
                CostItemController itemController = (CostItemController) userData;
                String costName = itemController.getCostName();
                if (costName.startsWith("MDF")) {
                    existingMdfMaterials.add(costName);
                }
            }
        }

        // Hammadde listesindeki mevcut MDF'leri güncelle ve silinecekleri belirle
        Set<String> mdfToRemove = new HashSet<>(existingMdfMaterials);
        for (Node node : vbMaterials.getChildren()) {
            Object userData = node.getUserData();
            if (userData instanceof CostItemController) {
                CostItemController itemController = (CostItemController) userData;
                String costName = itemController.getCostName();

                // Bu MDF hala kullanılıyor mu?
                if (mdfThicknessMap.containsKey(costName)) {
                    // Evet, miktarını güncelle
                    double multiplier = 1.12 / 5.88; // kayıplar için çarpan
                    itemController.setQuantity(mdfThicknessMap.get(costName) * multiplier);
                    mdfToRemove.remove(costName);
                    mdfThicknessMap.remove(costName); // Eklediğimiz olarak işaretle
                }
            }
        }

        // Artık kullanılmayan MDF'leri sil
        Iterator<Node> iterator = vbMaterials.getChildren().iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            Object userData = node.getUserData();
            if (userData instanceof CostItemController) {
                CostItemController itemController = (CostItemController) userData;
                if (mdfToRemove.contains(itemController.getCostName())) {
                    iterator.remove();
                }
            }
        }

        // Yeni MDF'leri ekle (mdf² > 0 olanlar)
        for (String mdfName : mdfThicknessMap.keySet()) {
            double area = mdfThicknessMap.get(mdfName);
            if (area > 0) {
                ProductDefinition.MaterialDefinition mdfDef = definitionManager.getMaterialByName(mdfName);
                if (mdfDef != null) {
                    addMaterialRowFromDefinition(mdfDef);
                    // Eklenen MDF'nin miktarını ayarla
                    if (!vbMaterials.getChildren().isEmpty()) {
                        Node lastNode = vbMaterials.getChildren().get(vbMaterials.getChildren().size() - 1);
                        Object userData = lastNode.getUserData();
                        if (userData instanceof CostItemController) {
                            CostItemController itemController = (CostItemController) userData;
                            double multiplier = 1.12 / 5.88; // kayıplar için çarpan

                            itemController.setQuantity(area * multiplier);
                        }
                    }
                }
            }
        }

        // Maliyet hesaplamalarını güncelle
        updateCostCalculations();
    }

    // Maliyet hesaplamalarını güncelle
    public void updateCostCalculations() {
        double totalM2 = Double.parseDouble(lblTotalM2.getText().replace(" m²", "").replace(",", "."));

        double materialTotalCost = 0.0;
        double materialM2Cost = 0.0;
        double laborTotalCost = 0.0;
        double laborM2Cost = 0.0;

        // Hammadde toplamları hesapla
        for (Node node : vbMaterials.getChildren()) {
            Object userData = node.getUserData();
            if (userData instanceof CostItemController) {
                CostItemController itemController = (CostItemController) userData;
                materialTotalCost += itemController.getTotalCost();
            }
        }

        // İşçilik toplamları hesapla
        for (Node node : vbLabor.getChildren()) {
            Object userData = node.getUserData();
            if (userData instanceof CostItemController) {
                CostItemController itemController = (CostItemController) userData;
                laborTotalCost += itemController.getTotalCost();
            }
        }

        // M² başına maliyetleri hesapla
        if (totalM2 > 0) {
            materialM2Cost = materialTotalCost / totalM2;
            laborM2Cost = laborTotalCost / totalM2;
        }

        // UI'da güncelle
        lblMaterialM2.setText(String.format("%.2f ₺", materialM2Cost));
        lblMaterialTotal.setText(String.format("%.2f ₺", materialTotalCost));
        lblLaborM2.setText(String.format("%.2f ₺", laborM2Cost));
        lblLaborTotal.setText(String.format("%.2f ₺", laborTotalCost));

        // Birim fiyat ve toplam fiyat
        double unitPrice = materialM2Cost + laborM2Cost;
        double totalPrice = materialTotalCost + laborTotalCost;

        lblUnitPrice.setText(String.format("%.2f ₺", unitPrice));
        lblTotalPrice.setText(String.format("%.2f ₺", totalPrice));

        // Kar hesapla (ileri sürümlerde kar oranı ve miktarı eklenecek)
        lblProfitRate.setText("0,00 %");
        lblProfitM2.setText("0,00 ₺");
        lblProfitTotal.setText("0,00 ₺");
    }

    private void handleCancel() {
        if (mainController != null) {
            mainController.loadDashboard();
        }
    }

    private void handleSave() {
        // TODO: Ürün kaydetme işlemleri yapılacak
        System.out.println("Ürün kaydedilecek");
        handleCancel();
    }
}

