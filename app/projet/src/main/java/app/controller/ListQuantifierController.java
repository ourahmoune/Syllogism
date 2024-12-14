package app.controller;

import app.model.Quantificator;
import app.model.QuantificatorList;
import app.model.Quantity;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.util.Optional;

import static app.StartApplication.scene;

/**
 * The ListQuantifierController class manages the display and modification
 * of quantificators in the user interface. It allows users to add and remove
 * quantificators for both universal and existential categories.
 */
public class ListQuantifierController implements Resize {

    @FXML
    public Circle minusUniv, plusUniv, minusExist, plusExist;
    public Label plusUnivLabel, minusUnivLabel, plusExistLabel, minusExistLabel;
    public Label Universal, Existential;
    public ScrollPane SCPUniversal, SCPExistential;


    @FXML
    private VBox ListUniversal, ListExistential;

    /**
     * Initializes the controller by populating the quantificator lists
     * for universal and existential types.
     */
    @FXML
    public void initialize() {
        SCPUniversal.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Désactiver barre horizontale
        SCPExistential.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

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
        Quantificator quantificator = new Quantificator(quantity, word);
        QuantificatorList.getInstance().addQuantificator(quantificator);
    }


    public void handleAddQuantificatorClickUniversal(MouseEvent actionEvent) {
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
    public void handleRemoveQuantificatorClickUniversal(MouseEvent actionEvent) {
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
    public void handleAddQuantificatorClickExistential(MouseEvent actionEvent) {
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
    public void handleRemoveQuantificatorClickExistential(MouseEvent actionEvent) {
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
        label.setStyle("-fx-font-size: 21,5px");
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
            System.out.println("name : " + quantificator.getName() + " et quantity : " + quantificator.getQuantity());
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

    @Override
    public void resize() {
        resizeButtons();
        resizeFontSize();

        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            resizeButtons();
            resizeFontSize();
        });
        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            resizeButtons();
        });
    }

    private void resizeFontSize() {
        plusUnivLabel.setFont(Font.font(scene.widthProperty().getValue() / 30)); // 1.8% of window width
        minusUnivLabel.setFont(Font.font(scene.widthProperty().getValue() / 30));
        plusExistLabel.setFont(Font.font(scene.widthProperty().getValue() / 30));
        minusExistLabel.setFont(Font.font(scene.widthProperty().getValue() / 30));
        Universal.setFont(Font.font(scene.widthProperty().getValue() / 56));
        Existential.setFont(Font.font(scene.widthProperty().getValue() / 56));

        String style = "-fx-font-size :"+ scene.widthProperty().getValue() / 60;
        for (Node elt: ListUniversal.getChildren()){
            ((HBox)elt).getChildren().get(0).setStyle(style);
        }
        for (Node elt: ListExistential.getChildren()){
            ((HBox)elt).getChildren().get(0).setStyle(style);
        }

    }

    private void resizeButtons() {
        plusUniv.setRadius(scene.widthProperty().getValue() / 60); // 1.5% of window width
        minusUniv.setRadius(scene.widthProperty().getValue() / 60);
        plusExist.setRadius(scene.widthProperty().getValue() / 60);
        minusExist.setRadius(scene.widthProperty().getValue() / 60);

        ListUniversal.setMinWidth(scene.widthProperty().getValue() / 4);
        ListExistential.setMinWidth(scene.widthProperty().getValue() / 4);

        SCPUniversal.setMinWidth(scene.widthProperty().getValue() / 4);
        SCPExistential.setMinWidth(scene.widthProperty().getValue() / 4);
        SCPUniversal.setMinHeight(scene.heightProperty().getValue() / 1.5);
        SCPExistential.setMinHeight(scene.heightProperty().getValue() / 1.5);

    }
}
