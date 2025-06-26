package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
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
    public void onClientsClick(MouseEvent event) {
        if (main_pane.getChildren().size() > 0 &&
            "clients-view.fxml".equals(main_pane.getChildren().get(0).getId())) {
            return; // zaten yüklü
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/ogsammaenr/muhasebeuygulamasi/clients-view.fxml"));
            Parent newClientsView = loader.load();


            main_pane.getChildren().clear();
            main_pane.getChildren().add(newClientsView);
            AnchorPane.setTopAnchor(newClientsView, 0.0);
            AnchorPane.setLeftAnchor(newClientsView, 0.0);
            AnchorPane.setRightAnchor(newClientsView, 0.0);
            AnchorPane.setBottomAnchor(newClientsView, 0.0);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
    private Button btn_newProduct, btn_clients;
    @FXML
    private AnchorPane stagePane, main_pane;


}

