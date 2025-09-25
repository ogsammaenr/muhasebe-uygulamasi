package com.example.muhasebeuygulamasidevelop.controller;

import com.example.muhasebeuygulamasidevelop.controller.items.MdfInputRowController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MdfInputViewController implements Initializable {
    @FXML
    private BorderPane mainPane;
    @FXML
    private VBox mdfInputsContainer;
    @FXML
    private Button addMdfButton
            ,nextButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MdfInputRowController row = new MdfInputRowController();
        mdfInputsContainer.getChildren().add(mdfInputsContainer.getChildren().size(), row.getRoot());

        Image imgPlus = new Image(getClass().getResource("/icons/plus.png").toExternalForm());
        ImageView viewPlus = new ImageView(imgPlus);
        viewPlus.setFitHeight(15);
        viewPlus.setFitWidth(15);

        addMdfButton.setGraphic(viewPlus);

        Image imgRightArrow = new Image(getClass().getResource("/icons/rightArrow.png").toExternalForm());
        ImageView viewRightArrow = new ImageView(imgRightArrow);
        viewRightArrow.setFitHeight(15);
        viewRightArrow.setFitWidth(15);

        nextButton.setGraphic(viewRightArrow);
        nextButton.setContentDisplay(ContentDisplay.RIGHT);

        addMdfButton.setOnAction(e -> addMdfButtonClicked());
        nextButton.setOnAction(e -> nextButtonClicked());
    }

    private void addMdfButtonClicked() {
        MdfInputRowController row = new MdfInputRowController();
        mdfInputsContainer.getChildren().add(mdfInputsContainer.getChildren().size(), row.getRoot());
    }

    private void nextButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cost-calculation-view.fxml"));
            Parent parent = (Parent) loader.load();

            // BorderPane bölgelerini temizleyip yeni içeriği merkezde göster
            mainPane.setTop(null);
            mainPane.setBottom(null);
            mainPane.setLeft(null);
            mainPane.setRight(null);
            mainPane.setCenter(parent);

            Stage stg = (Stage) mainPane.getScene().getWindow();
            stg.setMinHeight(700);
            stg.setMinWidth(1200);
            // Gerekirse mevcut boyutu da büyüt
            if (stg.getHeight() < 700) stg.setHeight(700);
            if (stg.getWidth() < 1200) stg.setWidth(1200);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
