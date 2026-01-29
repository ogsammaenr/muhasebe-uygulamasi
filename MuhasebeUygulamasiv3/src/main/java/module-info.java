module me.ogsammaenr.muhasebeuygulamasiv3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens me.ogsammaenr.muhasebeuygulamasiv3 to javafx.fxml;
    opens me.ogsammaenr.muhasebeuygulamasiv3.controller to javafx.fxml;
    exports me.ogsammaenr.muhasebeuygulamasiv3;
}