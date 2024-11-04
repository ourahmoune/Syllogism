package app.controller;

import app.StartApplication;
import app.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * The GuidedController class manages the interactions between the user interface
 * and the underlying logic of the syllogism application. It handles user input,
 * validates syllogism data, and updates the UI accordingly.
 */
public class GuidedController {
    @FXML
    private ImageView image_figure;

    @FXML
    private ComboBox<String> Q1, Q2, Q3;
    @FXML
    private ComboBox<String> choix_figure;

    @FXML
    private TextField P1_1, P1_2, P2_1, P2_2, P3_1, P3_2;

    @FXML
    private Button oneplus, oneminus, twoplus, twominus, threeplus, threeminus;

    // Qualities of the propositions
    private String ql1, ql2, ql3;

    /**
     * Initializes the controller by setting up the UI elements.
     */
    public void initialize() {
        choix_figure.getItems().addAll("UN", "DEUX", "TROIS", "QUATRE");

        // Listener for the figure selection
        choix_figure.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                // Update the image based on the selected figure
                String imagePath = getImagePathForOption(newValue);
                image_figure.setImage(new Image(String.valueOf(StartApplication.class.getResource(imagePath))));
            }
        });

        // Populate quantificator options
        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            Q1.getItems().add(quantificator.getName());
            Q2.getItems().add(quantificator.getName());
            Q3.getItems().add(quantificator.getName());
        }

        // Disable input fields initially
        disableInputs(true);
    }

    /**
     * Returns the image path corresponding to the selected figure.
     *
     * @param option The selected figure option.
     * @return The path of the corresponding image.
     */
    private String getImagePathForOption(String option) {
        return switch (option) {
            case "DEUX" -> "/app/image/Figure2_syllogism.png";
            case "TROIS" -> "/app/image/Figure3_syllogism.png";
            case "QUATRE" -> "/app/image/Figure4_syllogism.png";
            default -> "/app/image/Figure1_syllogism.png";
        };
    }

    /**
     * Clears the text fields.
     */
    @FXML
    private void clear() {
        P1_1.setText("");
        P1_2.setText("");
        P2_1.setText("");
        P2_2.setText("");
        P3_1.setText("");
        P3_2.setText("");
    }

    /**
     * Validates and constructs the syllogism based on user input.
     */
    @FXML
    private void validate() {
        Figure figure = Figure.valueOf(choix_figure.getSelectionModel().getSelectedItem());

        Quantificator quantificator1 = QuantificatorList.getInstance().getQuantificator(Q1.getSelectionModel().getSelectedItem());
        Quantificator quantificator2 = QuantificatorList.getInstance().getQuantificator(Q2.getSelectionModel().getSelectedItem());
        Quantificator quantificator3 = QuantificatorList.getInstance().getQuantificator(Q3.getSelectionModel().getSelectedItem());

        String sujet1 = P1_1.getText();
        String sujet2 = P2_1.getText();
        String sujet3 = P3_1.getText();

        String predicat1 = P1_2.getText();
        String predicat2 = P2_2.getText();
        String predicat3 = P3_2.getText();

        Proposition p1 = new Proposition(quantificator1, sujet1, predicat1, Quality.valueOf(ql1));
        Proposition p2 = new Proposition(quantificator2, sujet2, predicat2, Quality.valueOf(ql2));
        Proposition p3 = new Proposition(quantificator3, sujet3, predicat3, Quality.valueOf(ql3));

        Map<Integer, Proposition> map = new HashMap<>();
        map.put(1, p1);
        map.put(2, p2);
        map.put(3, p3);

        Syllogism s = new Syllogism(figure, map);
        // Additional processing of the syllogism can be done here
    }

    // Methods to set the quality for the propositions
    @FXML
    private void affirmatif1() { ql1 = "Affirmative"; }
    @FXML
    private void negatif1() { ql1 = "Negative"; }

    @FXML
    private void affirmatif2() { ql2 = "Affirmative"; }
    @FXML
    private void negatif2() { ql2 = "Negative"; }

    @FXML
    private void affirmatif3() { ql3 = "Affirmative"; }
    @FXML
    private void negatif3() { ql3 = "Negative"; }

    /**
     * Enables or disables the input fields based on the current selection.
     */
    @FXML
    public void fillorder() {
        if (choix_figure.getSelectionModel().getSelectedItem() != null) {
            Q1.setDisable(false);
        } else {
            disableInputs(true);
        }

        // Enable/disable fields based on current selections
        updateInputFields();
    }

    /**
     * Disables all input fields.
     *
     * @param disable True to disable inputs, false to enable.
     */
    private void disableInputs(boolean disable) {
        P1_1.setDisable(disable);
        P1_2.setDisable(disable);
        P2_1.setDisable(disable);
        P2_2.setDisable(disable);
        P3_1.setDisable(disable);
        P3_2.setDisable(disable);
        Q1.setDisable(disable);
        Q2.setDisable(disable);
        Q3.setDisable(disable);
    }

    /**
     * Updates input fields based on user selections.
     */
    private void updateInputFields() {
        // Logic to enable or disable fields based on current selections
        // The previous implementation of enabling/disabling can be optimized or refactored here
        // Example:
        // Enable P1_1 if Q1 has a selected item
        // Enable P1_2 if P1_1 is not empty, etc.
        // Implement as necessary
    }
}
