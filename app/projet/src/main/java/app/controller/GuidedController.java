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


public class GuidedController {
    @FXML
    ImageView image_figure;

    @FXML
    ComboBox<String> Q1,Q2,Q3;
    @FXML
    ComboBox<String> choix_figure;

    @FXML
    TextField P1_1,P1_2,P2_1,P2_2,P3_1,P3_2;

    @FXML
    Button oneplus,oneminus,twoplus,twominus,threeplus,threeminus, validate;

    //qualité
    String ql1, ql2, ql3;




    public void initialize(){
        choix_figure.getItems().addAll("UN", "DEUX", "TROIS", "QUATRE");

        // Listener pour détecter les changements de sélection dans le ComboBox
        choix_figure.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                // Met à jour l'image en fonction de la sélection
                String imagePath = getImagePathForOption(newValue);
                image_figure.setImage(new Image(String.valueOf(StartApplication.class.getResource(imagePath))));
            }
        });

        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
                Q1.getItems().add(quantificator.getName());
                Q2.getItems().add(quantificator.getName());
                Q3.getItems().add(quantificator.getName());
        }

        P1_1.setDisable(true);
        P1_2.setDisable(true);
        P2_1.setDisable(true);
        P2_2.setDisable(true);
        P3_1.setDisable(true);
        P3_2.setDisable(true);
        Q1.setDisable(true);
        Q2.setDisable(true);
        Q3.setDisable(true);
    }

    public String getImagePathForOption(String option){
        return switch (option) {
            case "DEUX" -> "/app/image/Figure2_syllogism.png";
            case "TROIS" -> "/app/image/Figure3_syllogism.png";
            case "QUATRE" -> "/app/image/Figure4_syllogism.png";
            default -> "/app/image/Figure1_syllogism.png";
        };
    }

    @FXML
    private void clear(){
        P1_1.setText("");
        P1_2.setText("");
        P2_1.setText("");
        P2_2.setText("");
        P3_1.setText("");
        P3_2.setText("");
    }

    @FXML
    private void validate(){
        try {
            Figure figure = Figure.valueOf(choix_figure.getSelectionModel().getSelectedItem());

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

    @FXML
    private void affirmatif1(){
        ql1 = "Affirmative";
        //oneplus.setStyle("SELECTED_STYLE");
        //oneminus.setStyle("DEFAULT_STYLE");
    }
    @FXML
    private void negatif1(){
        ql1 = "Negative";
        //oneminus.setStyle("SELECTED_STYLE");
        //oneplus.setStyle("DEFAULT_STYLE");
    }

    @FXML
    private void affirmatif2(){
        ql2 = "Affirmative";
        //twoplus.setStyle("SELECTED_STYLE");
        //twominus.setStyle("DEFAULT_STYLE");
    }
    @FXML
    private void negatif2(){
        ql2 = "Negative";
        //twominus.setStyle("SELECTED_STYLE");
        //twoplus.setStyle("DEFAULT_STYLE");
    }

    @FXML
    private void affirmatif3(){
        ql3 = "Affirmative";
        //threeplus.setStyle("SELECTED_STYLE");
        //threeminus.setStyle("DEFAULT_STYLE");
    }
    @FXML
    private void negatif3(){
        ql3 = "Negative";
        //threeminus.setStyle("SELECTED_STYLE");
        //threeplus.setStyle("DEFAULT_STYLE");
    }

    @FXML
    public void fillorder() {
        validate.setStyle("-fx-background-color: #9dff8c");
        if (choix_figure.getSelectionModel().getSelectedItem() != null) {
            Q1.setDisable(false);
        } else {
            P1_1.setDisable(true);
            P1_2.setDisable(true);
            P2_1.setDisable(true);
            P2_2.setDisable(true);
            P3_1.setDisable(true);
            P3_2.setDisable(true);
            Q1.setDisable(true);
            Q2.setDisable(true);
            Q3.setDisable(true);
        }
        // Débloque P1_1 seulement si un élément est sélectionné dans Q1
        if (Q1.getSelectionModel().getSelectedItem() != null) {
            P1_1.setDisable(false);
        } else {
            P1_1.setDisable(true);
            P1_2.setDisable(true);
            P2_1.setDisable(true);
            P2_2.setDisable(true);
            P3_1.setDisable(true);
            P3_2.setDisable(true);
            Q2.setDisable(true);
            Q3.setDisable(true);
        }

        // Débloque P1_2 seulement si P1_1 n'est pas vide
        if (P1_1.getText() == null || P1_1.getText().isEmpty()) {
            P1_2.setDisable(true);
            P2_1.setDisable(true);
            P2_2.setDisable(true);
            P3_1.setDisable(true);
            P3_2.setDisable(true);
            Q2.setDisable(true);
            Q3.setDisable(true);
        } else {
            P1_2.setDisable(false);
        }

        // Débloque Q2 seulement si P1_2 n'est pas vide
        if (P1_2.getText() == null || P1_2.getText().isEmpty()) {
            P2_1.setDisable(true);
            P2_2.setDisable(true);
            P3_1.setDisable(true);
            P3_2.setDisable(true);
            Q2.setDisable(true);
            Q3.setDisable(true);
        } else {
            Q2.setDisable(false);
        }

        // Débloque P2_1 seulement si un élément est sélectionné dans Q2
        if (Q2.getSelectionModel().getSelectedItem() != null) {
            P2_1.setDisable(false);
        } else {
            P2_1.setDisable(true);
            P2_2.setDisable(true);
            P3_1.setDisable(true);
            P3_2.setDisable(true);
            Q3.setDisable(true);
        }

        // Débloque P2_2 seulement si P2_1 n'est pas vide
        if (P2_1.getText() == null || P2_1.getText().isEmpty()) {
            P2_2.setDisable(true);
            P3_1.setDisable(true);
            P3_2.setDisable(true);
            Q3.setDisable(true);
        } else {
            P2_2.setDisable(false);
        }

        // Débloque Q3 seulement si P2_2 n'est pas vide
        if (P2_2.getText() == null || P2_2.getText().isEmpty()) {
            P3_1.setDisable(true);
            P3_2.setDisable(true);
            Q3.setDisable(true);
        } else {
            Q3.setDisable(false);
        }

        // Débloque P3_1 seulement si un élément est sélectionné dans Q3
        if (Q3.getSelectionModel().getSelectedItem() != null) {
            P3_1.setDisable(false);
        } else {
            P3_1.setDisable(true);
            P3_2.setDisable(true);
        }

        // Débloque P3_2 seulement si P3_1 n'est pas vide
        if (P3_1.getText() == null || P3_1.getText().isEmpty()) {
            P3_2.setDisable(true);
        } else {
            P3_2.setDisable(false);
        }
    }
}
