package com.example.muhasebeuygulamasidevelop.controller;

import com.example.muhasebeuygulamasidevelop.controller.items.MdfItemController;
import com.example.muhasebeuygulamasidevelop.controller.items.WorkItemRowController;
import com.example.muhasebeuygulamasidevelop.model.MdfData;
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

        addMdfItem(18, 450, 450, 5, 0, 1000);
        addMdfItem(22, 450, 450, 10, 5, 1500);
        addMdfItem(30, 450, 450, 5, 0, 1000);


    }

    @FXML
    private VBox mdfList;

    public void addMdfItem(int thickness, double width, double height, double patternTime, double drillTime, double unitPrice) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/items/MDF-item.fxml"));
            VBox mdfItem = loader.load();

            MdfData data = new MdfData(thickness, width, height, patternTime, drillTime, unitPrice);

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
}
