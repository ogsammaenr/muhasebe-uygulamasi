package com.example.muhasebeuygulamasidevelop.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Ana Menu Controller Class
 * Muhasebe Sistemi Ana Menü İşlemleri
 */
public class MainViewController implements Initializable {

    @FXML
    private AnchorPane mainContentArea;

    @FXML
    private Button musteriEkleBtn
            , musteriGoruntuleBtn
            , urunEkleBtn
            , urunGoruntuleBtn
            , genelDurumBtn
            , hammaddeBtn;

    private Stage currentMusteriChildStage = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Başlangıç ayarları
        setupButtonEvents();
        Platform.runLater(() -> mainContentArea.requestFocus());

    }

    /**
     * Buton olaylarını ayarla
     */
    private void setupButtonEvents() {
        musteriEkleBtn.setOnAction(e -> musteriEkleAction());
        musteriGoruntuleBtn.setOnAction(e -> musteriGoruntuleAction());
        urunEkleBtn.setOnAction(e -> urunEkleAction());
        urunGoruntuleBtn.setOnAction(e -> urunGoruntuleAction());
        genelDurumBtn.setOnAction(e -> genelDurumAction());
        hammaddeBtn.setOnAction(e -> hammaddeAction());
    }

    /**
     * Müşteri Ekleme İşlemi
     */
    private void musteriEkleAction() {
        System.out.println("Müşteri Ekleme sayfası açılıyor...");

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/new-client-view.fxml"));
            Parent newClientView = loader.load();

            Stage root = (Stage) mainContentArea.getScene().getWindow();

            if (currentMusteriChildStage != null && currentMusteriChildStage.isShowing()) {
                currentMusteriChildStage.toFront();
                System.out.println("Müşteri Ekleme sayfası zaten açık!!!");
                return;
            }

            Scene scene = new Scene(newClientView);
            scene.getStylesheets().add(getClass().getResource("/css/newClient.css").toExternalForm());
            Stage stg = new Stage();
            stg.initOwner(root);
            stg.setScene(scene);
            stg.setTitle("Client Input");
            stg.initModality(Modality.NONE);

            stg.setOnHidden(e -> currentMusteriChildStage = null);
            currentMusteriChildStage = stg;
            stg.show();


        }catch (Exception e){
            e.printStackTrace();

        }
    }
    

    /**
     * Müşteri Görüntüleme İşlemi
     */
    private void musteriGoruntuleAction() {
        System.out.println("Müşteri Görüntüleme sayfası açılıyor...");

        if (!mainContentArea.getChildren().isEmpty()
                && ("clients-container").equals(mainContentArea.getChildren().getFirst().getStyleClass().getFirst())){
            System.out.println("müşteri görüntüleme iptal edildi");
            return;
        }

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/clients-view.fxml"));
            Parent clientsView = loader.load();

            mainContentArea.getChildren().clear();
            mainContentArea.getChildren().add(clientsView);
            AnchorPane.setBottomAnchor(clientsView, 0.0);
            AnchorPane.setTopAnchor(clientsView, 0.0);
            AnchorPane.setLeftAnchor(clientsView, 0.0);
            AnchorPane.setRightAnchor(clientsView, 0.0);
            Stage stg = (Stage) mainContentArea.getScene().getWindow();
            stg.setMinWidth(1000);
            stg.setMinHeight(700);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Ürün Ekleme İşlemi
     */
    private void urunEkleAction() {
        System.out.println("Ürün Ekleme sayfası açılıyor...");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cost-calculation-view.fxml"));
            Parent mdfInputView = loader.load();

            Scene mdfInputScene = new Scene(mdfInputView);
            mdfInputScene.getStylesheets().add(getClass().getResource("/css/newProduct.css").toExternalForm());
            Stage stg = new Stage();
            stg.setScene(mdfInputScene);
            stg.setTitle("MDF Input");
            stg.setMinHeight(700);
            stg.setMinWidth(1200);
            stg.setMaxWidth(1200);
            stg.show();


        }catch (Exception e){
            e.printStackTrace();
            System.out.println();
            System.err.println("Ürün Ekleme Ekranı Açılamadı");
        }
    }

    /**
     * Ürün Görüntüleme İşlemi
     */
    private void urunGoruntuleAction() {
        System.out.println("Ürün Görüntüleme sayfası açılıyor...");

        if (!mainContentArea.getChildren().isEmpty()
        && "products-container".equals(mainContentArea.getChildren().getFirst().getStyleClass().getFirst())){
            System.out.println("ürün görüntüleme iptal edildi");
            return;
        }
        System.out.println(mainContentArea.getChildren().getFirst().getStyleClass().getFirst());


        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/products-view.fxml"));
            Parent productsView = loader.load();

            mainContentArea.getChildren().clear();
            mainContentArea.getChildren().add(productsView);
            AnchorPane.setBottomAnchor(productsView, 0.0);
            AnchorPane.setTopAnchor(productsView, 0.0);
            AnchorPane.setLeftAnchor(productsView, 0.0);
            AnchorPane.setRightAnchor(productsView, 0.0);

            Stage stg = (Stage) mainContentArea.getScene().getWindow();
            stg.setTitle("Ürün Görüntüleme Ekranı");
            stg.setMinWidth(1400);
            stg.setMinHeight(800);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Genel Durum İşlemi
     */
    private void genelDurumAction() {
        System.out.println("Genel Durum sayfası açılıyor...");
        // Buraya genel durum FXML'ini yükleme kodu gelecek
    }

    /**
     * Hammadde işlemleri
     */
    private void hammaddeAction() {
        System.out.println("hammadde sayfası açılıyor...");
    }
}
