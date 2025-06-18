package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

public class NewProductController2 {

    @FXML
    public void initialize() {
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

    @FXML
    public void onSaveClick(ActionEvent event) {

    }

    @FXML
    public void onKesimChange(InputMethodEvent e) {
        if (txt_kesimFiyat.getStyle().contains("-fx-text-fill: red")) {
            System.out.println("işlem yapılmaz");
            return;
        }
        if (txt_kesimFiyat.getText().isEmpty()) {
            System.out.println("işlem yapılamaz");
            return;
        }

        System.out.println("işlem yapıldı");


    }

    @FXML
    public void onCncChange(InputMethodEvent e) {

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
    

    private void handleCheckValues(TextField node) {
        try {
            String str = node.getText();
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
}
