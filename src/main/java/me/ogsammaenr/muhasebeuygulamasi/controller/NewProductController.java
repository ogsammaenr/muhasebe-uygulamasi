package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.ogsammaenr.muhasebeuygulamasi.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NewProductController {
    private Map<String, Group> units;
    private int unitCounter = 1;


    @FXML
    public void initialize() {
        this.units = new HashMap<>();

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
}
