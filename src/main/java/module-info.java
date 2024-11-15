module com.example.vertebrados {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;

    opens com.example.vertebrados to javafx.fxml;
    exports com.example.vertebrados;
}