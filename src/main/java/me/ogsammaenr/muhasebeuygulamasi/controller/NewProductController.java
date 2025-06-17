package me.ogsammaenr.muhasebeuygulamasi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
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
}
