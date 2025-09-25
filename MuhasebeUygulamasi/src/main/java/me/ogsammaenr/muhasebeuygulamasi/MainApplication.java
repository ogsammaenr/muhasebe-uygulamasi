package me.ogsammaenr.muhasebeuygulamasi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.ogsammaenr.muhasebeuygulamasi.storage.DatabaseManager;

import java.io.IOException;

public class MainApplication extends Application {
    public static MainApplication instance;

    private ContextMenu contextMenu;
    private MenuItem editItem;
    private MenuItem deleteItem;
    private Parent newProductView;

    private Stage stage;

    private double xOffset = 0, yOffset = 0;
    private final int RESIZE_MARGIN = 8;

    @Override
    public void init() throws Exception {
        DatabaseManager.connect();
    }

    @Override
    public void stop() throws Exception {
        DatabaseManager.disconnect();
    }

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

        Scene scene = new Scene(root, 900, 600);

        stage.getIcons().add(icon);
        stage.setTitle("Prucut Muhasebe Uygulaması");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
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
