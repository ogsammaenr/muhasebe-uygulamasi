package com.example.muhasebeuygulamasidevelop.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsViewController implements Initializable {
    // Sol Panel - Müşteri Listesi
    @FXML
    private VBox clientsListContainer;

    @FXML
    private Button sampleClientBtn;

    // Sağ Panel - Müşteri Detayları
    @FXML
    private VBox clientDetailsContainer;

    @FXML
    private VBox emptyStateContainer;

    @FXML
    private VBox clientDetailsContent;

    // Müşteri Detay Alanları
    @FXML
    private Label clientNameLabel;

    @FXML
    private Label registrationDateLabel;

    @FXML
    private Label lastActivityLabel;

    @FXML
    private Label totalContributionLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private TextArea notesArea;

    // Seçili müşteri butonu
    private Button selectedClientButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Başlangıç ayarları
        setupClientButtons();
        showEmptyState();
    }

    /**
     * Müşteri butonlarının olaylarını ayarla
     */
    private void setupClientButtons() {
        // Örnek müşteri butonu için olay tanımla
        sampleClientBtn.setOnAction(e -> selectClient(sampleClientBtn, "Örnek Firma A.Ş."));

        // Diğer müşteri butonları için de benzer şekilde olay tanımlanabilir
        // Bu örnekte sadece ilk buton için detay gösterimi yapılacak

        // Müşteri listesindeki tüm butonları al ve olay tanımla
        clientsListContainer.getChildren().forEach(node -> {
            if (node instanceof Button && node != sampleClientBtn) {
                Button btn = (Button) node;
                btn.setOnAction(e -> selectClient(btn, extractCompanyName(btn.getText())));
            }
        });
    }

    /**
     * Müşteri seçimi işlemi
     */
    private void selectClient(Button clientButton, String companyName) {
        // Önceki seçimi temizle
        if (selectedClientButton != null) {
            selectedClientButton.getStyleClass().remove("selected");
        }

        // Yeni seçimi ayarla
        selectedClientButton = clientButton;
        clientButton.getStyleClass().add("selected");

        // Müşteri detaylarını göster
        showClientDetails(companyName);
    }

    /**
     * Müşteri detaylarını göster
     */
    private void showClientDetails(String companyName) {
        // Boş durum ekranını gizle
        emptyStateContainer.setVisible(false);
        emptyStateContainer.setManaged(false);

        // Detay ekranını göster
        clientDetailsContent.setVisible(true);
        clientDetailsContent.setManaged(true);

        // Örnek verilerle doldur
        populateClientData(companyName);
    }

    /**
     * Boş durum ekranını göster
     */
    private void showEmptyState() {
        emptyStateContainer.setVisible(true);
        emptyStateContainer.setManaged(true);

        clientDetailsContent.setVisible(false);
        clientDetailsContent.setManaged(false);
    }

    /**
     * Müşteri verilerini doldur (Örnek veriler)
     */
    private void populateClientData(String companyName) {
        clientNameLabel.setText(companyName);

        // Örnek veriler - Gerçek uygulamada veritabanından gelecek
        switch (companyName) {
            case "Örnek Firma A.Ş.":
                registrationDateLabel.setText("15.03.2023");
                lastActivityLabel.setText("28.12.2024");
                totalContributionLabel.setText("125.750");
                phoneLabel.setText("+90 212 555 0123");
                emailLabel.setText("info@ornekfirma.com");
                notesArea.setText("Bu müşteri ile uzun süreli iş ortaklığımız bulunmaktadır. " +
                        "Aylık düzenli siparişleri vardır. Ödeme konusunda güvenilir bir firmadır. " +
                        "Özel indirim anlaşması mevcuttur.");
                break;

            case "Test Şirketi Ltd.":
                registrationDateLabel.setText("22.07.2023");
                lastActivityLabel.setText("25.12.2024");
                totalContributionLabel.setText("89.320");
                phoneLabel.setText("+90 216 444 5678");
                emailLabel.setText("contact@testshirketi.com");
                notesArea.setText("Yeni müşterimiz. İlk siparişlerinde memnun kalmışlar. " +
                        "Gelecek ay büyük bir proje için teklif bekliyor.");
                break;

            case "Demo İşletmesi":
                registrationDateLabel.setText("10.11.2023");
                lastActivityLabel.setText("20.12.2024");
                totalContributionLabel.setText("45.680");
                phoneLabel.setText("+90 312 333 9876");
                emailLabel.setText("demo@isletme.net");
                notesArea.setText("Küçük ölçekli işletme. Düzenli olmasa da sürekli müşterimiz. " +
                        "Fiyat konusunda hassas.");
                break;

            case "Deneme Ticaret":
                registrationDateLabel.setText("05.09.2023");
                lastActivityLabel.setText("18.12.2024");
                totalContributionLabel.setText("67.890");
                phoneLabel.setText("+90 232 777 4321");
                emailLabel.setText("info@denemeticaret.com");
                notesArea.setText("Toptan alım yapan müşteri. Sezonluk siparişler veriyor. " +
                        "Yaz aylarında daha aktif.");
                break;

            case "Prototip A.Ş.":
                registrationDateLabel.setText("18.12.2023");
                lastActivityLabel.setText("27.12.2024");
                totalContributionLabel.setText("156.420");
                phoneLabel.setText("+90 262 888 1234");
                emailLabel.setText("prototip@firma.com.tr");
                notesArea.setText("En büyük müşterilerimizden biri. Teknoloji sektöründe faaliyet gösteriyor. " +
                        "Kalite standartları yüksek. Premium ürünleri tercih ediyor.");
                break;

            default:
                // Varsayılan değerler
                registrationDateLabel.setText("--");
                lastActivityLabel.setText("--");
                totalContributionLabel.setText("0");
                phoneLabel.setText("--");
                emailLabel.setText("--");
                notesArea.setText("Bu müşteri hakkında henüz detaylı bilgi bulunmamaktadır.");
                break;
        }
    }

    /**
     * Buton metninden firma adını çıkar
     */
    private String extractCompanyName(String buttonText) {
        // Emoji ve boşlukları temizle
        return buttonText.replaceAll("^[^\\p{L}]+", "").trim();
    }

    /**
     * Yeni müşteri ekleme metodu (gelecekte kullanılabilir)
     */
    public void addNewClient(String companyName) {
        Button newClientBtn = new Button("🏢  " + companyName);
        newClientBtn.getStyleClass().add("client-button");
        newClientBtn.setMaxWidth(Double.MAX_VALUE);
        newClientBtn.setOnAction(e -> selectClient(newClientBtn, companyName));

        clientsListContainer.getChildren().add(newClientBtn);
    }

    /**
     * Seçili müşteriyi kaldırma metodu (gelecekte kullanılabilir)
     */
    public void removeSelectedClient() {
        if (selectedClientButton != null) {
            clientsListContainer.getChildren().remove(selectedClientButton);
            selectedClientButton = null;
            showEmptyState();
        }
    }
}

