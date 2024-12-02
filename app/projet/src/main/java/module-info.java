module projet.projet {
    requires javafx.fxml;
    requires javafx.controls;
    requires junit;
    requires org.apache.poi.ooxml;
    requires java.desktop;
    requires java.sql;

    opens app to javafx.fxml;
    opens app.controller to javafx.fxml;
    exports test to junit;
    exports app;
    exports app.model.polysyllogismes;
    exports app.model;
    exports app.controller;
}