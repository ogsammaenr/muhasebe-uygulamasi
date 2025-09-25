package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import me.ogsammaenr.muhasebeuygulamasi.manager.UnitsManager;
import me.ogsammaenr.muhasebeuygulamasi.model.Unit;
import me.ogsammaenr.muhasebeuygulamasi.util.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleConsumer;

public class NewProductController2 {

    private Map<String, Unit> unitMap;
    private Map<Group, Double> hammaddeGroups;

    private UnitsManager unitsManager;
    private double totalTime;
    private double totalArea;

    private double PvcFiyatdolar;
    private double PvcFiyat;
    private double dolar;

    private double kesimFiyat, CncFiyat, zimparaIscilikFiyat, zimparaFiyat,
            paletlemeIscilikFiyat, tutkalFiyat, PvcMasraf, basimIscilikFiyat,
            ambalajFiyat, nakliyeFiyat, ekIscilikFiyat, kar;

    @FXML
    public void initialize() {
        setupListeners();

    }

    public void setUnitsManager(UnitsManager unitsManager) {
        this.unitsManager = unitsManager;
        totalTime = unitsManager.getTotalTime();
        totalArea = unitsManager.getTotalArea();

        hammaddeGroups = new HashMap<>();

        addHammaddeGroups();
    }

    @FXML
    public void onSaveClick(ActionEvent event) {

    }

    private void handleGenericCost(TextField input, Label output, double multiplier, DoubleConsumer setter) {
        if (input.getStyle().equals("-fx-text-fill: red;") || input.getText().isEmpty()) {
            setter.accept(0);
            output.setText("");
            return;
        }
        double value = Double.parseDouble(input.getText()) * multiplier;
        setter.accept(Utils.round(value, 2));
        output.setText(String.valueOf(Utils.round(value, 2)));
    }

    @FXML
    public void onKesimChange() {
        handleGenericCost(txt_kesimFiyat, lbl_kesimFiyatBirim, totalArea / 5.88, v -> kesimFiyat = v);
        calculateKar();
        calculateFiyatlar();
    }

    @FXML
    public void onCncChange() {
        handleGenericCost(txt_CNCFiyat, lbl_CNCFiyatBirim, totalTime, v -> CncFiyat = v);
        calculateKar();
        calculateFiyatlar();
    }

    @FXML
    public void onZimparaIscilikChange() {
        handleGenericCost(txt_zimparaIscilik, lbl_zimparaIscilikBirim, totalArea, v -> zimparaIscilikFiyat = v);
        calculateKar();
        calculateFiyatlar();
    }


    @FXML
    public void onZimparaChange() {
        handleGenericCost(txt_zimparaFiyat, lbl_zimparaFiyatBirim, totalArea, v -> zimparaFiyat = v);
        calculateKar();
        calculateFiyatlar();
    }

    @FXML
    public void onPaletIscilikChange() {
        handleGenericCost(txt_paletIscilik, lbl_paletIscilikBirim, totalArea, v -> paletlemeIscilikFiyat = v);
        calculateKar();
        calculateFiyatlar();
    }

    @FXML
    public void onTutkalFiyatChange() {
        handleGenericCost(txt_tutkalFiyat, lbl_tutkalFiyatBirim, totalArea, v -> tutkalFiyat = v);
        calculateKar();
        calculateFiyatlar();
    }

    @FXML
    public void onBasimIscilikChange() {
        handleGenericCost(txt_basimIscilik, lbl_basimIscilikBirim, totalArea, v -> basimIscilikFiyat = v);
        calculateKar();
        calculateFiyatlar();
    }

    @FXML
    public void onAmbalajChange() {
        handleGenericCost(txt_ambalaj, lbl_ambalajBirim, totalArea, v -> ambalajFiyat = v);
        calculateKar();
        calculateFiyatlar();
    }

    @FXML
    public void onNakliyeChange() {
        handleGenericCost(txt_nakliye, lbl_nakliyeBirim, totalArea, v -> nakliyeFiyat = v);
        calculateKar();
        calculateFiyatlar();
    }

    @FXML
    public void onEkIscilikChange() {
        handleGenericCost(txt_ekIscilik, lbl_ekIscilikBirim, totalArea, v -> ekIscilikFiyat = v);
        calculateKar();
        calculateFiyatlar();
    }

    @FXML
    public void onPvcFiyatChange() {
        if (txt_PVCFiyat.getStyle().equals("-fx-text-fill: red;") || txt_PVCFiyat.getText().isEmpty()) {
            lbl_PVCBirim.setText("");
            lbl_PVC.setText("");
            PvcFiyatdolar = 0;
            return;
        }
        PvcFiyatdolar = Double.parseDouble(txt_PVCFiyat.getText());
        if (!txt_dolar.getStyle().equals("-fx-text-fill: red;") && !txt_dolar.getText().isEmpty()) {
            calculatePvc();
            calculateKar();
            calculateFiyatlar();
        }
    }

    @FXML
    public void onDolarChange() {
        if (txt_dolar.getStyle().equals("-fx-text-fill: red;") || txt_dolar.getText().isEmpty()) {
            lbl_PVCBirim.setText("");
            lbl_PVC.setText("");
            dolar = 0;
            return;
        }
        dolar = Double.parseDouble(txt_dolar.getText());
        if (!txt_PVCFiyat.getStyle().equals("-fx-text-fill: red;") && !txt_PVCFiyat.getText().isEmpty()) {
            calculatePvc();
            calculateKar();
            calculateFiyatlar();
        }
    }

    @FXML
    public void onKarChange() {
        calculateKar();
        calculateFiyatlar();
    }

    private void calculateKar() {
        if (txt_karOran.getStyle().equals("-fx-text-fill: red;") || txt_karOran.getText().isEmpty()) {
            lbl_karBirim.setText("");
            kar = 0;
            return;
        }
        double oran = Double.parseDouble(txt_karOran.getText());
        double toplam = kesimFiyat + CncFiyat + zimparaIscilikFiyat + zimparaFiyat +
                paletlemeIscilikFiyat + tutkalFiyat + PvcMasraf + basimIscilikFiyat +
                ambalajFiyat + nakliyeFiyat + ekIscilikFiyat;
        kar = Utils.round(toplam * oran, 2);
        lbl_karBirim.setText(String.valueOf(kar));
    }

    private void calculatePvc() {
        PvcFiyat = Utils.round(dolar * PvcFiyatdolar * 2 * 1.03, 2);
        lbl_PVC.setText(String.valueOf(PvcFiyat));
        PvcMasraf = Utils.round(PvcFiyat * totalArea, 2);
        lbl_PVCBirim.setText(String.valueOf(PvcMasraf));
    }

    private void calculateFiyatlar() {
        double temp = 0;
        for (double value : hammaddeGroups.values()) {
            temp += value;
        }
        double toplam = kesimFiyat + CncFiyat + zimparaIscilikFiyat + zimparaFiyat +
                paletlemeIscilikFiyat + tutkalFiyat + PvcMasraf + basimIscilikFiyat +
                ambalajFiyat + nakliyeFiyat + ekIscilikFiyat + kar + temp;
        lbl_takimFiyat.setText(String.valueOf(Utils.round(toplam, 2)));
        lbl_birimFiyat.setText(String.valueOf(Utils.round(toplam / totalArea, 2)));


        double hammaddeToplam = temp + zimparaFiyat + tutkalFiyat + PvcMasraf + ambalajFiyat + nakliyeFiyat;
        lbl_hammaddeFiyat.setText(String.valueOf(Utils.round(hammaddeToplam, 2)));
        lbl_hammaddeBirimFiyat.setText(String.valueOf(Utils.round(hammaddeToplam / totalArea, 2)));

        double iscilikFiyat = kesimFiyat + CncFiyat + zimparaIscilikFiyat + paletlemeIscilikFiyat + basimIscilikFiyat;
        lbl_iscilikFiyat.setText(String.valueOf(Utils.round(iscilikFiyat, 2)));
        lbl_iscilikBirimFiyat.setText(String.valueOf(Utils.round(iscilikFiyat / totalArea, 2)));

        lbl_kar.setText(String.valueOf(kar));
        lbl_birimKar.setText(String.valueOf(Utils.round(kar / totalArea, 2)));

    }


    private void addHammaddeGroups() {
        List<Integer> allThickness = unitsManager.getAllThickness();

        for (int thickness : allThickness) {
            Group group = Utils.copyGroup(hammaddeBilgiGroup);
            Group hammaddeGrp = Utils.copyGroup(hammaddeGroup);
            ((Label) group.getChildren().get(0)).setText("MDF " + thickness);
            ((Label) group.getChildren().get(1)).setText(String.valueOf(unitsManager.thicnessToArea(thickness)));

            hammaddeGrp.setId(String.valueOf(thickness));

            Label label = (Label) hammaddeGrp.getChildren().get(0);
            TextField fiyat = (TextField) hammaddeGrp.getChildren().get(1);

            label.setText("MDF " + thickness);
            fiyat.textProperty().addListener((obs, oldVal, newVal) -> {
                handleCheckValues(fiyat);
                handleHammaddeGroups(fiyat);
            });
            hammaddeGroups.put(hammaddeGrp, (double) 0);

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

        Label resultLabel = (Label) group.getChildren().getLast();
        if (node.getStyle().equals("-fx-text-fill: black;") && !node.getText().isEmpty()) {
            double fiyat = Double.parseDouble(node.getText());
            double kalinlik = Double.parseDouble(group.getId());
            double suremiktar = unitsManager.thicnessToArea(kalinlik) * (1.12 / 5.88);
            double birimFiyat = Utils.round(fiyat * suremiktar, 2);
            resultLabel.setText(String.valueOf(birimFiyat));
            hammaddeGroups.put(group, birimFiyat);
        } else {
            resultLabel.setText("");
            hammaddeGroups.put(group, (double) 0);
        }

    }


    private void setupListeners() {
        TextField[] fields = {txt_ambalaj, txt_dolar, txt_CNCFiyat, txt_kesimFiyat,
                txt_nakliye, txt_basimIscilik, txt_ekIscilik, txt_karOran, txt_paletIscilik,
                txt_PVCFiyat, txt_zimparaIscilik, txt_zimparaFiyat, txt_tutkalFiyat};

        for (TextField field : fields) {
            field.textProperty().addListener((obs, oldVal, newVal) -> handleCheckValues(field));
        }
    }

    @FXML
    private TextField txt_PVCFiyat, txt_PVCRenk, txt_PVCFirma, txt_dolar, txt_kesimFiyat, txt_CNCFiyat,
            txt_zimparaIscilik, txt_zimparaFiyat, txt_paletIscilik, txt_tutkalFiyat,
            txt_basimIscilik, txt_ambalaj, txt_nakliye, txt_ekIscilik, txt_karOran;

    @FXML
    private Label lbl_PVCBirim, lbl_PVC, lbl_kesimFiyatBirim, lbl_CNCFiyatBirim,
            lbl_zimparaIscilikBirim, lbl_zimparaFiyatBirim, lbl_paletIscilikBirim,
            lbl_tutkalFiyatBirim, lbl_basimIscilikBirim, lbl_ambalajBirim, lbl_nakliyeBirim,
            lbl_ekIscilikBirim, lbl_karBirim;

    @FXML
    private Label lbl_takimFiyat, lbl_birimFiyat, lbl_hammaddeFiyat,
            lbl_hammaddeBirimFiyat, lbl_iscilikFiyat, lbl_iscilikBirimFiyat,
            lbl_kar, lbl_birimKar;

    @FXML
    private VBox hammaddeVBox, mainVBox;
    @FXML
    private Group hammaddeBilgiGroup, hammaddeGroup;
}
