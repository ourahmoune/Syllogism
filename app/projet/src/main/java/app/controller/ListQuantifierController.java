package app.controller;

import app.model.Quantificator;
import app.model.QuantificatorList;
import app.model.Quantity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

/**
 * The ListQuantifierController class manages the display and modification
 * of quantificators in the user interface. It allows users to add and remove
 * quantificators for both universal and existential categories.
 */
public class ListQuantifierController {

    @FXML
    private VBox ListUniversal, ListExistential;

    /**
     * Initializes the controller by populating the quantificator lists
     * for universal and existential types.
     */
    @FXML
    public void initialize() {
        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.setPrefHeight(30.0);
            hbox.setPrefWidth(110.0);

            // Create a label for the quantificator
            Label label = new Label(quantificator.getName());
            label.setAlignment(Pos.TOP_CENTER);

            // Add the label to the HBox
            hbox.getChildren().add(label);

            // Add to the appropriate list
            if (quantificator.getQuantity() == Quantity.Universal) {
                ListUniversal.getChildren().add(hbox);
            } else if (quantificator.getQuantity() == Quantity.Exisential) {
                ListExistential.getChildren().add(hbox);
            }
        }
    }

    public void addQuantificator(String word, Quantity quantity) {
        Quantificator quantificator = new Quantificator(Quantity.Universal, word);
        QuantificatorList.getInstance().addQuantificator(quantificator);
    }


    public void handleAddQuantificatorClickUniversal(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        if (SettingController.language.getObject("Language").equals("English  ")) {
            dialog.setTitle("Add a quantificator");
            dialog.setHeaderText("Enter a word to add to the list:");
        }
        else {
            dialog.setTitle("Ajouter un quantificateur");
            dialog.setHeaderText("Entrer un mot à ajouter à la liste:");
        }
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(word -> {

            // Création d'un nouveau HBox avec les paramètres nécessaires
            HBox hbox = createHBox(word);

            addQuantificator(word, Quantity.Universal);

            // Ajout du HBox dans le VBox principal (ListQuantifier)
            ListUniversal.getChildren().add(hbox);
        });
    }

    /**
     * Handles the event to remove a universal quantificator.
     */
    public void handleRemoveQuantificatorClickUniversal(ActionEvent actionEvent) {
        Alert alert = createDeleteConfirmationDialog("Universal");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String selectedQuantificatorName = getSelectedQuantificatorName(alert);
            if (selectedQuantificatorName != null) {
                removeQuantificator(selectedQuantificatorName, Quantity.Universal, ListUniversal);
            }
        }
    }

    /**
     * Handles the event to add a new existential quantificator.
     */
    public void handleAddQuantificatorClickExistential(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        if (SettingController.language.getObject("Language").equals("English  ")) {
            dialog.setTitle("Add a quantificator");
            dialog.setHeaderText("Enter a word to add to the list:");
        }
        else {
            dialog.setTitle("Ajouter un quantificateur");
            dialog.setHeaderText("Entrer un mot à ajouter à la liste:");
        }
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(word -> {
            // Création d'un nouveau HBox avec les paramètres nécessaires
            HBox hbox = createHBox(word);

            addQuantificator(word, Quantity.Exisential);

            // Ajout du HBox dans le VBox principal (ListQuantifier)
            ListExistential.getChildren().add(hbox);
        });
    }

    /**
     * Handles the event to remove an existential quantificator.
     */
    public void handleRemoveQuantificatorClickExistential(ActionEvent actionEvent) {
        Alert alert = createDeleteConfirmationDialog("Existential");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String selectedQuantificatorName = getSelectedQuantificatorName(alert);
            if (selectedQuantificatorName != null) {
                removeQuantificator(selectedQuantificatorName, Quantity.Exisential, ListExistential);
            }
        }
    }

    /**
     * Creates an HBox containing a label for the given quantificator name.
     *
     * @param word The name of the quantificator.
     * @return The created HBox.
     */
    private HBox createHBox(String word) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPrefHeight(30.0);
        hbox.setPrefWidth(110.0);

        Label label = new Label(word);
        label.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().add(label);
        return hbox;
    }

    /**
     * Prints the list of all quantificators to the console.
     */
    private void printQuantificators() {
        System.out.println("List of all quantificators:");
        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            System.out.println(quantificator.getName());
        }
    }

    /**
     * Creates a confirmation dialog for deleting a quantificator.
     *
     * @param type The type of quantificator ("Universal" or "Existential").
     * @return The created Alert dialog.
     */
    private Alert createDeleteConfirmationDialog(String type) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete a Quantificator");
        alert.setHeaderText("Select a quantificator to delete:");
        ComboBox<String> comboBox = new ComboBox<>();

        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            if ((type.equals("Universal") && quantificator.getQuantity() == Quantity.Universal) ||
                    (type.equals("Existential") && quantificator.getQuantity() == Quantity.Exisential)) {
                comboBox.getItems().add(quantificator.getName());
            }
        }

        alert.getDialogPane().setContent(comboBox);
        return alert;
    }

    /**
     * Retrieves the selected quantificator name from the ComboBox in the dialog.
     *
     * @param alert The alert dialog containing the ComboBox.
     * @return The selected quantificator name or null if none selected.
     */
    private String getSelectedQuantificatorName(Alert alert) {
        ComboBox<String> comboBox = (ComboBox<String>) alert.getDialogPane().getContent();
        return comboBox.getSelectionModel().getSelectedItem();
    }

    /**
     * Removes the specified quantificator from the list and UI.
     *
     * @param name  The name of the quantificator to remove.
     * @param type  The quantity type of the quantificator.
     * @param list  The VBox containing the UI elements.
     */
    private void removeQuantificator(String name, Quantity type, VBox list) {
        list.getChildren().removeIf(node ->
                node instanceof HBox && ((Label) ((HBox) node).getChildren().get(0)).getText().equals(name)
        );
        QuantificatorList.getInstance().removeQuantificator(name, type);
    }
}
