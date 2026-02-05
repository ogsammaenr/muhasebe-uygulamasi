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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancel.setOnAction(event -> handleCancel());
        btnSave.setOnAction(event -> handleSave());
        btnAddDimension.setOnAction(event -> handleAddDimension());

        // İlk ölçü satırını ekle - javafx.application.Platform.runLater kullanarak gecikmeli çalıştır
        javafx.application.Platform.runLater(this::addDimensionRow);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void addDimensionRow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/mdf-dimension-item.fxml"));
            Node dimensionItem = loader.load();

            // Controller referansını al
            MdfDimensionItemController itemController = loader.getController();
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

