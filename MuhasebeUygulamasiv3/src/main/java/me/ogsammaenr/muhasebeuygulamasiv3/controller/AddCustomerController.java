package me.ogsammaenr.muhasebeuygulamasiv3.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

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

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnKaydet.setOnAction(event -> kaydetMusteri());
        btnIptal.setOnAction(event -> iptalET());
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

        // TODO: Veritabanına kaydet
        System.out.println("Müşteri Kaydedildi:");
        System.out.println("Firma Adı: " + firmaAdi);
        System.out.println("E-posta: " + eposta);
        System.out.println("Telefon: " + telefon);
        System.out.println("Notlar: " + notlar);

        // Formu temizle ve ana ekrana dön
        temizleForm();
        if (mainController != null) {
            mainController.loadDashboard();
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

