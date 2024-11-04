package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class SettingsController {
    @FXML
    private StackPane knobContainer,knobContainer11,knobContainer111, knobContainer1111,knobContainer1, knobContainer11111,knobContainer111111,knobContainer1111111,knobContainer2;
    @FXML
    private Label on,on1, on11, on111,on1111,on11111,on111111,on1111111,on2;
    @FXML
    private Label off, off1, off11, off111, off1111, off11111, off111111, off1111111, off2;
    @FXML
    private Circle toggleBall,toggleBall1,toggleBall2,toggleBall3,toggleBall4,toggleBall5,toggleBall6,toggleBall7,toggleBall8;

    private boolean isOn = false;
    private boolean isOn1 = false;
    private boolean isOn11 = false;
    private boolean isOn111 = false;
    private boolean isOn1111 = false;
    private boolean isOn11111 = false;
    private boolean isOn111111 = false;
    private boolean isOn1111111 = false;
    private boolean isOn2=false;



    @FXML
    private void toggleSwitch1() {
        isOn = !isOn;
        updateToggle(toggleBall, on, off, isOn);
        System.out.println("Toggle Switch 1 is " + (isOn1 ? "ON" : "OFF"));
    }
    @FXML
    private void toggleSwitch2() {
        isOn2 = !isOn2;
        updateToggle(toggleBall1, on1, off1, isOn2);
        System.out.println("Toggle Switch 2 is " + (isOn1 ? "ON" : "OFF"));
    }
    @FXML
    private void toggleSwitch3() {
        isOn11 = !isOn11;
        updateToggle(toggleBall2, on11, off11, isOn11);
        System.out.println("Toggle Switch 3 is " + (isOn11 ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch4() {
        isOn111 = !isOn111;
        updateToggle(toggleBall3, on111, off111, isOn111);
        System.out.println("Toggle Switch 4 is " + (isOn111 ? "ON" : "OFF"));
    }
    @FXML
    private void toggleSwitch5() {
        isOn1111 = !isOn1111;
        updateToggle(toggleBall4, on1111, off1111, isOn1111);
        System.out.println("Toggle Switch 5 is " + (isOn1111 ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch6() {
        isOn11111 = !isOn11111;
        updateToggle(toggleBall5, on11111, off11111, isOn11111);
        System.out.println("Toggle Switch 6 is " + (isOn11111 ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch7() {
        isOn111111 = !isOn111111;
        updateToggle(toggleBall6, on111111, off111111, isOn111111);
        System.out.println("Toggle Switch 7 is " + (isOn111111 ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch8() {
        isOn1111111 = !isOn1111111;
        updateToggle(toggleBall7, on1111111, off1111111, isOn1111111);
        System.out.println("Toggle Switch 8 is " + (isOn1111111 ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch9() {
        isOn2 = !isOn2;
        updateToggle(toggleBall8, on2, off2, isOn2);
        System.out.println("Toggle Switch 9 is " + (isOn2 ? "ON" : "OFF"));
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
        updateToggle(circle, labelOn, labelOff, false);
        knob.setOnMouseClicked(event -> toggleAction.run());
    }

    @FXML
    public void initialize() {
        setupToggleSwitch(knobContainer,toggleBall, on, off, this::toggleSwitch1);
        setupToggleSwitch(knobContainer1,toggleBall1, on1, off1, this::toggleSwitch2);
        setupToggleSwitch(knobContainer11,toggleBall2, on11, off11, this::toggleSwitch3);
        setupToggleSwitch(knobContainer111,toggleBall3, on111, off111, this::toggleSwitch4);
        setupToggleSwitch(knobContainer1111,toggleBall4, on1111, off1111, this::toggleSwitch5);
        setupToggleSwitch(knobContainer11111,toggleBall5, on11111, off11111, this::toggleSwitch6);
        setupToggleSwitch(knobContainer111111,toggleBall6, on111111, off111111, this::toggleSwitch7);
        setupToggleSwitch(knobContainer1111111,toggleBall7, on1111111, off1111111, this::toggleSwitch8);
        setupToggleSwitch(knobContainer2,toggleBall8, on2, off2, this::toggleSwitch9);
    }

}
