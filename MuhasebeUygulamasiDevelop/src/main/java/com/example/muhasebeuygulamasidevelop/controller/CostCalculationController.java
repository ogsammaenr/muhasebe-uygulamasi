package com.example.muhasebeuygulamasidevelop.controller;

import com.example.muhasebeuygulamasidevelop.controller.items.MdfItemController;
import com.example.muhasebeuygulamasidevelop.controller.items.WorkItemRowController;
import com.example.muhasebeuygulamasidevelop.model.MdfData;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CostCalculationController {
    @FXML
    private Label teamTotalTimeLabel;

    @FXML
    private Button saveButton
            , addExtraLaborButton
            , addMdfButton;

    @FXML
    private VBox extraLaborVbox;

    @FXML
    private VBox mdfList;

    /*-*-*-*-   Sağ Panel   -*-*-*-*/
    @FXML
    private Label profitRateSummaryLabel
            , profitValueLabel
            , totalPerM2Label
            , totalOverallLabel
            , laborPerM2Label
            , laborTotalLabel
            , materialPerM2Label
            , materialTotalLabel;

    @FXML
    private ComboBox<String> customerComboBox;
    @FXML
    private TextField searchCustomerField;
    @FXML
    private Button addCustomerButton;
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    /****************************************************/

    /*-*-*-*-   Orta Panel   -*-*-*-*/
    @FXML
    private TextField cncUnitField
            , cuttingUnitField
            , sandingLaborField
            , sandingMaterialField
            , palletingField
            , glueField
            , pvcField
            , printingLaborField
            , packagingField
            , shippingField
            , profitRateField;
    @FXML
    private Label cncTotalLabel
            , cuttingTotalLabel
            , sandingLaborTotalLabel
            , sandingMaterialTotalLabel
            , palletingTotalLabel
            , glueTotalLabel
            , pvcTotalLabel
            , printingLaborTotalLabel
            , packagingTotalLabel
            , shippingTotalLabel
            , profitTotalLabel;
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/


    private Stage currentMusteriChildStage = null;

    private String selectedCustomer = null;
    private ObservableList<String> masterCustomerList;

    @FXML
    public void initialize() {

        setupCustomerComboBox();
        setupSearchField();

        addExtraLaborButton.setOnAction(e -> addExtraLaborButtonOnAction(e) );
        saveButton.setOnAction(e -> saveButtonOnAction(e));
        addMdfButton.setOnAction(e -> addMdfButtonOnAction(e));

        addMdfItem(18, 450, 450, 5, 0, 1000, 1);
        addMdfItem(22, 450, 450, 10, 5, 1500, 4);
        addMdfItem(30, 450, 450, 5, 0, 1000, 5);


    }

    private void setupCustomerComboBox() {
        masterCustomerList = FXCollections.observableArrayList(
                "Veli Karasu", "Caner Turan", "Osman Aytaş", "Mahmut Gerçek", "Hasan Berk", "Walter White", "Hank Shreider"
        );

        customerComboBox.setItems(FXCollections.observableArrayList(masterCustomerList));
        customerComboBox.setOnAction(e -> {
            if (customerComboBox.getSelectionModel().getSelectedItem() != null) {
                selectedCustomer = customerComboBox.getSelectionModel().getSelectedItem();
                System.out.println("[INFO] Selected Customer : " + selectedCustomer);
            }else  {
                System.out.println("[ERROR] Selected Customer is null");
                customerComboBox.getSelectionModel().select(selectedCustomer);
            }
        });
        addCustomerButton.setOnAction(e -> openNewCustomerScene());
    }

    private void setupSearchField() {
        searchCustomerField.textProperty().addListener((obs, oldVal, newVal) -> {
            String query = newVal == null ? "" : newVal.toLowerCase();

            List<String> filtered = masterCustomerList.stream()
                    .filter(name -> name.toLowerCase().contains(query) || name.equals(selectedCustomer))
                    .collect(Collectors.toList());

            customerComboBox.getItems().setAll(filtered);
            if (!customerComboBox.isShowing()) {
                customerComboBox.show();
            }
        });
    }

    private void openNewCustomerScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/new-client-view.fxml"));
            Parent newClientView = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Yeni Müşteri Ekle");
            stage.setScene(new Scene(newClientView));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMdfItem(int thickness, double width, double height, double patternTime, double drillTime, double unitPrice, int count) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/items/MDF-item.fxml"));
            VBox mdfItem = loader.load();

            MdfData data = new MdfData(thickness, width, height, patternTime, drillTime, unitPrice, count);

            MdfItemController controller = loader.getController();

            controller.setData(data);

            mdfList.getChildren().add(mdfItem);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addExtraLaborButtonOnAction(ActionEvent actionEvent) {
        WorkItemRowController workItem = new WorkItemRowController();
        extraLaborVbox.getChildren().add(extraLaborVbox.getChildren().size() - 1, workItem.getRoot());

    }

    private void saveButtonOnAction(ActionEvent actionEvent) {

    }

    private void addMdfButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MDF-input-view.fxml"));
            Scene scene = new Scene(loader.load());

            scene.getStylesheets().add(getClass().getResource("/css/newProduct.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setMinHeight(450);
            stage.setMinWidth(750);
            stage.setMaxHeight(450);
            stage.setMaxWidth(750);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
