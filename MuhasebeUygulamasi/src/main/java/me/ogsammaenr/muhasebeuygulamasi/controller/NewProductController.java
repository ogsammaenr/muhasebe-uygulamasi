package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.ogsammaenr.muhasebeuygulamasi.manager.UnitsManager;
import me.ogsammaenr.muhasebeuygulamasi.model.Unit;
import me.ogsammaenr.muhasebeuygulamasi.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NewProductController {
    private Map<String, Group> units;

    private UnitsManager unitsManager;

    private int unitCounter = 2;

    @FXML
    public void initialize() {
        unitsManager = new UnitsManager();

        this.units = new HashMap<>();
        units.put("Unit-1", textGroup);
        parentPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            double centerX = newVal.doubleValue() / 2;
            next.setLayoutX(centerX + 190);
        });
        for (var node : textGroup.getChildren()) {
            if (node instanceof TextField textField) {
                textField.textProperty().addListener((obs, oldVal, newVal) -> handlecheckValues(textField));
            }
        }
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
            if (node instanceof TextField textField) {
                textField.textProperty().addListener((observable, oldValue, newValue) -> handlecheckValues((TextField) node));
            }
            System.out.println(node.getId());
        }
        btn.setOnAction(e -> handleDeleteUnit(newGroup));


    }

    @FXML
    public void onDeleteUnitClick(ActionEvent event) {

    }

    @FXML
    public void onNextClick(ActionEvent event) {
        for (String id : units.keySet()) {
            for (var node : units.get(id).getChildren()) {
                if (node instanceof TextField textField && (textField.getText().isEmpty() || textField.getStyle().equals("-fx-text-fill: red;"))) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Geçersiz Giriş");
                    alert.setHeaderText(null);
                    alert.setContentText("Lütfen geçerli ve düzgün değerler girin.");
                    alert.showAndWait();

                    return;
                }
            }

            unitsManager.addUnit(getUnitByGroup(units.get(id)));

        }

        try {
            URL fxmlUrl = getClass().getResource("/me/ogsammaenr/muhasebeuygulamasi/new-product-view2.fxml");
            System.out.println("FXML URL: " + fxmlUrl);
            FXMLLoader loader = new FXMLLoader(fxmlUrl);


            Parent newProductView = loader.load();

            NewProductController2 controller = loader.getController();
            controller.setUnitsManager(unitsManager);

            Stage stage = new Stage();
            stage.setTitle("Product Muhasebe Uygulamasi");
            stage.setAlwaysOnTop(true);
            stage.setMinWidth(900);
            stage.setMinHeight(600);
            stage.setScene(new Scene(newProductView));
            stage.show();

            Stage stg = (Stage) next.getScene().getWindow();
            stg.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void handleDeleteUnit(@NotNull Group unit) {
        for (var node : unit.getChildren()) {
            if (node instanceof TextField textField) {

            }
        }

        units.remove(unit.getId());
        containerVBox.getChildren().remove(unit);
    }

    private void handlecheckValues(TextField node) {
        try {
            String str = node.getText();

            if (str.isEmpty()) {
                node.setStyle("-fx-text-fill: black;");
                return;
            }

            if (node.getId().equals("txt_patternTime") || node.getId().equals("txt_drillTime")) {
                double value = Double.parseDouble(str);
            } else {
                int value = Integer.parseInt(str);
            }
            if (str.endsWith("f") || str.endsWith("F")) {
                node.setStyle("-fx-text-fill: red;");
                return;
            }
            node.setStyle("-fx-text-fill: black;");

        } catch (Exception e) {
            node.setStyle("-fx-text-fill: red;");
        }
    }

    private Unit getUnitByGroup(Group group) {
        TextField tfMeasureX = (TextField) group.getChildren().get(0);
        TextField tfMeasureY = (TextField) group.getChildren().get(1);
        TextField tfMeasureZ = (TextField) group.getChildren().get(2);
        TextField tfCount = (TextField) group.getChildren().get(3);
        TextField tfPatternTime = (TextField) group.getChildren().get(4);
        TextField tfDrillTime = (TextField) group.getChildren().get(5);

        int measurex = Integer.parseInt(tfMeasureX.getText());
        int measurey = Integer.parseInt(tfMeasureY.getText());
        int measurez = Integer.parseInt(tfMeasureZ.getText());
        int count = Integer.parseInt(tfCount.getText());
        double patternTime = Double.parseDouble(tfPatternTime.getText());
        double drillTime = Double.parseDouble(tfDrillTime.getText());

        return new Unit(group.getId(), measurex, measurey, measurez, count, patternTime, drillTime);

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
}
