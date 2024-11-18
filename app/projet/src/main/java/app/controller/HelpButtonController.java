package app.controller;

import app.StartApplication;
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

    Map<Integer, String> RulesContent;
    Map<Integer, String> RulesNames;
    Map<Integer, String> RulesPrint;
    int counter;
    MenuController menuController;
    final int ImgSize = 250;

    public void initialize(){
        counter = 0;
        RulesContent = new HashMap<>();
        RulesNames = new HashMap<>();
        RulesPrint = new HashMap<>();

        imgFigure.setImage(new Image(String.valueOf(StartApplication.class.getResource("/app/image/HelpFigureImage.png"))));
        imgQuant.setImage(new Image(String.valueOf(StartApplication.class.getResource("/app/image/HelpQuantifierImage.png"))));
        imgVValidate.setImage(new Image(String.valueOf(StartApplication.class.getResource("/app/image/HelpVValidate.png"))));
        imgWValidate.setImage(new Image(String.valueOf(StartApplication.class.getResource("/app/image/HelpWValidate.png"))));
        imgFigure.setFitHeight(ImgSize);
        imgQuant.setFitHeight(ImgSize);

        RulesContent.put(0, "deux prémisses affirmatives donnent une conclusion affirmative.");
        RulesContent.put(1, "");
        RulesContent.put(2,  "la quantité d’un terme de la conclusion ne peut être universelle que si elle l’est dans la prémisse contenant ce terme");
        RulesContent.put(3, "la quantité de M doit être universelle dans l’une des prémisses au moins.");
        RulesContent.put(4, "si une prémisse est négative, la conclusion est négative");
        RulesContent.put(5, "deux prémisses négatives ne donnent pas de conclusion.");
        RulesContent.put(6, " si une prémisse est particulière la conclusion est particulière.");
        RulesContent.put(7, "deux prémisses particulières ne donnent pas de conclusion.");
        RulesContent.put(8, " deux prémisses universelles ne conduisent pas à une conclusion particulière.");

        RulesNames.put(0, "Raa\n(Rule of Affirmative Premises)\n");
        RulesNames.put(1, "Rii\n(Rule of Existential Propositions)\n");
        RulesNames.put(2, "Rlh\n(Rule of the Latius Hos)\n");
        RulesNames.put(3, "Rmt\n(Rule of the Middle Term)\n");
        RulesNames.put(4, "Rn\n(Rule of Negative Conclusion)\n");
        RulesNames.put(5, "Rnn\n(Rule of Negative Premises)\n");
        RulesNames.put(6, "Rp\n(Rule of Particular Conclusion)\n");
        RulesNames.put(7, "Rpp\n(Rule of Particular Premises)\n");
        RulesNames.put(8, "Ruu\n(Rule of Universal Propositions)\n");

        RulesPrint.put(0, "Raa");
        RulesPrint.put(1, "Rii");
        RulesPrint.put(2, "Rlh");
        RulesPrint.put(3, "Rmt");
        RulesPrint.put(4, "Rn");
        RulesPrint.put(5, "Rnn");
        RulesPrint.put(6, "Rp");
        RulesPrint.put(7, "Rpp");
        RulesPrint.put(8, "Ruu");

        NameRule.setText(RulesNames.get(counter));
        rulePrinter.setText(RulesContent.get(counter));
        stateLabel.setText(RulesPrint.get(counter));
        downLabel.setText(RulesPrint.get(counter+1));

        // Empêche l'événement de se propager aux nœuds sous-jacents
        RulesPrinter.addEventHandler(MouseEvent.MOUSE_CLICKED, Event::consume);
    }

    @FXML
    private void countIncr(){
        if(counter<8){
            counter++;
            upLabel.setText(RulesPrint.get(counter-1));
            stateLabel.setText(RulesPrint.get(counter));
            downLabel.setText(RulesPrint.get(counter+1));
            NameRule.setText(RulesNames.get(counter));
            rulePrinter.setText(RulesContent.get(counter));
        }
    }

    @FXML
    private void countDecr(){
        if(counter>0){
            counter--;
            upLabel.setText(RulesPrint.get(counter-1));
            stateLabel.setText(RulesPrint.get(counter));
            downLabel.setText(RulesPrint.get(counter+1));
            NameRule.setText(RulesNames.get(counter));
            rulePrinter.setText(RulesContent.get(counter));
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
