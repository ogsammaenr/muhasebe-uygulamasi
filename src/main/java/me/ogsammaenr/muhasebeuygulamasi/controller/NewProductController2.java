package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    private double PvcFiyat;
    private double dolar;

    private double kesimFiyat;
    private double CncFiyat;
    private double zimparaIscilikFiyat;
    private double zimparaFiyat;
    private double paletlemeIscilikFiyat;
    private double tutkalFiyat;
    private double PvcMasraf;
    private double basimIscilikFiyat;
    private double ambalajFiyat;
    private double nakliyeFiyat;
    private double ekIscilikFiyat;

    private double kar;

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
    public void onKesimChange() {
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
        if (txt_CNCFiyat.getStyle().equals("-fx-text-fill: red;") || txt_CNCFiyat.getText().isEmpty()) {
            lbl_CNCFiyatBirim.setText("");
            return;
        }

        double birimfiyat = Double.parseDouble(txt_CNCFiyat.getText()) * totalTime;
        lbl_CNCFiyatBirim.setText(String.valueOf(Utils.round(birimfiyat, 2)));
    }

    @FXML
    public void onZimparaIscilikChange(KeyEvent event) {
        if (txt_zimparaIscilik.getStyle().equals("-fx-text-fill: red;") || txt_zimparaIscilik.getText().isEmpty()) {
            lbl_zimparaIscilikBirim.setText("");
            return;
        }

        double birimfiyat = Double.parseDouble(txt_zimparaIscilik.getText()) * totalArea;
        lbl_zimparaIscilikBirim.setText(String.valueOf(Utils.round(birimfiyat, 2)));
    }

    @FXML
    public void onZimparaChange(KeyEvent event) {
        if (txt_zimparaFiyat.getStyle().equals("-fx-text-fill: red;") || txt_zimparaFiyat.getText().isEmpty()) {
            lbl_zimparaFiyatBirim.setText("");
            return;
        }

        double birimfiyat = Double.parseDouble(txt_zimparaFiyat.getText()) * totalArea;
        lbl_zimparaFiyatBirim.setText(String.valueOf(Utils.round(birimfiyat, 2)));
    }

    @FXML
    public void onPaletIscilikChange(KeyEvent event) {
        if (txt_paletIscilik.getStyle().equals("-fx-text-fill: red;") || txt_paletIscilik.getText().isEmpty()) {
            lbl_paletIscilikBirim.setText("");
            return;
        }

        double birimfiyat = Double.parseDouble(txt_paletIscilik.getText()) * totalArea;
        lbl_paletIscilikBirim.setText(String.valueOf(Utils.round(birimfiyat, 2)));
    }

    @FXML
    public void onTutkalFiyatChange(KeyEvent event) {
        if (txt_tutkalFiyat.getStyle().equals("-fx-text-fill: red;") || txt_tutkalFiyat.getText().isEmpty()) {
            lbl_tutkalFiyatBirim.setText("");
            return;
        }

        double birimfiyat = Double.parseDouble(txt_tutkalFiyat.getText()) * totalArea;
        lbl_tutkalFiyatBirim.setText(String.valueOf(Utils.round(birimfiyat, 2)));
    }

    @FXML
    public void onBasimIscilikChange(KeyEvent event) {
        if (txt_basimIscilik.getStyle().equals("-fx-text-fill: red;") || txt_basimIscilik.getText().isEmpty()) {
            lbl_basimIscilikBirim.setText("");
            return;
        }

        double birimfiyat = Double.parseDouble(txt_basimIscilik.getText()) * totalArea;
        lbl_basimIscilikBirim.setText(String.valueOf(Utils.round(birimfiyat, 2)));
    }

    @FXML
    public void onAmbalajChange(KeyEvent event) {
        if (txt_ambalaj.getStyle().equals("-fx-text-fill: red;") || txt_ambalaj.getText().isEmpty()) {
            lbl_ambalajBirim.setText("");
            return;
        }

        double birimfiyat = Double.parseDouble(txt_ambalaj.getText()) * totalArea;
        lbl_ambalajBirim.setText(String.valueOf(Utils.round(birimfiyat, 2)));
    }

    @FXML
    public void onNakliyeChange(KeyEvent event) {
        if (txt_nakliye.getStyle().equals("-fx-text-fill: red;") || txt_nakliye.getText().isEmpty()) {
            lbl_nakliyeBirim.setText("");
            return;
        }

        double birimfiyat = Double.parseDouble(txt_nakliye.getText()) * totalArea;
        lbl_nakliyeBirim.setText(String.valueOf(Utils.round(birimfiyat, 2)));
    }

    @FXML
    public void onEkIscilikChange(KeyEvent event) {
        if (txt_ekIscilik.getStyle().equals("-fx-text-fill: red;") || txt_ekIscilik.getText().isEmpty()) {
            lbl_ekIscilikBirim.setText("");
            return;
        }

        double birimfiyat = Double.parseDouble(txt_ekIscilik.getText()) * totalArea;
        lbl_ekIscilikBirim.setText(String.valueOf(Utils.round(birimfiyat, 2)));
    }

    @FXML
    public void onPvcFiyatChange() {

    }

    @FXML
    public void onPvcChange() {

    }

    @FXML
    public void onDolarChange() {

    }

    private void addHammaddeGroups() {
        List<Integer> allThickness = unitsManager.getAllThickness();

        for (int thickness : allThickness) {
            Group group = Utils.copyGroup(hammaddeBilgiGroup);
            Group hammaddeGrp = Utils.copyGroup(hammaddeGroup);

            Label hammadde = (Label) group.getChildren().get(0);
            Label alan = (Label) group.getChildren().get(1);

            hammadde.setText("MDF " + thickness);
            alan.setText(String.valueOf(unitsManager.thicnessToArea(thickness)));

            Label hammaddeLabel = (Label) hammaddeGrp.getChildren().get(0);
            TextField hammaddeFiyat = (TextField) hammaddeGrp.getChildren().get(1);

            hammaddeFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(hammaddeFiyat));
            hammaddeFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleHammaddeGroups(hammaddeFiyat));

            hammaddeLabel.setText("MDF " + thickness);
            hammaddeGrp.setId("" + Utils.round(thickness, 2));

            mainVBox.getChildren().add(1, hammaddeGrp);
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

    private void handleHammaddeGroups(TextField node) {
        Group group = (Group) node.getParent();

        if (node.getStyle().equals("-fx-text-fill: black;") && !node.getText().isEmpty()) {
            double fiyat = Utils.round(Double.parseDouble(node.getText()), 2);
            double sureMiktar = unitsManager.thicnessToArea(Double.parseDouble(group.getId())) * (1.12 / 5.88);

            double birimFiyat = Utils.round(fiyat * sureMiktar, 2);

            Label birimEkran = (Label) group.getChildren().getLast();

            birimEkran.setText(String.valueOf(birimFiyat));
        } else {
            Label birimEkran = (Label) group.getChildren().getLast();
            birimEkran.setText("");
        }

    }


    private void setupListeners() {
        txt_ambalaj.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_ambalaj));
        txt_dolar.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_dolar));
        txt_CNCFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_CNCFiyat));
        txt_kesimFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_kesimFiyat));
        txt_nakliye.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_nakliye));
        txt_basimIscilik.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_basimIscilik));
        txt_ekIscilik.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_ekIscilik));
        txt_karOran.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_karOran));
        txt_paletIscilik.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_paletIscilik));
        txt_PVC.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_PVC));
        txt_PVCFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_PVCFiyat));
        txt_zimparaIscilik.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_zimparaIscilik));
        txt_zimparaFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_zimparaFiyat));
        txt_tutkalFiyat.textProperty().addListener((observable, oldValue, newValue) -> handleCheckValues(txt_tutkalFiyat));
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
    private Label lbl_ekIscilikBirim;
    @FXML
    private TextField txt_ekIscilik;
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
    @FXML
    private Group hammaddeGroup;
}
