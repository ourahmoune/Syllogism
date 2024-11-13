package app.controller;

import app.StartApplication;
import app.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static app.StartApplication.scene;

public class MenuController {
    @FXML
    public Label language;
    @FXML
    public Button GuidedPage;
    @FXML
    public Button ArrayPage;
    @FXML
    public Button FreePage;
    @FXML
    public Button ListQuantifiersPage;
    @FXML
    public StackPane ParamPage;
    @FXML
    public StackPane HelpPage;
    @FXML
    public Circle ParamPageCircle;
    @FXML
    public Circle HelpPageCircle;
    @FXML
    public Text HelpButton;
    @FXML
    Pane contentPane;


    @FXML
    public void initialize(){
        //GuidedInterface();
        Rules.getListRules().put(new Rmt(), true);
        Rules.getListRules().put(new Raa(), true);
        Rules.getListRules().put(new Rii(), true);
        Rules.getListRules().put(new Rlh(), true);
        Rules.getListRules().put(new Rnn(), true);
        Rules.getListRules().put(new Rn(), true);
        Rules.getListRules().put(new Rp(), true);
        Rules.getListRules().put(new Rpp(), true);
        Rules.getListRules().put(new Ruu(), true);
    }
    @FXML
    public void changeLanguage() throws IOException {
        SettingController.changeLanguage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("vue/menu.fxml"), SettingController.language);
        scene.setRoot(fxmlLoader.load());
        ((MenuController) fxmlLoader.getController()).resize();
    }
    private void resizeFontSize(){
        GuidedPage.setFont(Font.font(scene.widthProperty().getValue() / 56)); // 1.8% de la largeur de la fenêtre
        FreePage.setFont(Font.font(scene.widthProperty().getValue() / 56));
        ArrayPage.setFont(Font.font(scene.widthProperty().getValue() / 56));
        ListQuantifiersPage.setFont(Font.font(scene.widthProperty().getValue() / 56));
        language.setFont(Font.font(scene.widthProperty().getValue() / 56));
        HelpButton.setFont(Font.font(scene.widthProperty().getValue() / 50)); // 2% de la largeur de la fenêtre
    }
    private void resizeButtons() {
        GuidedPage.setMinWidth(scene.widthProperty().getValue() / 5); // 20% de la largeur de la fenêtre
        FreePage.setMinWidth(scene.widthProperty().getValue() / 5);
        ArrayPage.setMinWidth(scene.widthProperty().getValue() / 5);
        ListQuantifiersPage.setMinWidth(scene.widthProperty().getValue() / 5);
        language.setMinWidth(scene.widthProperty().getValue() / 9);

        ParamPageCircle.setRadius(scene.widthProperty().getValue() / 40); // rayon : 2.5% de la largeur de la fenêtre
        HelpPageCircle.setRadius(scene.widthProperty().getValue() / 40);

        GuidedPage.setMinHeight(scene.heightProperty().getValue() / 10); // 10% de la hauteur de la fenêtre
        FreePage.setMinHeight(scene.heightProperty().getValue() / 10);
        ArrayPage.setMinHeight(scene.heightProperty().getValue() / 10);
        ListQuantifiersPage.setMinHeight(scene.heightProperty().getValue() / 10);
        language.setMinHeight(scene.heightProperty().getValue() / 12);
    }
    public void resize(){
        resizeFontSize();
        resizeButtons();

        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            resizeButtons();
            resizeFontSize();
        });
        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            resizeButtons();;
        });
    }

    @FXML
    private void GuidedInterface(){
        loadInterface("vue/syllogism_guided.fxml");
    }

    @FXML
    private void UnguidedInterface(){ loadInterface("vue/syllogism_unguided.fxml");}

    @FXML
    private void ListInterface(){ loadInterface("vue/interface_list.fxml");}

    public void loadInterface(String fxmlPath){
        try {
            FXMLLoader loader = new FXMLLoader(StartApplication.class.getResource(fxmlPath));
            Pane paneloaded = loader.load();
            paneloaded.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            contentPane.setStyle("-fx-border-color: yellow; -fx-border-width: 3;");
            paneloaded.prefWidthProperty().bind(contentPane.widthProperty());
            paneloaded.prefHeightProperty().bind(contentPane.heightProperty());
            // Remplacer le contenu de contentPane par le nouveau contenu charge
            contentPane.getChildren().clear();
            contentPane.getChildren().add(paneloaded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSettings(){
        try {
            // Load the FXML file for the settings pop-up
            FXMLLoader loader = new FXMLLoader(StartApplication.class.getResource("vue/Settings.fxml"));
            Pane root = loader.load();

            SettingsController settingsController = loader.getController();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Settings");
            stage.setScene(new Scene(root));

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
