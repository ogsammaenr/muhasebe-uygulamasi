package com.example.muhasebeuygulamasidevelop.controller;

import com.example.muhasebeuygulamasidevelop.controller.items.MdfItemController;
import com.example.muhasebeuygulamasidevelop.controller.items.WorkItemRowController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CostCalculationController {
    @FXML
    private Label teamTotalTimeLabel;

    @FXML
    private Button saveButton
            , addExtraLaborButton;

    @FXML
    private VBox extraLaborVbox;


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

    @FXML
    public void initialize() {

        addExtraLaborButton.setOnAction(e -> addExtraLaborButtonOnAction(e) );
        saveButton.setOnAction(e -> saveButtonOnAction(e));

        addMdfItem("18mm", "12.5 m²", "120 ₺", "1.500 ₺");
        addMdfItem("22mm", "8 m²", "150 ₺", "1.200 ₺");
        addMdfItem("18mm", "12.5 m²", "120 ₺", "1.500 ₺");
        addMdfItem("22mm", "8 m²", "150 ₺", "1.200 ₺");
        addMdfItem("18mm", "12.5 m²", "120 ₺", "1.500 ₺");
        addMdfItem("22mm", "8 m²", "150 ₺", "1.200 ₺");

    }

    @FXML
    private VBox mdfList;

    public void addMdfItem(String name, String quantity, String price, String totalPrice) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/items/MDF-item.fxml"));
            VBox mdfItem = loader.load();

            MdfItemController controller = loader.getController();
            controller.setMdfName(name);
            controller.setMdfQuantity(quantity);
            controller.setMdfPrice(price);
            controller.setMdfTotalPrice(totalPrice);

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
}
