package me.ogsammaenr.muhasebeuygulamasiv3.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import me.ogsammaenr.muhasebeuygulamasiv3.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class CustomerListController implements Initializable {

    @FXML
    private ListView<Customer> customerListView;

    @FXML
    private Label lblFirmaAdi;

    @FXML
    private Label lblEposta;

    @FXML
    private Label lblTelefon;

    @FXML
    private Label lblNotlar;

    @FXML
    private VBox detailsContainer;

    @FXML
    private Button btnYeniMusteri;

    @FXML
    private Button btnSil;

    @FXML
    private Button btnDuzenle;

    @FXML
    private Button btnKapat;

    private MainController mainController;
    private ObservableList<Customer> customers;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customers = FXCollections.observableArrayList();
        customerListView.setItems(customers);

        // Özel ListCell oluştur
        customerListView.setCellFactory(param -> new CustomerListCell());

        // Liste seçim olayını dinle
        customerListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        displayCustomerDetails(newVal);
                    }
                }
        );

        // Buton olayları
        btnYeniMusteri.setOnAction(event -> loadAddCustomerForm());
        btnSil.setOnAction(event -> deleteSelectedCustomer());
        btnDuzenle.setOnAction(event -> editSelectedCustomer());
        btnKapat.setOnAction(event -> clearDetails());

        // Örnek müşteri verisi ekle (demo için)
        addSampleCustomers();
    }

    /**
     * Örnek müşteri verisi ekler (demo için)
     */
    private void addSampleCustomers() {
        customers.addAll(
                new Customer(UUID.randomUUID().toString(), "ABC Şirketler A.Ş.", "contact@abc.com", "+90 555 123 4567", "İstanbul'da bulunan üretim şirketi"),
                new Customer(UUID.randomUUID().toString(), "XYZ Ticaret Ltd.", "info@xyz.com", "+90 555 234 5678", "İhracat ve ithalatla uğraşan firma"),
                new Customer(UUID.randomUUID().toString(), "Tekno İnova Teknolojileri", "sales@tekno.com", "+90 555 345 6789", "Yazılım geliştirme ve danışmanlık"),
                new Customer(UUID.randomUUID().toString(), "Marmara Lojistik", "logistics@marmara.com", "+90 555 456 7890", "Ulaştırma ve depolama hizmetleri"),
                new Customer(UUID.randomUUID().toString(), "Doğu Harita Mühendislik", "engineering@doguharita.com", "+90 555 567 8901", "İnşaat ve harita mühendisliği")
        );
    }

    /**
     * Seçili müşterinin detaylarını göster
     */
    private void displayCustomerDetails(Customer customer) {
        lblFirmaAdi.setText(customer.getFirmaAdi());
        lblEposta.setText(customer.getEposta());
        lblTelefon.setText(customer.getTelefon());
        lblNotlar.setText(customer.getNotlar().isEmpty() ? "---" : customer.getNotlar());
        detailsContainer.setStyle("-fx-opacity: 1;");
    }

    /**
     * Detayları temizle
     */
    private void clearDetails() {
        lblFirmaAdi.setText("---");
        lblEposta.setText("---");
        lblTelefon.setText("---");
        lblNotlar.setText("---");
        customerListView.getSelectionModel().clearSelection();
        detailsContainer.setStyle("-fx-opacity: 0.5;");
    }

    /**
     * Seçili müşteri sil
     */
    private void deleteSelectedCustomer() {
        Customer selected = customerListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            customers.remove(selected);
            clearDetails();
            System.out.println("Müşteri silindi: " + selected.getFirmaAdi());
        }
    }

    /**
     * Seçili müşteri düzenle
     */
    private void editSelectedCustomer() {
        Customer selected = customerListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            System.out.println("Müşteri düzenleme: " + selected.getFirmaAdi());
            // TODO: Düzenleme formu aç
        }
    }

    /**
     * Yeni müşteri ekleme formunu yükle
     */
    private void loadAddCustomerForm() {
        if (mainController != null) {
            mainController.loadAddCustomerForm();
        }
    }

    /**
     * Müşteri ekle (AddCustomerController tarafından çağrılır)
     */
    public void addCustomer(String firmaAdi, String eposta, String telefon, String notlar) {
        Customer newCustomer = new Customer(
                UUID.randomUUID().toString(),
                firmaAdi,
                eposta,
                telefon,
                notlar
        );
        customers.add(newCustomer);
        System.out.println("Müşteri eklendi: " + firmaAdi);
    }

    /**
     * Özel ListCell sınıfı - Lista öğelerini FXML ile göster
     */
    private class CustomerListCell extends ListCell<Customer> {
        @Override
        protected void updateItem(Customer item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(
                            getClass().getResource("/me/ogsammaenr/muhasebeuygulamasiv3/customer-list-item.fxml")
                    );
                    CustomerListItemController controller = new CustomerListItemController();
                    fxmlLoader.setController(controller);
                    Parent itemContent = fxmlLoader.load();

                    controller.setCustomer(item);
                    setGraphic(itemContent);
                    setText(null);

                } catch (IOException e) {
                    System.err.println("Liste öğesi yüklenemedi: " + e.getMessage());
                    setText(item.getFirmaAdi());
                }
            }
        }
    }
}

