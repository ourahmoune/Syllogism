package app;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class UnguidedController {

    @FXML
    TextField TF1_1,TF2_1,TF3_1,TF1_2,TF2_2,TF3_2,TF1_3,TF2_3,TF3_3;
    @FXML
    Button Btn_P1,Btn_P2,Btn_P3, Btn_Clear, Btn_Validate,Add_syllogism,Btn_N1,Btn_N2,Btn_N3, Remove_syllogism;
    @FXML
    ComboBox cb1,cb2,cb3;
    @FXML
    VBox vbox;


    private List<TextField> dynamicFields = new ArrayList<>();
    @FXML
    private void handleAdd(){
        HBox newSyllogismBox = new HBox();
        newSyllogismBox.setAlignment(javafx.geometry.Pos.CENTER);
        newSyllogismBox.setStyle("-fx-spacing: 10;");
        VBox.setVgrow(newSyllogismBox, Priority.ALWAYS);

        VBox.setMargin(newSyllogismBox, new Insets(10, 0, 10, 0));


        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPromptText("Ex: All");
        comboBox.setPrefWidth(150.0);
        comboBox.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(comboBox, Priority.ALWAYS);
        HBox.setMargin(comboBox, new Insets(0, 0, 20, 0));

        TextField textField1 = new TextField();
        textField1.setPromptText("birds");
        textField1.setStyle("-fx-prompt-text-fill: #808080;");
        textField1.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(textField1, Priority.ALWAYS);
        HBox.setMargin(textField1, new Insets(0, 0, 20, 0));

        TextField textField2 = new TextField();
        textField2.setPromptText("have");
        textField2.setStyle("-fx-prompt-text-fill: #808080;");
        textField2.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(textField2, Priority.ALWAYS);
        HBox.setMargin(textField2, new Insets(0, 0, 20, 0));

        TextField textField3 = new TextField();
        textField3.setPromptText("wings");
        textField3.setStyle("-fx-prompt-text-fill: #808080;");
        textField3.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(textField3, Priority.ALWAYS);
        HBox.setMargin(textField3, new Insets(0, 0, 20, 0));

        Button btnPlus = new Button("+");
        btnPlus.setStyle("-fx-background-radius: 15; -fx-min-width: 30; -fx-min-height: 30; -fx-max-height: 30; -fx-max-width: 30; -fx-font-size: 20; -fx-text-fill: #32b71b; -fx-background-color: lightgrey; -fx-alignment: center; -fx-padding: 0;");
        btnPlus.setMaxWidth(Double.MAX_VALUE);
        btnPlus.setPrefWidth(40);
        btnPlus.setPrefHeight(40);
        HBox.setHgrow(btnPlus, Priority.ALWAYS);

        Button btnMinus = new Button("—");
        btnMinus.setStyle("-fx-background-radius: 15; -fx-min-width: 30; -fx-min-height: 30; -fx-max-height: 30; -fx-max-width: 30; -fx-font-size: 15; -fx-text-fill: #32b71b; -fx-background-color: lightgrey; -fx-alignment: center; -fx-padding: 0; -fx-font-weight: bold;");
        btnMinus.setMaxWidth(Double.MAX_VALUE);
        btnMinus.setPrefWidth(41);
        btnMinus.setPrefHeight(37);
        HBox.setHgrow(btnMinus, Priority.ALWAYS);

        dynamicFields.add(textField1);
        dynamicFields.add(textField2);
        dynamicFields.add(textField3);

        newSyllogismBox.getChildren().addAll(comboBox, textField1, textField2, textField3,btnPlus, btnMinus);

        vbox.getChildren().add(newSyllogismBox);
    }
    @FXML
    private void handleRemove(){
            int lastIndex = vbox.getChildren().size() - 1;
            if (lastIndex >= 2) {
                vbox.getChildren().remove(lastIndex);
            }
    }
    @FXML
    private void handleClear(){
        TF1_1.clear();
        TF2_1.clear();
        TF3_1.clear();
        TF1_2.clear();
        TF2_2.clear();
        TF3_2.clear();
        TF1_3.clear();
        TF2_3.clear();
        TF3_3.clear();
        for (TextField field : dynamicFields) {
            field.clear();
        }
    }




    public void initialize(){}
}
