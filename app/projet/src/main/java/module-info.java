module projet.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    opens projet.projet to javafx.fxml;
    exports test to junit;
    exports projet.projet;
}