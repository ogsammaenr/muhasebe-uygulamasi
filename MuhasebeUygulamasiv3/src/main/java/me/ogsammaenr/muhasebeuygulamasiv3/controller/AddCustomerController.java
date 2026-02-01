package me.ogsammaenr.muhasebeuygulamasiv3.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import me.ogsammaenr.muhasebeuygulamasiv3.manager.CustomerManager;
import me.ogsammaenr.muhasebeuygulamasiv3.model.Customer;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddCustomerController implements Initializable {

    @FXML
    private VBox formRoot;

    @FXML
    private TextField tfFirmaAdi;

    @FXML
    private TextField tfEposta;

    @FXML
    private TextField tfTelefon;

    @FXML
    private TextArea taNotlar;

    @FXML
    private Button btnKaydet;

    @FXML
    private Button btnIptal;

    private MainController mainController;

    private CustomerManager customerManager;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnKaydet.setOnAction(event -> kaydetMusteri());
        btnIptal.setOnAction(event -> iptalET());

        customerManager = CustomerManager.getInstance();
    }

    private void kaydetMusteri() {
        String firmaAdi = tfFirmaAdi.getText().trim();
        String eposta = tfEposta.getText().trim();
        String telefon = tfTelefon.getText().trim();
        String notlar = taNotlar.getText().trim();

        // Validation
        if (firmaAdi.isEmpty() || eposta.isEmpty() || telefon.isEmpty()) {
            System.out.println("Lütfen tüm zorunlu alanları doldurunuz!");
            return;
        }

        // Yeni müşteri oluştur ve CustomerManager'a ekle
        Customer newCustomer = new Customer(
                UUID.randomUUID().toString(),
                firmaAdi,
                eposta,
                telefon,
                notlar
        );

        customerManager.addCustomer(newCustomer);

        System.out.println("Müşteri Kaydedildi:");
        System.out.println("Firma Adı: " + firmaAdi);
        System.out.println("E-posta: " + eposta);
        System.out.println("Telefon: " + telefon);
        System.out.println("Notlar: " + notlar);

        // Formu temizle ve müşteri listesine dön
        temizleForm();
        if (mainController != null) {
            mainController.loadCustomerList();
        }
    }

    private void iptalET() {
        temizleForm();
        if (mainController != null) {
            mainController.loadDashboard();
        }
    }

    private void temizleForm() {
        tfFirmaAdi.clear();
        tfEposta.clear();
        tfTelefon.clear();
        taNotlar.clear();
    }
}

