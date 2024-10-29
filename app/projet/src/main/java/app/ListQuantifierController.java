package app;

import app.Model.Quantificator;
import app.Model.QuantificatorList;
import app.Model.Quantity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class ListQuantifierController {

    @FXML
    private VBox ListUniversal, ListExistential;

    @FXML
    public void initialize() {
        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            if (quantificator.getQuantity() == Quantity.Universal) {
                HBox hbox = new HBox();
                hbox.setAlignment(Pos.CENTER); // Centre le contenu dans le HBox
                hbox.setPrefHeight(30.0);
                hbox.setPrefWidth(110.0);

                // Création d'un nouveau Label avec le texte saisi par l'utilisateur
                Label label = new Label(quantificator.getName());
                label.setAlignment(Pos.TOP_CENTER); // Centre le texte du Label

                // Ajout du Label dans le HBox
                hbox.getChildren().add(label);

                ListUniversal.getChildren().add(hbox);
            }

            else if (quantificator.getQuantity() == Quantity.Exisential) {
                HBox hbox = new HBox();
                hbox.setAlignment(Pos.CENTER); // Centre le contenu dans le HBox
                hbox.setPrefHeight(30.0);
                hbox.setPrefWidth(110.0);

                // Création d'un nouveau Label avec le texte saisi par l'utilisateur
                Label label = new Label(quantificator.getName());
                label.setAlignment(Pos.TOP_CENTER); // Centre le texte du Label

                // Ajout du Label dans le HBox
                hbox.getChildren().add(label);

                ListExistential.getChildren().add(hbox);
            }
        }
    }


    public void handleAddQuantificatorClickUniversal(ActionEvent actionEvent) {

        // Crée une boîte de dialogue pour saisir le texte
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add a quantificator");
        dialog.setHeaderText("Enter a word to add to the list :");

        // Affiche la boîte de dialogue et attend l'entrée de l'utilisateur
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(word -> {
            // Création d'un nouveau HBox avec les paramètres nécessaires
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER); // Centre le contenu dans le HBox
            hbox.setPrefHeight(30.0);
            hbox.setPrefWidth(110.0);

            // Création d'un nouveau Label avec le texte saisi par l'utilisateur
            Label label = new Label(word);
            label.setAlignment(Pos.TOP_CENTER); // Centre le texte du Label

            // Ajout du Label dans le HBox
            hbox.getChildren().add(label);

            //test de la size
            Quantificator quantificator = new Quantificator(Quantity.Universal, word);
            QuantificatorList.getInstance().addQuantificator(quantificator);

            System.out.println("List of all quantificators:");
            for (Quantificator quantificatorr : QuantificatorList.getInstance().getQuantificators()) {
                System.out.println(quantificatorr.getName());
            }

            // Ajout du HBox dans le VBox principal (ListQuantifier)
            ListUniversal.getChildren().add(hbox);
        });

    }

    public void handleRemoveQuantificatorClickUniversal(ActionEvent actionEvent) {
        // Crée la boîte de dialogue de suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete a Quantificator");
        alert.setHeaderText("Select a quantificator to delete:");

        ComboBox<String> comboBox = new ComboBox<>();
        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            if (quantificator.getQuantity() == Quantity.Universal) {
                comboBox.getItems().add(quantificator.getName());
            }
        }

        // Ajoute la ComboBox dans la boîte de dialogue
        alert.getDialogPane().setContent(comboBox);

        // Affiche la boîte de dialogue et attend la confirmation de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String selectedQuantificatorName = comboBox.getSelectionModel().getSelectedItem();
            if (selectedQuantificatorName != null) {
                // Supprime le label correspondant de l'interface utilisateur
                ListUniversal.getChildren().removeIf(node ->
                        node instanceof HBox && ((Label) ((HBox) node).getChildren().get(0)).getText().equals(selectedQuantificatorName)
                );

                // Recherchez le Quantificator correspondant par son nom
                QuantificatorList.getInstance().removeQuantificator(selectedQuantificatorName, Quantity.Universal);

            }
        }
    }

    public void handleAddQuantificatorClickExistential(ActionEvent actionEvent) {

        // Crée une boîte de dialogue pour saisir le texte
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add a quantificator");
        dialog.setHeaderText("Enter a word to add to the list :");

        // Affiche la boîte de dialogue et attend l'entrée de l'utilisateur
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(word -> {
            // Création d'un nouveau HBox avec les paramètres nécessaires
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER); // Centre le contenu dans le HBox
            hbox.setPrefHeight(30.0);
            hbox.setPrefWidth(110.0);

            // Création d'un nouveau Label avec le texte saisi par l'utilisateur
            Label label = new Label(word);
            label.setAlignment(Pos.TOP_CENTER); // Centre le texte du Label

            // Ajout du Label dans le HBox
            hbox.getChildren().add(label);

            //test de la size
            Quantificator quantificator = new Quantificator(Quantity.Exisential, word);
            QuantificatorList.getInstance().addQuantificator(quantificator);

            // Ajout du HBox dans le VBox principal (ListQuantifier)
            ListExistential.getChildren().add(hbox);
        });

    }

    public void handleRemoveQuantificatorClickExistential(ActionEvent actionEvent) {
        // Crée la boîte de dialogue de suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete a Quantificator");
        alert.setHeaderText("Select a quantificator to delete:");

        ComboBox<String> comboBox = new ComboBox<>();
        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            if (quantificator.getQuantity() == Quantity.Exisential) {
                comboBox.getItems().add(quantificator.getName());
            }
        }

        // Ajoute la ComboBox dans la boîte de dialogue
        alert.getDialogPane().setContent(comboBox);

        // Affiche la boîte de dialogue et attend la confirmation de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String selectedQuantificatorName = comboBox.getSelectionModel().getSelectedItem();
            if (selectedQuantificatorName != null) {
                // Supprime le label correspondant de l'interface utilisateur
                ListExistential.getChildren().removeIf(node ->
                        node instanceof HBox && ((Label) ((HBox) node).getChildren().get(0)).getText().equals(selectedQuantificatorName)
                );

                // Recherchez le Quantificator correspondant par son nom
                QuantificatorList.getInstance().removeQuantificator(selectedQuantificatorName, Quantity.Exisential);
            }
        }
    }
}
