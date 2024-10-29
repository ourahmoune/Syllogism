module projet.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires org.apache.poi.ooxml;
    requires java.desktop;

    opens app to javafx.fxml;
    exports test to junit;
    exports app;
}