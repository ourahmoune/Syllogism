package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class SettingsController {
    @FXML
    private StackPane knobContainer,knobContainer1,knobContainer2,knobContainer3,knobContainer4,knobContainer5,knobContainer6,knobContainer7,knobContainer8;
    @FXML
    private Label on,on1, on2, on3,on4, on5,on6,on7,on8;
    @FXML
    private Label off, off1, off2, off3, off4, off5, off6, off7, off8;
    @FXML
    private Circle toggleBall,toggleBall1,toggleBall2,toggleBall3,toggleBall4,toggleBall5,toggleBall6,toggleBall7,toggleBall8;

    private boolean isOn = true;
    private boolean isOn1 = true;
    private boolean isOn2 = true;
    private boolean isOn3 = true;
    private boolean isOn4 = true;
    private boolean isOn5 = true;
    private boolean isOn6 = true;
    private boolean isOn7 = true;
    private boolean isOn8 = true;



    @FXML
    private void toggleSwitch1() {
        isOn = !isOn;
        updateToggle(toggleBall, on, off, isOn);
        System.out.println("Toggle Switch 1 is " + (isOn1 ? "ON" : "OFF"));
    }
    @FXML
    private void toggleSwitch2() {
        isOn1 = !isOn1;
        updateToggle(toggleBall1, on1, off1, isOn1);
        System.out.println("Toggle Switch 2 is " + (isOn1 ? "ON" : "OFF"));
    }
    @FXML
    private void toggleSwitch3() {
        isOn2 = !isOn2;
        updateToggle(toggleBall2, on2, off2, isOn2);
        System.out.println("Toggle Switch 3 is " + (isOn2 ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch4() {
        isOn3 = !isOn3;
        updateToggle(toggleBall3, on3, off3, isOn3);
        System.out.println("Toggle Switch 4 is " + (isOn3 ? "ON" : "OFF"));
    }
    @FXML
    private void toggleSwitch5() {
        isOn4 = !isOn4;
        updateToggle(toggleBall4, on4, off4, isOn4);
        System.out.println("Toggle Switch 5 is " + (isOn4 ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch6() {
        isOn5 = !isOn5;
        updateToggle(toggleBall5, on5, off5, isOn5);
        System.out.println("Toggle Switch 6 is " + (isOn5 ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch7() {
        isOn6 = !isOn6;
        updateToggle(toggleBall6, on6, off6, isOn6);
        System.out.println("Toggle Switch 7 is " + (isOn6 ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch8() {
        isOn7 = !isOn7;
        updateToggle(toggleBall7, on7, off7, isOn7);
        System.out.println("Toggle Switch 8 is " + (isOn7 ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch9() {
        isOn8 = !isOn8;
        updateToggle(toggleBall8, on8, off8, isOn8);
        System.out.println("Toggle Switch 9 is " + (isOn8 ? "ON" : "OFF"));
    }

    private void updateToggle(Circle knob, Label labelOn, Label labelOff, boolean isOn) {
        if (isOn) {
            knob.setTranslateX(-20); // Move to the "On" position
            labelOn.setOpacity(1);
            labelOff.setOpacity(0.5);
        } else {
            knob.setTranslateX(20); // Move to the "Off" position
            labelOn.setOpacity(0.5);
            labelOff.setOpacity(1);
        }
    }
    private void setupToggleSwitch(StackPane knob,Circle circle, Label labelOn, Label labelOff, Runnable toggleAction) {
        updateToggle(circle, labelOn, labelOff, true);
        knob.setOnMouseClicked(event -> toggleAction.run());
    }

    @FXML
    public void initialize() {
        setupToggleSwitch(knobContainer,toggleBall, on, off, this::toggleSwitch1);
        setupToggleSwitch(knobContainer1,toggleBall1, on1, off1, this::toggleSwitch2);
        setupToggleSwitch(knobContainer2,toggleBall2, on2, off2, this::toggleSwitch3);
        setupToggleSwitch(knobContainer3,toggleBall3, on3, off3, this::toggleSwitch4);
        setupToggleSwitch(knobContainer4,toggleBall4, on4, off4, this::toggleSwitch5);
        setupToggleSwitch(knobContainer5,toggleBall5, on5, off5, this::toggleSwitch6);
        setupToggleSwitch(knobContainer6,toggleBall6, on6, off6, this::toggleSwitch7);
        setupToggleSwitch(knobContainer7,toggleBall7, on7, off7, this::toggleSwitch8);
        setupToggleSwitch(knobContainer8,toggleBall8, on8, off8, this::toggleSwitch9);
    }

}
