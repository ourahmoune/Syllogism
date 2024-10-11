module projet.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    opens app to javafx.fxml;
    exports test to junit;
    exports app;
}