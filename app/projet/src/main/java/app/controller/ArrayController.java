package app.controller;

import app.StartApplication;
import app.model.Proposition;
import app.model.allSyllogism.Data;
import app.model.allSyllogism.SyllogismAndRules;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.nio.file.Paths;


import java.util.List;


public class ArrayController {

    @FXML
    private GridPane table;

    private Data data;

    @FXML
    public void initialize() {
        System.out.println("URL : " + Paths.get("Syllogism/app/projet/src/main/resources/app/Tableur.xlsx").toAbsolutePath().toString());

        data = new Data(Paths.get("Syllogism/app/projet/src/main/resources/app/Tableur.xlsx").toString());
        data.load();
        readData();
    }
    
    private void readData(){

        List<SyllogismAndRules> sar = data.getAllSyllogismAndRules();
        int idI=1;
        addStyledCell(new Label("Figure"), 0, 0, "table-header");
        addStyledCell(new Label("Proposition 1"), 1, 0, "table-header");
        addStyledCell(new Label("Proposition 2"), 2, 0, "table-header");
        addStyledCell(new Label("Conclusion"), 3, 0, "table-header");
        addStyledCell(new Label("Rmt"), 4, 0, "table-header");
        addStyledCell(new Label("Rlh"), 5, 0, "table-header");
        addStyledCell(new Label("Rnn"), 6, 0, "table-header");
        addStyledCell(new Label("Rn"), 7, 0, "table-header");
        addStyledCell(new Label("Raa"), 8, 0, "table-header");
        addStyledCell(new Label("Rpp"), 9, 0, "table-header");
        addStyledCell(new Label("Rp"), 10, 0, "table-header");
        if (SettingController.getLanguage().equals("english")){
            addStyledCell(new Label("Valid"), 11, 0, "table-header");
        }
        else addStyledCell(new Label("Valide"), 11, 0, "table-header");
        addStyledCell(new Label("Ruu"), 12, 0, "table-header");
        addStyledCell(new Label("Rii"), 13, 0, "table-header");

        for(SyllogismAndRules s : sar){
            addStyledCell(new Label(s.getSyllogism().getFigure().toString()), 0, idI, "table-cell");
            addStyledCell(new Label(s.getSyllogism().getProposition().get(1).getType().toString()), 1, idI, "table-cell");
            addStyledCell(new Label(s.getSyllogism().getProposition().get(2).getType().toString()), 2, idI, "table-cell");
            addStyledCell(new Label(s.getSyllogism().getProposition().get(3).getType().toString()), 3, idI, "table-cell");

            for(int i=0; i<s.getAllRules().size(); i++){
                if (i != s.getAllRules().size()-2){
                    addStyledCell(new Label(String.valueOf(s.getRules(i))), i + 4, idI, "table-cell");
                    System.out.println("i : " + i);

                }
                if (i == 11){
                    addStyledCell(new Label(String.valueOf(s.getRules(i))), i+3, idI, "table-cell");
                    System.out.println("i : " + i);
                }
            }
            idI++;
        }

    }

    private void translateArray(){
        if (SettingController.getLanguage().equals("english")) {

        }
    }
    private void addStyledCell(Label label, int col, int row, String styleClass) {
        label.getStyleClass().add(styleClass);
        table.add(label, col, row);
    }

}

