package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class UnguidedController {

    @FXML
    TextField TF1_1,TF2_1,TF3_1,TF1_2,TF2_2,TF3_2,TF1_3,TF2_3,TF3_3;
    @FXML
    Button Btn_P1,Btn_P2,Btn_P3, Btn_Clear, Btn_Validate,Add_syllogism,Btn_N1,Btn_N2,Btn_N3;
    @FXML
    ComboBox cb1,cb2,cb3;
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


    }




    public void initialize(){}
}
