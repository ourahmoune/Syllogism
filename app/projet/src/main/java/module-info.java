module projet.projet {
    requires javafx.controls;
    requires javafx.fxml;


    opens projet.projet to javafx.fxml;
    exports projet.projet;
}