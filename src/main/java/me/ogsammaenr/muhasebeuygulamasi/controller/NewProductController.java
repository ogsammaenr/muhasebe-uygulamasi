package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import me.ogsammaenr.muhasebeuygulamasi.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class NewProductController {
    private Map<String, Group> units = new HashMap<>();
    private int unitCounter = 1;


    @FXML
    public void initialize() {
        units.put("Unit-1", textGroup);
        parentPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            double centerX = newVal.doubleValue() / 2;
            next.setLayoutX(centerX + 190);
        });
    }

    @FXML
    public void onAddUnitClick(ActionEvent event) {
        Group newGroup = Utils.copyGroup(textGroup);

        String id = "Unit-" + unitCounter++;
        newGroup.setId(id);
        units.put(id, newGroup);

        int insertIndex = Math.max(containerVBox.getChildren().size() - 1, 0);
        containerVBox.getChildren().add(insertIndex, newGroup);


        Button btn = (Button) newGroup.getChildren().getLast();

        System.out.println(newGroup.getId());
        for (var node : newGroup.getChildren()) {
            System.out.println(node.getId());
        }
        btn.setOnAction(e -> handleDeleteUnit(newGroup));
    }

    @FXML
    public void onDeleteUnitClick(ActionEvent event) {

    }

    @FXML
    public void onNextClick(ActionEvent event) {

    }

    private void handleDeleteUnit(@NotNull Group unit) {
        units.remove(unit.getId());
        containerVBox.getChildren().remove(unit);
    }

    @FXML
    private Button addUnit;
    @FXML
    private Button deleteUnit;
    @FXML
    private Group textGroup;
    @FXML
    private VBox containerVBox;
    @FXML
    private AnchorPane parentPane;
    @FXML
    private Button next;

    /*=====================================*/

    @FXML
    private TextField txt_PVCFiyat;
    @FXML
    private TextField txt_PVCRenk;
    @FXML
    private TextField txt_PVCFirma;
    @FXML
    private TextField txt_dolar;
    @FXML
    private Label lbl_hammadde;
    @FXML
    private Label lbl_hammaddeAlan;
    @FXML
    private Label lbl_kesimFiyatBirim;
    @FXML
    private TextField txt_kesimFiyat;
    @FXML
    private Label lbl_CNCFiyatBirim;
    @FXML
    private TextField txt_CNCFiyat;
    @FXML
    private Label lbl_zimparaIscilikBirim;
    @FXML
    private TextField txt_zimparaIscilik;
    @FXML
    private Label lbl_zimparaFiyatBirim;
    @FXML
    private TextField txt_zimparaFiyat;
    @FXML
    private Label lbl_paletIscilikBirim;
    @FXML
    private TextField txt_paletIscilik;
    @FXML
    private Label lbl_tutkalFiyatBirim;
    @FXML
    private TextField txt_tutkalFiyat;
    @FXML
    private Label lbl_PVCBirim;
    @FXML
    private TextField txt_PVC;
    @FXML
    private Label lbl_basimIscilikBirim;
    @FXML
    private TextField txt_basimIscilik;
    @FXML
    private Label lbl_ambalajBirim;
    @FXML
    private TextField txt_ambalaj;
    @FXML
    private Label lbl_nakliyeBirim;
    @FXML
    private TextField txt_nakliye;
    @FXML
    private Label lbl_kalinlastirmaIscilikBirim;
    @FXML
    private TextField txt_kalinlastirmaIscilik;
    @FXML
    private Label lbl_karBirim;
    @FXML
    private TextField txt_karOran;
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
    @FXML
    private Button btn_save;

}
