package app.controller;

import app.model.allSyllogism.Data;
import app.model.allSyllogism.SyllogismAndRules;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class ArrayController {

    @FXML
    private GridPane table;
    @FXML
    private GridPane table_menu;
    private Data data;

    @FXML
    public void initialize() {
        System.out.println("URL : " + Paths.get("Syllogism/app/projet/src/main/resources/app/Tableur.xlsx").toAbsolutePath().toString());

        URL resourceUrl = getClass().getResource("/app/Tableur.xlsx");

        if (resourceUrl == null) {
            System.err.println("File not found in classpath: /app/Tableur.xlsx");
            return;
        }

        String absolutePath = Paths.get(resourceUrl.getPath()).toAbsolutePath().toString();
        System.out.println("Correct File path is: " + absolutePath);

        data = new Data(absolutePath);
        data.load();
        readData();
        setupColumnConstraints(14); // headers.length = nombre de colonnes
    }
    
    private void readData(){

        List<SyllogismAndRules> sar = data.getAllSyllogismAndRules();
        String[] headers = {
                "Figure", "Proposition 1", "Proposition 2", "Conclusion",
                "Rmt", "Rlh", "Rnn", "Rn", "Raa", "Rpp", "Rp",
                SettingController.getLanguage().equals("english") ? "Valid" : "Valide",
                "Ruu", "Rii"
        };
        for (int i = 0; i < headers.length; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / headers.length);
            table_menu.getColumnConstraints().add(column);

        }
        RowConstraints rowConstraints = new RowConstraints();

        rowConstraints.setPrefHeight(40);
        rowConstraints.setMaxHeight(40);
        table_menu.getRowConstraints().add(rowConstraints);

        for (int col = 0; col < headers.length; col++) {
            addStyledCell(table_menu,new Label(headers[col]), col, 0, "table-header");
        }

        int idI=1;

        for(SyllogismAndRules s : sar){
            addStyledCell(table,new Label(s.getSyllogism().getFigure().toString()), 0, idI, "table-cell");
            addStyledCell(table,new Label(s.getSyllogism().getProposition().get(1).getType().toString()), 1, idI, "table-cell");
            addStyledCell(table,new Label(s.getSyllogism().getProposition().get(2).getType().toString()), 2, idI, "table-cell");
            addStyledCell(table,new Label(s.getSyllogism().getProposition().get(3).getType().toString()), 3, idI, "table-cell");

            System.out.println("Size : " + s.getAllRules().size());

            for(int i=0; i<s.getAllRules().size(); i++){
                if (i == 10){
                   addStyledCell(table,new Label(String.valueOf(s.getRules(i))), i+3, idI, "table-cell");
                    System.out.println("i : " + i);
                }
                else if (i != s.getAllRules().size()-2){
                   addStyledCell(table,new Label(String.valueOf(s.getRules(i))), i + 4, idI, "table-cell");
                    System.out.println("i : " + i);
                }

            }

            idI++;
        }
        table.setMinHeight(Region.USE_COMPUTED_SIZE);
        table.setPrefHeight(Region.USE_COMPUTED_SIZE);
        table.setMaxHeight(Double.MAX_VALUE);

    }
    private void printGridContent() {
        for (Node node : table.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer colIndex = GridPane.getColumnIndex(node);

            if (rowIndex == null) rowIndex = 0;
            if (colIndex == null) colIndex = 0;

            String content = "";
            if (node instanceof Label) {
                content = ((Label) node).getText();
            }

            System.out.println("Cellule (" + rowIndex + ", " + colIndex + ") : " + content);
        }
    }

    private void translateArray(){
        if (SettingController.getLanguage().equals("english")) {

        }
    }
    private void addStyledCell(GridPane table,Label label, int col, int row, String styleClass) {
       label.getStyleClass().add(styleClass);
        table.add(label, col, row);
       label.getStyleClass().add(row % 2 == 0 ? "even-row" : "odd-row");

    }
    private void setupColumnConstraints(int numCols) {
        table.getColumnConstraints().clear();
        table_menu.getColumnConstraints().clear();

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / numCols);
            column.setHalignment(HPos.CENTER);
            table.getColumnConstraints().add(column);
            table_menu.getColumnConstraints().add(column);
        }
    }

}

