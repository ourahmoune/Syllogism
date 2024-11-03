module projet.projet {
    requires javafx.fxml;
    requires javafx.controls;
    requires junit;

    opens app to javafx.fxml;
    exports test to junit;
    exports app;
}