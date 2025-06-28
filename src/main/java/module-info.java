module me.ogsammaenr.muhasebeuygulamasi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires annotations;
    requires FX.BorderlessScene;
    requires java.sql;

    opens me.ogsammaenr.muhasebeuygulamasi to javafx.fxml;
    exports me.ogsammaenr.muhasebeuygulamasi;
    exports me.ogsammaenr.muhasebeuygulamasi.controller;
    opens me.ogsammaenr.muhasebeuygulamasi.controller to javafx.fxml;
}