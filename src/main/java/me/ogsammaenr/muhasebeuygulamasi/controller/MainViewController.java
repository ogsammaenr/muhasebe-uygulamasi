package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.ogsammaenr.muhasebeuygulamasi.MainApplication;

import java.io.IOException;
import java.net.URL;

public class MainViewController {
    MainApplication mainApp = MainApplication.getInstance();
    private Button clickedButton;
    private ContextMenu contextMenu;
    private Stage stage;
    private Scene scene;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void initialize() {
        setupContextMenu();
        setupListeners();

    }

    @FXML
    public void onNewProductClick(MouseEvent event) {
        clickedButton = btn_newProduct;

        if (event.getButton() == MouseButton.PRIMARY) {

            try {
                URL fxmlUrl = getClass().getResource("/me/ogsammaenr/muhasebeuygulamasi/new-product-view.fxml");
                System.out.println("FXML URL: " + fxmlUrl);
                FXMLLoader loader = new FXMLLoader(fxmlUrl);

                Parent newProductView = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Product Muhasebe Uygulamasi");
                stage.initOwner(MainApplication.getInstance().getStage());
                stage.initModality(Modality.WINDOW_MODAL);

                stage.setMinWidth(550);
                stage.setMinHeight(300);
                stage.setScene(new Scene(newProductView));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void onTitleBarClicked(MouseEvent event) {
        // Mouse'un pencere üzerindeki konumunu kaydet (sahne içi konum)
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    public void onTitleBarDragged(MouseEvent event) {
        Stage stage = (Stage) titleBar.getScene().getWindow();
        // Pencerenin yeni konumunu hesapla
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    private void setupContextMenu() {
        contextMenu = new ContextMenu();

        MenuItem editItem = new MenuItem("Düzenle");
        MenuItem deleteItem = new MenuItem("Sil");

        editItem.setOnAction(e -> onEdit(clickedButton));
        deleteItem.setOnAction(e -> onDelete(clickedButton));

        contextMenu.getItems().addAll(editItem, deleteItem);
    }

    private void setupListeners() {
    }

    // 🔹 Her buton için sağ tık dinleyicisi kurar
    private void setupRightClick(Button button) {
        button.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                clickedButton = button; // 👈 Sağ tıklanan buton burada tutulur
                contextMenu.show(button, event.getScreenX(), event.getScreenY());
            }
        });
    }

    private void onEdit(Button source) {
        System.out.println(source.getText() + " için Düzenle tıklandı");
    }

    private void onDelete(Button source) {
        System.out.println(source.getText() + " için Sil tıklandı");
    }

    @FXML
    private Button btn_newProduct;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane titleBar;
    @FXML
    private Group ekran;
    @FXML
    private Label lbl_x;
    @FXML
    private Label lbl_y;
    @FXML
    private Label lbl_z;
    @FXML
    private Label lbl_adet;
    @FXML
    private Label lbl_takimFiyat;
    @FXML
    private Label lbl_birimFiyat;
    @FXML
    private Label lbl_hammaddeFiyat;
    @FXML
    private Label lbl_hammaddeBirimFiyat;
    @FXML
    private Label lbl_iscilikFiyat;
    @FXML
    private Label lbl_iscilikBirimFiyat;
    @FXML
    private Label lbl_kar;
    @FXML
    private Label lbl_birimKar;


}

