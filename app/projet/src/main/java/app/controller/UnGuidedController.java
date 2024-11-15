package app.controller;

import app.model.Quantificator;
import app.model.QuantificatorList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UnGuidedController {

    @FXML
    VBox vboxPremice;

    @FXML
    ComboBox<String> Q1, Q2, Q3;

    @FXML
    public void initialize() {

        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            Q1.getItems().add(quantificator.getName());
            Q2.getItems().add(quantificator.getName());
            Q3.getItems().add(quantificator.getName());
        }


    }

    public void addPremice(ActionEvent actionEvent) {
        // Création de l'HBox
        HBox hbox = new HBox();
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        hbox.setSpacing(10);

        // Ajout du ComboBox
        ComboBox<String> comboBox = new ComboBox<>();
        setComboBoxQuanti(comboBox);
        comboBox.setPrefWidth(150);
        comboBox.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(comboBox, javafx.scene.layout.Priority.ALWAYS);

        // Ajout des TextField avec les styles
        TextField textField1 = new TextField();
        textField1.setStyle("-fx-prompt-text-fill: #808080;");
        HBox.setHgrow(textField1, javafx.scene.layout.Priority.ALWAYS);

        TextField textField2 = new TextField();
        textField2.setStyle("-fx-prompt-text-fill: #808080;");
        HBox.setHgrow(textField2, javafx.scene.layout.Priority.ALWAYS);

        TextField textField3 = new TextField();
        textField3.setStyle("-fx-prompt-text-fill: #808080;");
        HBox.setHgrow(textField3, javafx.scene.layout.Priority.ALWAYS);

        // Bouton "+"
        Button addButton = new Button("+");
        addButton.setStyle("-fx-background-radius: 15; -fx-min-width: 30; -fx-min-height: 30; " +
                "-fx-max-height: 30; -fx-max-width: 30; -fx-font-size: 20; -fx-text-fill: #32b71b; " +
                "-fx-background-color: lightgrey; -fx-alignment: center; -fx-padding: 0;");
        HBox.setHgrow(addButton, javafx.scene.layout.Priority.ALWAYS);

        // Bouton "-"
        Button removeButton = new Button("—");
        removeButton.setStyle("-fx-background-radius: 15; -fx-min-width: 30; -fx-min-height: 30; " +
                "-fx-max-height: 30; -fx-max-width: 30; -fx-font-size: 15; -fx-text-fill: #32b71b; " +
                "-fx-background-color: lightgrey; -fx-alignment: center; -fx-padding: 0; -fx-font-weight: bold;");
        HBox.setHgrow(removeButton, javafx.scene.layout.Priority.ALWAYS);

        // Ajout de tous les éléments à l'HBox
        hbox.getChildren().addAll(comboBox, textField1, textField2, textField3, addButton, removeButton);

        // Ajout de l'HBox au conteneur VBox dans le ScrollPane
        vboxPremice.getChildren().add(hbox);
    }

    private void setComboBoxQuanti(ComboBox<String> comboBox) {
        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            comboBox.getItems().add(quantificator.getName());
        }
    }
}
