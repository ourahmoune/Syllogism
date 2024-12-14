package app.controller;

import app.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SettingsController {
    @FXML
    private StackPane knobContainer, knobContainer1, knobContainer2, knobContainer3, knobContainer4, knobContainer5, knobContainer6, knobContainer7, knobContainer8;
    @FXML
    private Label on, on1, on2, on3, on4, on5, on6, on7, on8;
    @FXML
    private Label off, off1, off2, off3, off4, off5, off6, off7, off8;
    @FXML
    private Circle toggleBall, toggleBall1, toggleBall2, toggleBall3, toggleBall4, toggleBall5, toggleBall6, toggleBall7, toggleBall8;

    private Rmt rmt = new Rmt();
    private Raa raa = new Raa();
    private Rii rii = new Rii();
    private Rlh rlh = new Rlh();
    private Rnn rnn = new Rnn();
    private Rn rn = new Rn();
    private Rp rp = new Rp();
    private Rpp rpp = new Rpp();
    private Ruu ruu = new Ruu();



    @FXML
    private void toggleSwitch1() {
        boolean isOn = Rules.getListRules().get(rmt);
        if (!isOn) {
            Rules.updateRule(rmt,true);
            System.out.println("Toggle Switch 1 is added" );

        } else {
            Rules.updateRule(rmt,false);
            System.out.println("Toggle Switch 1 is removed" );
        }
        updateToggle(toggleBall, on, off, Rules.getListRules().get(rmt));
        System.out.println("Toggle Switch 1 is " + (!isOn ? "ON" : "OFF"));

    }

    @FXML
    private void toggleSwitch2() {
        boolean isOn = Rules.getListRules().get(rlh);
        if (!isOn) {
            Rules.updateRule(rlh, true);
            System.out.println("Toggle Switch 2 is added");
        } else {
            Rules.updateRule(rlh, false);
            System.out.println("Toggle Switch 2 is removed");
        }
        updateToggle(toggleBall1, on1, off1, Rules.getListRules().get(rlh));
        System.out.println("Toggle Switch 2 is " + (!isOn ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch3() {
        boolean isOn = Rules.getListRules().get(rnn);
        if (!isOn) {
            Rules.updateRule(rnn, true);
            System.out.println("Toggle Switch 3 is added");
        } else {
            Rules.updateRule(rnn, false);
            System.out.println("Toggle Switch 3 is removed");
        }
        updateToggle(toggleBall2, on2, off2, Rules.getListRules().get(rnn));
        System.out.println("Toggle Switch 3 is " + (!isOn ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch4() {
        boolean isOn = Rules.getListRules().get(rn);
        if (!isOn) {
            Rules.updateRule(rn, true);
            System.out.println("Toggle Switch 4 is added");
        } else {
            Rules.updateRule(rn, false);
            System.out.println("Toggle Switch 4 is removed");
        }
        updateToggle(toggleBall3, on3, off3, Rules.getListRules().get(rn));
        System.out.println("Toggle Switch 4 is " + (!isOn ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch5() {
        boolean isOn = Rules.getListRules().get(raa);
        if (!isOn) {
            Rules.updateRule(raa, true);
            System.out.println("Toggle Switch 5 is added");
        } else {
            Rules.updateRule(raa, false);
            System.out.println("Toggle Switch 5 is removed");
        }
        updateToggle(toggleBall4, on4, off4, Rules.getListRules().get(raa));
        System.out.println("Toggle Switch 5 is " + (!isOn ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch6() {
        boolean isOn = Rules.getListRules().get(rpp);
        if (!isOn) {
            Rules.updateRule(rpp, true);
            System.out.println("Toggle Switch 6 is added");
        } else {
            Rules.updateRule(rpp, false);
            System.out.println("Toggle Switch 6 is removed");
        }
        updateToggle(toggleBall5, on5, off5, Rules.getListRules().get(rpp));
        System.out.println("Toggle Switch 6 is " + (!isOn ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch7() {
        boolean isOn = Rules.getListRules().get(rp);
        if (!isOn) {
            Rules.updateRule(rp, true);
            System.out.println("Toggle Switch 7 is added");
        } else {
            Rules.updateRule(rp, false);
            System.out.println("Toggle Switch 7 is removed");
        }
        updateToggle(toggleBall6, on6, off6, Rules.getListRules().get(rp));
        System.out.println("Toggle Switch 7 is " + (!isOn ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch8() {
        boolean isOn = Rules.getListRules().get(ruu);
        if (!isOn) {
            Rules.updateRule(ruu, true);
            System.out.println("Toggle Switch 8 is added");
        } else {
            Rules.updateRule(ruu, false);
            System.out.println("Toggle Switch 8 is removed");
        }
        updateToggle(toggleBall7, on7, off7, Rules.getListRules().get(ruu));
        System.out.println("Toggle Switch 8 is " + (!isOn ? "ON" : "OFF"));
    }

    @FXML
    private void toggleSwitch9() {
        boolean isOn = Rules.getListRules().get(rii);
        if (!isOn) {
            Rules.updateRule(rii, true);
            System.out.println("Toggle Switch 9 is added");
        } else {
            Rules.updateRule(rii, false);
            System.out.println("Toggle Switch 9 is removed");
        }
        updateToggle(toggleBall8, on8, off8, Rules.getListRules().get(rii));
        System.out.println("Toggle Switch 9 is " + (!isOn ? "ON" : "OFF"));
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

    private void setupToggleSwitch(StackPane knob, Circle circle, Label labelOn, Label labelOff, Runnable toggleAction,Rule rule) {
        boolean isOn = Rules.getListRules().getOrDefault(rule,true);
        updateToggle(circle, labelOn, labelOff, true);
        knob.setOnMouseClicked(event -> toggleAction.run());
    }
    private void updateUIFromRules() {
        if (Rules.getListRules() == null) {
            throw new IllegalStateException("Rules instance is not initialized.");
        }

        System.out.println("Updating UI from rules...");
        System.out.println("Current rules in list: " + Rules.getListRules());

        updateToggle(toggleBall, on, off, Rules.getListRules().get(rmt));
        updateToggle(toggleBall1, on1, off1, Rules.getListRules().get(rlh));
        updateToggle(toggleBall2, on2, off2, Rules.getListRules().get(rnn));
        updateToggle(toggleBall3, on3, off3, Rules.getListRules().get(rn));
        updateToggle(toggleBall4, on4, off4, Rules.getListRules().get(raa));
        updateToggle(toggleBall5, on5, off5, Rules.getListRules().get(rpp));
        updateToggle(toggleBall6, on6, off6, Rules.getListRules().get(rp));
        updateToggle(toggleBall7, on7, off7, Rules.getListRules().get(ruu));
        updateToggle(toggleBall8, on8, off8, Rules.getListRules().get(rii));
    }


    @FXML
    public void initialize() {
        System.out.println("Rules before setup: " + Rules.getListRules());
        if (Rules.getListRules().isEmpty()) {
            System.out.println("Rules map is empty. Initializing...");
            new Rules(); // This calls the constructor to set all rules to true
        }
        setupToggleSwitch(knobContainer, toggleBall, on, off, this::toggleSwitch1, rmt);
        setupToggleSwitch(knobContainer1, toggleBall1, on1, off1, this::toggleSwitch2, rlh);
        setupToggleSwitch(knobContainer2, toggleBall2, on2, off2, this::toggleSwitch3, rnn);
        setupToggleSwitch(knobContainer3, toggleBall3, on3, off3, this::toggleSwitch4, rn);
        setupToggleSwitch(knobContainer4, toggleBall4, on4, off4, this::toggleSwitch5, raa);
        setupToggleSwitch(knobContainer5, toggleBall5, on5, off5, this::toggleSwitch6, rpp);
        setupToggleSwitch(knobContainer6, toggleBall6, on6, off6, this::toggleSwitch7, rp);
        setupToggleSwitch(knobContainer7, toggleBall7, on7, off7, this::toggleSwitch8, ruu);
        setupToggleSwitch(knobContainer8, toggleBall8, on8, off8, this::toggleSwitch9, rii);

        // Update UI from current rules
        updateUIFromRules();
    }

}
