package app.controller;

import app.StartApplication;
import app.model.allSyllogism.Data;
import app.model.allSyllogism.PolySyllogismAndRules;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.List;

import static app.StartApplication.scene;

public class ArrayController implements Resize{

    @FXML
    public Button allButton;
    public Text txt_nbValide;
    @FXML
    private Text nbValide;
    @FXML
    private GridPane table;
    @FXML
    private GridPane table_menu;
    private Data data;

    private boolean all;


    @FXML
    public void initialize() {

        all = true;
        setTextBUtton();
        URL resourceUrl = getClass().getResource("/app/Tableur.xlsx");

        if (resourceUrl == null) {
            System.err.println("File not found in classpath: /app/Tableur.xlsx");
            return;
        }

        String decodedPath;
        // Décoder le chemin pour éviter les caractères encodés
        try {
            decodedPath = URLDecoder.decode(resourceUrl.getPath(), "UTF-8");
            // Ajuster pour retirer le "/" initial si nécessaire
            if (decodedPath.startsWith("/") && decodedPath.charAt(2) == ':') {
                decodedPath = decodedPath.substring(1);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        String absolutePath = Paths.get(decodedPath).toAbsolutePath().toString();

        data = new Data(absolutePath);
        data.load();
        readData();
        setupColumnConstraints(14); // headers.length = nombre de colonnes
        resize();
    }
    
    private void readData(){

        List<PolySyllogismAndRules> sar = data.getAllSyllogismAndRules();
        String[] headers = {
                "Figure", "Proposition 1", "Proposition 2", "Conclusion",
                "Rmt", "Rlh", "Rnn", "Rn", "Raa", "Rpp", "Rp",
                SettingController.getLanguage().equals("english") ? "Syllo_VaLid" : "Syllo_Valide",
                "Ruu", "Rii"
        };
        for (int i = 0; i < headers.length; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / (headers.length-1));
            table_menu.getColumnConstraints().add(column);

        }
        RowConstraints rowConstraints = new RowConstraints();

        table_menu.getRowConstraints().add(rowConstraints);

        for (int col = 0; col < headers.length; col++) {
            addStyledCell(table_menu,new Label(headers[col]), col, 0, "table-header");
        }

        int idI=1;
        for(PolySyllogismAndRules s : sar){
           if (all || s.getSyllogism().solve()) {
                addStyledCell(table, new Label(s.getSyllogism().getFigure().toString()), 0, idI, "table-cell");
                addStyledCell(table, new Label(s.getSyllogism().getProposition().get(1).getType().toString()), 1, idI, "table-cell");
                addStyledCell(table, new Label(s.getSyllogism().getProposition().get(2).getType().toString()), 2, idI, "table-cell");
                addStyledCell(table, new Label(s.getSyllogism().getProposition().get(3).getType().toString()), 3, idI, "table-cell");

                for (int i = 0; i < s.getAllRules().size(); i++) {
                    if (i == 10) {
                        addStyledCell(table, new Label(String.valueOf(!s.getRules(i))), i + 3, idI, "table-cell");
                    } else if (i != s.getAllRules().size() - 2) {
                        addStyledCell(table, new Label(String.valueOf(s.getRules(i))), i + 4, idI, "table-cell");
                    }
                }
                idI++;
            }
        }
        nbValide.setText(String.valueOf((idI-1)));
        table.setMinHeight(Region.USE_COMPUTED_SIZE);
        table.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        table_menu.setMinHeight(Region.USE_COMPUTED_SIZE);
        table_menu.setPrefHeight(Region.USE_COMPUTED_SIZE);
        //table.setMaxHeight(Double.MAX_VALUE);

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

    private void addStyledCell(GridPane table,Label label, int col, int row, String styleClass) {
       label.getStyleClass().add(styleClass);
       table.add(label, col, row);
       label.getStyleClass().add(row % 2 == 0 ? "even-row" : "odd-row");
       if (label.getText().equals("false")){
           label.setStyle("-fx-background-color: #ff3800");
       }

    }
    private void setupColumnConstraints(int numCols) {
        table.getColumnConstraints().clear();
        table_menu.getColumnConstraints().clear();

        for (int i = 0; i < numCols; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100.0 / numCols);
            //column.setPercentWidth(Region.USE_COMPUTED_SIZE);
            column.setHalignment(HPos.CENTER);
            table.getColumnConstraints().add(column);
            table_menu.getColumnConstraints().add(column);
        }
    }

    private void setTextBUtton(){
        if (all){
            if (SettingController.getLanguage().equals("english")) {
                allButton.setText("Valid");
            }else{
                allButton.setText("Valide");
            }
        }else{
            if (SettingController.getLanguage().equals("english")) {
                allButton.setText("all");
            }else{
                allButton.setText("tous");
            }
        }
    }
    @FXML
    public void showAll() {
        all = !all;
        setTextBUtton();
        table.getChildren().clear();
        table_menu.getChildren().clear();
        readData();
        resize();
    }

    @Override
    public void resize() {
        resizeFontSize();
        resizeCell();

        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            resizeCell();
            resizeFontSize();
        });
        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            resizeCell();
        });
    }

    private void resizeCell() {
        table_menu.setMinWidth(scene.widthProperty().getValue()*0.8);
        for (Node node : table_menu.getChildren()) {
            if (node instanceof Label){
                ((Label) node).setMinWidth(scene.widthProperty().getValue()*0.06);
                ((Label) node).setMinHeight(scene.heightProperty().getValue()*0.045);
            }
        }
        for (Node node : table.getChildren()) {
            if (node instanceof Label){
                ((Label) node).setMinWidth(scene.widthProperty().getValue()*0.06);
                ((Label) node).setMinHeight(scene.heightProperty().getValue()*0.06);
            }
        }
        setupColumnConstraints(14);
        allButton.setMinWidth(scene.widthProperty().getValue()*0.05);
        allButton.setMinHeight(scene.heightProperty().getValue()*0.05);
    }

    private void resizeFontSize() {
        for(Node e : table.getChildren()){
            if (e instanceof Label){
                ((Label) e).setFont(Font.font(scene.widthProperty().getValue() *0.01)); // 1% of window width
            }
        }
        allButton.setFont(Font.font(scene.widthProperty().getValue() / 65));

        nbValide.setFont(Font.font(scene.widthProperty().getValue() / 65));
        txt_nbValide.setFont(Font.font(scene.widthProperty().getValue() / 65));
    }
}

