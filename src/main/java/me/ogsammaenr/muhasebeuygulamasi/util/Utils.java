package me.ogsammaenr.muhasebeuygulamasi.util;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Utils {
    public static Group copyGroup(Group group) {
        Group clone = new Group();
        for (var node : group.getChildren()) {
            if (node instanceof TextField original) {
                TextField copy = new TextField();

                copy.setPromptText(original.getPromptText());
                copy.setLayoutX(original.getLayoutX());
                copy.setLayoutY(original.getLayoutY());
                copy.setAlignment(original.getAlignment());

                copy.setPrefWidth(original.getPrefWidth());
                copy.setPrefHeight(original.getPrefHeight());
                copy.setMinWidth(original.getMinWidth());
                copy.setMinHeight(original.getMinHeight());
                copy.setMaxWidth(original.getMaxWidth());
                copy.setMaxHeight(original.getMaxHeight());

                copy.getStyleClass().clear();
                copy.getStyleClass().addAll(original.getStyleClass());

                copy.setId(original.getId());
                clone.getChildren().add(copy);
            } else if (node instanceof Button original) {
                Button copy = new Button();

                copy.setLayoutX(original.getLayoutX());
                copy.setLayoutY(original.getLayoutY());

                copy.setPrefWidth(original.getPrefWidth());
                copy.setPrefHeight(original.getPrefHeight());
                copy.setMinWidth(original.getMinWidth());
                copy.setMinHeight(original.getMinHeight());
                copy.setMaxWidth(original.getMaxWidth());
                copy.setMaxHeight(original.getMaxHeight());

                copy.getStyleClass().clear();
                copy.getStyleClass().addAll(original.getStyleClass());

                if (original.getGraphic() instanceof ImageView originalImageView) {
                    ImageView copyImageView = new ImageView(originalImageView.getImage());

                    copyImageView.setFitWidth(originalImageView.getFitWidth());
                    copyImageView.setFitHeight(originalImageView.getFitHeight());
                    copyImageView.setX(originalImageView.getX());
                    copyImageView.setY(originalImageView.getY());
                    copyImageView.setRotate(originalImageView.getRotate());
                    copyImageView.setScaleX(originalImageView.getScaleX());
                    copyImageView.setScaleY(originalImageView.getScaleY());
                    copyImageView.setStyle(originalImageView.getStyle());
                    copyImageView.setCache(originalImageView.isCache());

                    copy.setGraphic(copyImageView);

                }

                copy.setOnAction(original.getOnAction());

                copy.setId(original.getId());
                clone.getChildren().add(copy);
            }

        }
        return clone;
    }


}
