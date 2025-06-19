package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import me.ogsammaenr.muhasebeuygulamasi.manager.UnitsManager;
import me.ogsammaenr.muhasebeuygulamasi.model.Unit;
import me.ogsammaenr.muhasebeuygulamasi.util.Utils;

import java.util.List;
import java.util.Map;

public class NewProductController2 {

    private Map<String, Unit> unitMap;

    private UnitsManager unitsManager;
    private double totalTime;
    private double totalArea;

    @FXML
    public void initialize() {
        setupListeners();

    }

    public void setUnitsManager(UnitsManager unitsManager) {
        this.unitsManager = unitsManager;
        totalTime = unitsManager.getTotalTime();
        totalArea = unitsManager.getTotalArea();

        addHammaddeGroups();
    }

    @FXML
    public void onSaveClick(ActionEvent event) {

    }

    @FXML
    public void onKesimChange(KeyEvent event) {
        if (txt_kesimFiyat.getStyle().equals("-fx-text-fill: red;")) {
            System.out.println("işlem yapılmaz");
            return;
        }
        if (txt_kesimFiyat.getText().isEmpty()) {
            lbl_kesimFiyatBirim.setText("");
            return;
        }

        double birimFiyat = (Double.parseDouble(txt_kesimFiyat.getText()) / 5.88) * totalArea;

        lbl_kesimFiyatBirim.setText(String.valueOf(Utils.round(birimFiyat, 2)));
        System.out.println("işlem yapıldı");

    }

    @FXML
    public void onCncChange(KeyEvent e) {
        if (txt_CNCFiyat.getStyle().equals("-fx-text-fill: red;")) return;
        if (txt_CNCFiyat.getText().isEmpty()) {
            lbl_CNCFiyatBirim.setText("");
            return;
        }

        double birimfiyat = Double.parseDouble(txt_CNCFiyat.getText()) * totalTime;
        lbl_CNCFiyatBirim.setText(String.valueOf(Utils.round(birimfiyat, 2)));
    }

    @FXML
    public void onKalinlastirmaChange(InputMethodEvent e) {

    }

    @FXML
    public void onKarChange(InputMethodEvent e) {

    }

    @FXML
    public void onPaletlemeChange(InputMethodEvent e) {

    }

    @FXML
    public void onPvcChange(InputMethodEvent e) {

    }

    @FXML
    public void onZimparaIscilikChange(InputMethodEvent e) {

    }

    @FXML
    public void onZimparaChange(InputMethodEvent e) {

    }

    @FXML
    public void onTutkalChange(InputMethodEvent e) {

    }

    @FXML
    public void onBasimIscilikChange(InputMethodEvent e) {

    }

    @FXML
    public void onAmbalajChange(InputMethodEvent e) {

    }

    @FXML
    public void onNakliyeChange(InputMethodEvent e) {

    }

    private void setupListeners() {
        txt_ambalaj.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_ambalaj));
        txt_dolar.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_dolar));
        txt_CNCFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_CNCFiyat));
        txt_kesimFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_kesimFiyat));
        txt_nakliye.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_nakliye));
        txt_basimIscilik.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_basimIscilik));
        txt_kalinlastirmaIscilik.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_kalinlastirmaIscilik));
        txt_karOran.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_karOran));
        txt_paletIscilik.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_paletIscilik));
        txt_PVC.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_PVC));
        txt_PVCFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_PVCFiyat));
        txt_zimparaIscilik.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_zimparaIscilik));
        txt_zimparaFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_zimparaFiyat));
        txt_tutkalFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_tutkalFiyat));
    }

    private void addHammaddeGroups() {
        List<Double> allThickness = unitsManager.getAllThickness();

        for (double thickness : allThickness) {
            Group group = Utils.copyGroup(hammaddeBilgiGroup);

            Label hammadde = (Label) group.getChildren().get(0);
            Label alan = (Label) group.getChildren().get(1);

            hammadde.setText("MDF " + Utils.round(thickness, 0));
            alan.setText("" + unitsManager.thicnessToArea(thickness));

            hammaddeVBox.getChildren().add(1, group);
        }
    }

    private void handleCheckValues(TextField node) {
        try {
            String str = node.getText();
            if (str.isEmpty()) {
                node.setStyle("-fx-text-fill: black;");
                return;
            }

            if (str.endsWith("f") || str.endsWith("F")) {
                node.setStyle("-fx-text-fill: red;");
                return;
            }

            double value = Double.parseDouble(str);
            node.setStyle("-fx-text-fill: black;");
        } catch (Exception e) {
            node.setStyle("-fx-text-fill: red;");
        }
    }


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
    @FXML
    private VBox hammaddeVBox;
    @FXML
    private VBox mainVBox;
    @FXML
    private Group hammaddeBilgiGroup;
}
