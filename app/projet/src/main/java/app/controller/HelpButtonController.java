package app.controller;

import app.StartApplication;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import static app.StartApplication.scene;

public class HelpButtonController implements Resize{
    @FXML
    HBox rootHelp, RulesPrinter,Help1,Help2,Help3,Help4,Help5;
    @FXML
    VBox vbox1;
    @FXML
    Text  rulePrinter, NameRule;
    @FXML
    Label upLabel, downLabel, stateLabel;
    @FXML
    Polygon polyDown,polyUp,polyState;
    @FXML
    ImageView imgFigure, imgQuant, imgVValidate, imgWValidate, imgQualite;

    Map<Integer, String> RulesContent_fr;
    Map<Integer, String> RulesContent_eng;
    Map<Integer, String> RulesNames_fr;
    Map<Integer, String> RulesNames_eng;
    Map<Integer, String> RulesPrint;
    int counter;
    MenuController menuController;
    final int ImgSize = 250;

    public void initialize(){
        counter = 0;
        RulesContent_fr = new HashMap<>();
        RulesNames_eng = new HashMap<>();
        RulesNames_fr = new HashMap<>();
        RulesContent_eng = new HashMap<>();
        RulesPrint = new HashMap<>();

        if (SettingController.getLanguage().equals("english")){
            imgFigure.setImage(new Image(String.valueOf(StartApplication.class.getResource("/app/image/HelpFigureImage_eng.png"))));
            imgQuant.setImage(new Image(String.valueOf(StartApplication.class.getResource("/app/image/HelpQuantifierImage_eng.png"))));
        }else{
            imgFigure.setImage(new Image(String.valueOf(StartApplication.class.getResource("/app/image/HelpFigureImage_fr.png"))));
            imgQuant.setImage(new Image(String.valueOf(StartApplication.class.getResource("/app/image/HelpQuantifierImage_fr.png"))));
        }

        imgVValidate.setImage(new Image(String.valueOf(StartApplication.class.getResource("/app/image/HelpVValidate.png"))));
        imgWValidate.setImage(new Image(String.valueOf(StartApplication.class.getResource("/app/image/HelpWValidate.png"))));
        imgQualite.setImage(new Image(String.valueOf(StartApplication.class.getResource("/app/image/HelpQualite.png"))));
        //rootHelp.setStyle("-fx-background-color: black");

        RulesContent_fr.put(0, "Deux prémisses affirmatives donnent une conclusion affirmative.");
        RulesContent_fr.put(1, "Un syllogisme est considéré comme inintéressant si sa conclusion est existentielle et peut être remplacée par une conclusion universelle plus forte.");
        RulesContent_fr.put(2, "La quantité d’un terme de la conclusion ne peut être universelle que si elle l’est dans la prémisse contenant ce terme.");
        RulesContent_fr.put(3, "La quantité de M doit être universelle dans l’une des prémisses au moins.");
        RulesContent_fr.put(4, "Si une prémisse est négative, la conclusion est négative.");
        RulesContent_fr.put(5, "Deux prémisses négatives ne donnent pas de conclusion.");
        RulesContent_fr.put(6, "Si une prémisse est particulière la conclusion est particulière.");
        RulesContent_fr.put(7, "Deux prémisses particulières ne donnent pas de conclusion.");
        RulesContent_fr.put(8, "Deux prémisses universelles ne conduisent pas à une conclusion particulière.");

        RulesContent_eng.put(0, "Two affirmative premises give an affirmative conclusion.");
        RulesContent_eng.put(1, "A syllogism is considered uninteresting if its conclusion is existential and can be replaced by a stronger universal conclusion.");
        RulesContent_eng.put(2, "The quantity of a term in the conclusion can only be universal if it is universal in the premise containing that term.");
        RulesContent_eng.put(3, "The quantity of M must be universal in at least one of the premises.");
        RulesContent_eng.put(4, "If a premise is negative, the conclusion is negative.");
        RulesContent_eng.put(5, "Two negative premises do not give a conclusion.");
        RulesContent_eng.put(6, "If a premise is particular, the conclusion is particular.");
        RulesContent_eng.put(7, "Two particular premises do not give a conclusion.");
        RulesContent_eng.put(8, "Two universal premises do not lead to a particular conclusion.");

        RulesNames_eng.put(0, "Raa\n(Rule of Affirmative Premises)\n");
        RulesNames_eng.put(1, "Rii\n(Rule of Existential Propositions)\n");
        RulesNames_eng.put(2, "Rlh\n(Rule of the Latius Hos)\n");
        RulesNames_eng.put(3, "Rmt\n(Rule of the Middle Term)\n");
        RulesNames_eng.put(4, "Rn\n(Rule of Negative Conclusion)\n");
        RulesNames_eng.put(5, "Rnn\n(Rule of Negative Premises)\n");
        RulesNames_eng.put(6, "Rp\n(Rule of Particular Conclusion)\n");
        RulesNames_eng.put(7, "Rpp\n(Rule of Particular Premises)\n");
        RulesNames_eng.put(8, "Ruu\n(Rule of Universal Propositions)\n");

        RulesNames_fr.put(0, "Raa\n(Règle des prémisses affirmatives)\n");
        RulesNames_fr.put(1, "Rii\n(Règle des propositions existentielles)\n");
        RulesNames_fr.put(2, "Rlh\n(Règle du Latius Hos)\n");
        RulesNames_fr.put(3, "Rmt\n(Règle du moyen terme)\n");
        RulesNames_fr.put(4, "Rn\n(Règle de la conclusion négative)\n");
        RulesNames_fr.put(5, "Rnn\n(Règle des prémisses négatives)\n");
        RulesNames_fr.put(6, "Rp\n(Règle de la conclusion particulière)\n");
        RulesNames_fr.put(7, "Rpp\n(Règle des prémisses particulières)\n");
        RulesNames_fr.put(8, "Ruu\n(Règle des propositions universelles)\n");

        RulesPrint.put(0, "Raa");
        RulesPrint.put(1, "Rii");
        RulesPrint.put(2, "Rlh");
        RulesPrint.put(3, "Rmt");
        RulesPrint.put(4, "Rn");
        RulesPrint.put(5, "Rnn");
        RulesPrint.put(6, "Rp");
        RulesPrint.put(7, "Rpp");
        RulesPrint.put(8, "Ruu");

        if (SettingController.getLanguage().equals("english")){
            NameRule.setText(RulesNames_eng.get(counter));
            rulePrinter.setText(RulesContent_eng.get(counter));
        }else{
            NameRule.setText(RulesNames_fr.get(counter));
            rulePrinter.setText(RulesContent_fr.get(counter));
        }
        stateLabel.setText(RulesPrint.get(counter));
        downLabel.setText(RulesPrint.get(counter+1));

        // Empêche l'événement de se propager aux nœuds sous-jacents
        RulesPrinter.addEventHandler(MouseEvent.MOUSE_CLICKED, Event::consume);

        resize();


    }

    @FXML
    private void countIncr(){
        if(counter<8){
            counter++;
            if (SettingController.getLanguage().equals("english")){
                NameRule.setText(RulesNames_eng.get(counter));
                rulePrinter.setText(RulesContent_eng.get(counter));
            }else{
                NameRule.setText(RulesNames_fr.get(counter));
                rulePrinter.setText(RulesContent_fr.get(counter));
            }
            upLabel.setText(RulesPrint.get(counter-1));
            stateLabel.setText(RulesPrint.get(counter));
            downLabel.setText(RulesPrint.get(counter+1));
        }
    }

    @FXML
    private void countDecr(){
        if(counter>0){
            counter--;
            upLabel.setText(RulesPrint.get(counter-1));
            stateLabel.setText(RulesPrint.get(counter));
            downLabel.setText(RulesPrint.get(counter+1));
            if (SettingController.getLanguage().equals("english")){
                NameRule.setText(RulesNames_eng.get(counter));
                rulePrinter.setText(RulesContent_eng.get(counter));
            }else{
                NameRule.setText(RulesNames_fr.get(counter));
                rulePrinter.setText(RulesContent_fr.get(counter));
            }
        }
    }

    @FXML
    private void close(){
        menuController.stackroot.getChildren().remove(menuController.stackroot.getChildren().size()-1);
    }

    public void setMainController(MenuController mainController){
        menuController = mainController;
    }

    private void resizeFontSize() {
        vbox1.setPrefWidth(scene.widthProperty().getValue()*0.80);
        vbox1.setPrefHeight(scene.heightProperty().getValue());
        /// ////////////
        //SCALE POLYGONE
        /// ////////////
        double targetWidth = scene.widthProperty().getValue() * (125.0 / 16 / 100); // 125/16% de la largeur
        double targetHeight = scene.heightProperty().getValue() * (125.0 / 9 / 100); // 125/9% de la hauteur

        // Définir les proportions initiales du triangle
        double baseWidth = 100.0; // Largeur initiale de la base
        double baseHeight = 100.0; // Hauteur initiale du triangle

        // Facteurs d'échelle
        double scaleFactorX = targetWidth / baseWidth;
        double scaleFactorY = targetHeight / baseHeight;

        // Recalculer les points du triangle
        polyUp.getPoints().clear(); // Efface les anciens points
        polyUp.getPoints().addAll(
                100.0 * scaleFactorX, 150.0 * scaleFactorY,   // Bas gauche
                200.0 * scaleFactorX, 150.0 * scaleFactorY,   // Bas droit
                150.0 * scaleFactorX, 50.0 * scaleFactorY
        );

        polyDown.getPoints().clear();
        polyDown.getPoints().addAll(
                50.0 * scaleFactorX, 50.0 * scaleFactorY,   // Point 1 (coin gauche-bas)
                150.0 * scaleFactorX, 50.0 * scaleFactorY,  // Point 2 (coin droite-bas)
                100.0 * scaleFactorX, 150.0 * scaleFactorY  // Point 3 (sommet haut)
        );

        polyState.getPoints().clear();
        polyState.getPoints().addAll(
                50.0 * scaleFactorX, 50.0 * scaleFactorY,   // Point 1 (coin haut-gauche)
                150.0 * scaleFactorX, 50.0 * scaleFactorY,  // Point 2 (coin haut-droit)
                150.0 * scaleFactorX, 75.0 * scaleFactorY,  // Point 3 (coin bas-droit)
                50.0 * scaleFactorX, 75.0 * scaleFactorY    // Point 4 (coin bas-gauche)
        );

    }

    /**
     * Resizes buttons and circles based on the current window dimensions.
     */
    private void resizeButtons() {
        imgFigure.setFitHeight((scene.heightProperty().getValue()/100)*35.61);
        imgQuant.setFitHeight((scene.heightProperty().getValue()/100)*35.61);
        imgVValidate.setFitHeight((scene.heightProperty().getValue()/100)*((double) 20 /3));
        imgWValidate.setFitHeight((scene.heightProperty().getValue()/100)*6.32);
        imgQualite.setFitHeight((scene.heightProperty().getValue()/100)*(((double) 20 /3)*2));

//
//
//
//
//        P1_1.setMinHeight(scene.heightProperty().getValue() / 10); // 10% of window height
//        P1_2.setMinHeight(scene.heightProperty().getValue() / 10);
//        V1.setMinHeight(scene.heightProperty().getValue() / 10);
//        Q1.setMinHeight(scene.heightProperty().getValue() / 10);
//        P2_1.setMinHeight(scene.heightProperty().getValue() / 10);
//        P2_2.setMinHeight(scene.heightProperty().getValue() / 10);
//        V2.setMinHeight(scene.heightProperty().getValue() / 10);
//        Q2.setMinHeight(scene.heightProperty().getValue() / 10);
//        P3_1.setMinHeight(scene.heightProperty().getValue() / 10);
//        P3_2.setMinHeight(scene.heightProperty().getValue() / 10);
//        V3.setMinHeight(scene.heightProperty().getValue() / 10);
//        Q3.setMinHeight(scene.heightProperty().getValue() / 10);
//        validate.setMinHeight(scene.heightProperty().getValue() / 10);
//        clear.setMinHeight(scene.heightProperty().getValue() / 10);
//        image_figure.setFitHeight(scene.heightProperty().getValue() / 10);
//        choix_figure.setMinHeight(scene.heightProperty().getValue() / 10);
//
//
//        oneplus.setRadius(scene.widthProperty().getValue() / 60); // 1.5% of window width
//        oneminus.setRadius(scene.widthProperty().getValue() / 60);
//        twoplus.setRadius(scene.widthProperty().getValue() / 60);
//        twominus.setRadius(scene.widthProperty().getValue() / 60);
//        threeminus.setRadius(scene.widthProperty().getValue() / 60);
//        threeplus.setRadius(scene.widthProperty().getValue() / 60);

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
}
