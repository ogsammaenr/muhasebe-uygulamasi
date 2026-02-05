package me.ogsammaenr.muhasebeuygulamasiv3.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private BorderPane mainContainer;

    @FXML
    private Button btnMusteriEkle,
            btnUrunEkle,
            btnMusteriListesi,
            btnUrunListesi,
            btnHammadde;

    @FXML
    private Label lblToplamMusteri,
            lblToplamUrun;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnMusteriEkle.setOnAction(event -> loadAddCustomerForm());
        btnUrunEkle.setOnAction(event -> loadAddProductForm());
        btnMusteriListesi.setOnAction(event -> loadCustomerList());
        btnUrunListesi.setOnAction(event -> System.out.println("Ürün Listesi Açılacak"));
        btnHammadde.setOnAction(event -> loadMaterialList());
    }

    public void loadCustomerList() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/customer-list.fxml")
            );
            CustomerListController controller = new CustomerListController();
            controller.setMainController(this);
            fxmlLoader.setController(controller);
            mainContainer.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println("Müşteri listesi yüklenemedi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadAddCustomerForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/add-customer.fxml")
            );
            AddCustomerController controller = new AddCustomerController();
            controller.setMainController(this);
            fxmlLoader.setController(controller);
            mainContainer.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println("Müşteri ekleme formu yüklenemedi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadDashboard() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/main-view.fxml")
            );
            BorderPane dashboard = fxmlLoader.load();
            mainContainer.setCenter(dashboard.getCenter());
        } catch (IOException e) {
            System.err.println("Dashboard yüklenemedi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadMaterialList() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/material-list.fxml")
            );
            mainContainer.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println("Hammadde fiyatları sayfası yüklenemedi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadAddProductForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/add-product.fxml")
            );
            AddProductController controller = new AddProductController();
            controller.setMainController(this);
            fxmlLoader.setController(controller);
            mainContainer.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println("Ürün ekleme formu yüklenemedi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
