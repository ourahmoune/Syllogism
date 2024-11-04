module projet.projet {
    requires javafx.fxml;
    requires javafx.controls;
    requires junit;
    requires org.apache.poi.ooxml;
    requires java.desktop;

    opens app to javafx.fxml;
    opens app.controller to javafx.fxml;
    exports test to junit;
    exports app;
}