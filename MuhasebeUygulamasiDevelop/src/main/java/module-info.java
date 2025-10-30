module com.example.muhasebeuygulamasidevelop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires javafx.graphics;

    opens com.example.muhasebeuygulamasidevelop.controller to javafx.fxml;
    exports com.example.muhasebeuygulamasidevelop;
    exports com.example.muhasebeuygulamasidevelop.controller;
    exports com.example.muhasebeuygulamasidevelop.controller.items;
    opens com.example.muhasebeuygulamasidevelop.controller.items to javafx.fxml;
}