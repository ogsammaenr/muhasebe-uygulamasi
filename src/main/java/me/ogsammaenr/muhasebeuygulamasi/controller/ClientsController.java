package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.ogsammaenr.muhasebeuygulamasi.MainApplication;
import me.ogsammaenr.muhasebeuygulamasi.manager.ClientsManager;
import me.ogsammaenr.muhasebeuygulamasi.model.Client;

public class ClientsController {
    private ClientsManager clientsManager;

    @FXML
    public void initialize() {
        clientsManager = new ClientsManager();

        lbl_totalClient.setText(String.valueOf(clientsManager.getClientCount()));

        setupClients();
    }

    @FXML
    public void onNewClientClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/me/ogsammaenr/muhasebeuygulamasi/new-client-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 500, 500);
            Image icon = new Image(getClass().getResource("/me/ogsammaenr/muhasebeuygulamasi/icons/logo.png").toExternalForm());


            Stage stage = new Stage();
            stage.initOwner(MainApplication.getInstance().getStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.setTitle("Yeni Müşteri");

            stage.show();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setupClients() {
        for (Client client : clientsManager.getAllClients()) {
            Button clientLabel = new Button();
            clientLabel.setText(client.getCompanyName());
            clientLabel.getStyleClass().add("client_display");

            clientLabel.setMinWidth(btn_clientScreen.getMinWidth());
            clientLabel.setMinHeight(btn_clientScreen.getMinHeight());
            clientLabel.setPrefWidth(btn_clientScreen.getPrefWidth());
            clientLabel.setPrefHeight(btn_clientScreen.getPrefHeight());
            clientLabel.setMaxHeight(btn_clientScreen.getMaxHeight());
            clientLabel.setMaxWidth(btn_clientScreen.getMaxWidth());

            clientLabel.setOnMouseClicked(event -> handleClientButtonAction(event, client));

            clientsVBox.getChildren().add(1, clientLabel);
        }
    }

    private void handleClientButtonAction(MouseEvent event, Client client) {
        if (event.getButton() == MouseButton.PRIMARY) {
            lbl_companyName.setText(client.getCompanyName());
            lbl_registerDate.setText(client.getRegistrationDate().toString());
            lbl_lastActionDate.setText(client.getLastActionDate().toString());
            lbl_eMail.setText(client.getEMailAddress());
            lbl_telNo.setText(client.getPhoneNumber());
            lbl_notes.setText(client.getNotes());
        }

    }

    @FXML
    private Button btn_newClient, btn_search, btn_clientScreen;
    @FXML
    private Label lbl_totalClient, lbl_companyName, lbl_productCount,
            lbl_totalPayment, lbl_registerDate, lbl_lastActionDate,
            lbl_eMail, lbl_telNo, lbl_notes;
    @FXML
    private VBox clientsVBox;
    @FXML
    private TextField txt_searchBar;
}
