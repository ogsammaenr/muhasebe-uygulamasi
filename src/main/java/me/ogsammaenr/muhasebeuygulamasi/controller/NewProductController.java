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
import me.ogsammaenr.muhasebeuygulamasi.model.Unit;
import me.ogsammaenr.muhasebeuygulamasi.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NewProductController {
    private Map<String, Group> units;
    private Map<String, Unit> unitMap;

    private int unitCounter = 2;

    @FXML

    public void initialize() {
        this.unitMap = new HashMap<>();

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
            System.out.println(node.getId());
        }
        btn.setOnAction(e -> handleDeleteUnit(newGroup));

        for (var node : newGroup.getChildren()) {
            if (node instanceof TextField textField) {
                textField.textProperty().addListener((observable, oldValue, newValue) -> handlecheckValues((TextField) node));
            }
        }

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

                    units.clear();
                    return;
                }
            }
            addUnitByGroup(units.get(id));

        }

        try {
            URL fxmlUrl = getClass().getResource("/me/ogsammaenr/muhasebeuygulamasi/new-product-view2.fxml");
            System.out.println("FXML URL: " + fxmlUrl);
            FXMLLoader loader = new FXMLLoader(fxmlUrl);

            Parent newProductView = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Product Muhasebe Uygulamasi");
            stage.setAlwaysOnTop(true);
            stage.setMinWidth(900);
            stage.setMinHeight(600);
            stage.setScene(new Scene(newProductView));
            stage.show();

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
            if (!node.getId().equals("txt_count")) {
                double value = Double.parseDouble(str);
            } else {
                int value = Integer.parseInt(str);
            }
            node.setStyle("-fx-text-fill: black;");
            if (str.endsWith("f") || str.endsWith("F"))
                node.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            node.setStyle("-fx-text-fill: red;");
        }
    }

    private void addUnitByGroup(Group group) {
        String unitId = group.getId();
        double measurex = Double.parseDouble(group.getChildren().get(0).getAccessibleText());
        double measurey = Double.parseDouble(group.getChildren().get(1).getAccessibleText());
        double measurez = Double.parseDouble(group.getChildren().get(2).getAccessibleText());
        int count = Integer.parseInt(group.getChildren().get(3).getAccessibleText());
        double patternTime = Double.parseDouble(group.getChildren().get(4).getAccessibleText());
        double drillTime = Double.parseDouble(group.getChildren().get(5).getAccessibleText());

        Unit unit = new Unit(unitId, measurex, measurey, measurez, count, patternTime, drillTime);

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
