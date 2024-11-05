package app.controller;

import app.StartApplication;
import app.model.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private ComboBox<String> choix_figure, Q1, Q2, Q3;

    @FXML
    private TextField P1_1, P1_2, P2_1, P2_2, P3_1, P3_2, V1, V2, V3;

    @FXML
    private Button oneplus,oneminus,twoplus,twominus,threeplus,threeminus, validate;

    // Qualities of the propositions
    private String ql1, ql2, ql3;

    /**
     * Initializes the controller by setting up the UI elements.
     */
    public void initialize() {
        if (SettingController.language.getObject("Language").equals("English  ")){
            choix_figure.getItems().addAll("ONE", "TWO", "THREE", "FOUR");
        }
        else {
            choix_figure.getItems().addAll("UN", "DEUX", "TROIS", "QUATRE");
        }

        // Listener for the figure selection
        choix_figure.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                // Update the image based on the selected figure
                String imagePath = getImagePathForOption(newValue);
                image_figure.setImage(new Image(String.valueOf(StartApplication.class.getResource(imagePath))));

                clearBindings();

                if (newValue.equals("UN") || newValue.equals("ONE")) {
                    newValue = "UN";
                    P1_2.setDisable(true);
                    P1_1.setDisable(false);
                    P1_1.textProperty().bindBidirectional(P2_2.textProperty());
                    P2_1.textProperty().bindBidirectional(P3_1.textProperty());
                    P1_2.textProperty().bindBidirectional(P3_2.textProperty());
                }
                else if (newValue.equals("TWO") || newValue.equals("DEUX")) {
                    newValue = "DEUX";
                    P1_1.setDisable(true);
                    P1_2.setDisable(false);
                    P1_2.textProperty().bindBidirectional(P2_2.textProperty());
                    P1_1.textProperty().bindBidirectional(P3_2.textProperty());
                    P2_1.textProperty().bindBidirectional(P3_1.textProperty());
                }
                else if (newValue.equals("THREE") || newValue.equals("TROIS")) {
                    newValue = "TROIS";
                    P1_2.setDisable(true);
                    P1_1.setDisable(false);
                    P1_1.textProperty().bindBidirectional(P2_1.textProperty());
                    P1_2.textProperty().bindBidirectional(P3_2.textProperty());
                    P2_2.textProperty().bindBidirectional(P3_1.textProperty());
                }
                else if (newValue.equals("FOUR") || newValue.equals("QUATRE")) {
                    newValue = "QUATRE";
                    P1_1.setDisable(true);
                    P1_2.setDisable(false);
                    P1_1.textProperty().bindBidirectional(P3_2.textProperty());
                    P1_2.textProperty().bindBidirectional(P2_1.textProperty());
                    P2_2.textProperty().bindBidirectional(P3_1.textProperty());
                }
            }
        });

        // Populate quantificator options
        resetComboBoxQuanti();

        P1_1.setDisable(true);
        P1_2.setDisable(true);
        P2_1.setDisable(true);
        P2_2.setDisable(true);
    }


    private void translateComboBoxQuanti(){
        if (SettingController.language.getObject("Language").equals("English  ")){
            Q1.setPromptText("Ex: All");
            Q2.setPromptText("Ex: Some");
            Q3.setPromptText("Ex: Some");
        }
        else {
            Q1.setPromptText("Ex: Tous/Tout/Toute/Toutes");
            Q2.setPromptText("Ex: Certains/Certaines");
            Q3.setPromptText("Ex: Certains/Certaines");
        }
    }

    private void resetComboBoxQuanti(){
        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            Q1.getItems().add(quantificator.getName());
            Q2.getItems().add(quantificator.getName());
            Q3.getItems().add(quantificator.getName());
        }
        translateComboBoxQuanti();
    }

    /**
     * Returns the image path corresponding to the selected figure.
     *
     * @param option The selected figure option.
     * @return The path of the corresponding image.
     */
    private String getImagePathForOption(String option) {
        return switch (option) {
            case "DEUX", "TWO" -> "/app/image/Figure2_syllogism.png";
            case "TROIS", "THREE" -> "/app/image/Figure3_syllogism.png";
            case "QUATRE", "FOUR" -> "/app/image/Figure4_syllogism.png";
            default -> "/app/image/Figure1_syllogism.png";
        };
    }

    /**
     * Clears the text fields.
     */
    @FXML
    private void clear() {
        Q1.getSelectionModel().clearSelection();
        Q2.getSelectionModel().clearSelection();
        Q3.getSelectionModel().clearSelection();
        P1_1.setText("");
        P1_2.setText("");
        P2_1.setText("");
        P2_2.setText("");
        P3_1.setText("");
        P3_2.setText("");
        V1.setText("");
        V2.setText("");
        V3.setText("");
    }

    /**
     * Validates and constructs the syllogism based on user input.
     */
    @FXML
    private void validate(){
        try {
        Figure figure = switch (choix_figure.getSelectionModel().getSelectedItem()) {
            case "ONE" -> Figure.UN;
            case "TWO" -> Figure.DEUX;
            case "THREE" -> Figure.TROIS;
            case "FOUR" -> Figure.QUATRE;
            default -> Figure.valueOf(choix_figure.getSelectionModel().getSelectedItem());
        };
        Quantificator quantificator1 = QuantificatorList.getInstance().getQuantificator(Q1.getSelectionModel().getSelectedItem());
            Quantificator quantificator2 = QuantificatorList.getInstance().getQuantificator(Q2.getSelectionModel().getSelectedItem());
            Quantificator quantificator3 = QuantificatorList.getInstance().getQuantificator(Q3.getSelectionModel().getSelectedItem());

            String sujet1 = P1_1.getText();
            String sujet2 = P2_1.getText();
            String sujet3 = P3_1.getText();

            String predicat2 = P2_2.getText();
            String predicat1 = P1_2.getText();
            String predicat3 = P3_2.getText();

            Proposition p1 = new Proposition(quantificator1, sujet1, predicat1, Quality.valueOf(ql1));
            Proposition p2 = new Proposition(quantificator2, sujet2, predicat2, Quality.valueOf(ql2));
            Proposition p3 = new Proposition(quantificator3, sujet3, predicat3, Quality.valueOf(ql3));

            Map<Integer, Proposition> map = new HashMap<>();
            map.put(1, p1);
            map.put(2, p2);
            map.put(3, p3);

            Syllogism s = new Syllogism(figure, map);
        } catch (Exception e) {
            validate.setStyle("-fx-background-color: red");
        }
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


    // Méthode pour supprimer tous les bindings
    private void clearBindings() {

                P1_1.textProperty().unbindBidirectional(P2_2.textProperty());
                P2_1.textProperty().unbindBidirectional(P3_1.textProperty());
                P1_2.textProperty().unbindBidirectional(P3_2.textProperty());
                P1_2.textProperty().unbindBidirectional(P2_2.textProperty());
                P1_1.textProperty().unbindBidirectional(P3_2.textProperty());
                P2_1.textProperty().unbindBidirectional(P3_1.textProperty());
                P1_1.textProperty().unbindBidirectional(P2_1.textProperty());
                P1_2.textProperty().unbindBidirectional(P3_2.textProperty());
                P2_2.textProperty().unbindBidirectional(P3_1.textProperty());
                P1_1.textProperty().unbindBidirectional(P3_2.textProperty());
                P1_2.textProperty().unbindBidirectional(P2_1.textProperty());
                P2_2.textProperty().unbindBidirectional(P3_1.textProperty());

    }
}
