package app.controller;

import app.StartApplication;
import app.model.QuantificatorList;
import app.model.Quantity;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class HelpButtonController {
    @FXML
    HBox rootHelp, RulesPrinter;
    @FXML
    Text  rulePrinter, NameRule;
    @FXML
    Label upLabel, downLabel, stateLabel;
    @FXML
    Polygon polyUp;
    @FXML
    Polygon polyDown;
    @FXML
    ImageView imgFigure, imgQuant, imgVValidate, imgWValidate;

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
        imgFigure.setFitHeight(ImgSize);
        imgQuant.setFitHeight(ImgSize);

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
}
