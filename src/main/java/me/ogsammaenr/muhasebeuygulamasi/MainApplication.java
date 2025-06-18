package me.ogsammaenr.muhasebeuygulamasi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

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

        contextMenu.getItems().addAll(editItem, deleteItem);

        Scene scene = new Scene(root);
        stage.setTitle("Procut Muhasebe Uygulamasi");
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
