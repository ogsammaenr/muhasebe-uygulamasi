package me.ogsammaenr.muhasebeuygulamasi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {
    public static MainApplication instance;

    private ContextMenu contextMenu;
    private MenuItem editItem;
    private MenuItem deleteItem;
    private Parent newProductView;

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        this.stage = stage;

        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Parent root = rootLoader.load();
        this.contextMenu = new ContextMenu();

        this.editItem = new MenuItem("Edit");
        this.deleteItem = new MenuItem("Delete");
        Image icon = new Image(getClass().getResource("icons/logo.png").toExternalForm());

        contextMenu.getItems().addAll(editItem, deleteItem);

        Scene scene = new Scene(root);
        stage.setTitle("Procut Muhasebe Uygulamasi");
        stage.getIcons().add(icon);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }


    public Stage getStage() {
        return stage;
    }

    public static MainApplication getInstance() {
        return instance;
    }
}
