package app.controller;

import app.StartApplication;
import app.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static app.StartApplication.scene;

/**
 * MenuController manages the main menu interface of the application.
 * It handles navigation between different pages and the language settings.
 */
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

        if (SettingController.getLanguage().equals("english")){
            QuantificatorList.getInstance().getQuantificators().clear();
            //Quantificator par défault universel
            TranslateQuantificator("All", Quantity.Universal);
            TranslateQuantificator("Every", Quantity.Universal);
            TranslateQuantificator("No", Quantity.Universal);
            TranslateQuantificator("None", Quantity.Universal);

            //Quantificator par défault existentiel
            TranslateQuantificator("Some", Quantity.Exisential);
            TranslateQuantificator("Most", Quantity.Exisential);
            TranslateQuantificator("Many", Quantity.Exisential);
            TranslateQuantificator("Few", Quantity.Exisential);
            TranslateQuantificator("Several", Quantity.Exisential);
            TranslateQuantificator("Any", Quantity.Exisential);
        }else{
            QuantificatorList.getInstance().getQuantificators().clear();
            //Quantificator par défault universel
            TranslateQuantificator("Tous/Tout/Toute/Toutes", Quantity.Universal);
            TranslateQuantificator("Chaque", Quantity.Universal);
            TranslateQuantificator("Aucun/Aucune", Quantity.Universal);

            //Quantificator par défault existentiel
            TranslateQuantificator("Certains/Certaines", Quantity.Exisential);
            TranslateQuantificator("La plupart", Quantity.Exisential);
            TranslateQuantificator("Beaucoup", Quantity.Exisential);
            TranslateQuantificator("Peu", Quantity.Exisential);
            TranslateQuantificator("Plusieurs", Quantity.Exisential);
            TranslateQuantificator("N'importe quel", Quantity.Exisential);
        }
    }

    private void TranslateQuantificator(String word, Quantity quantity) {
        Quantificator quantificator = new Quantificator(quantity, word);
        QuantificatorList.getInstance().addQuantificator(quantificator);
    }


    @FXML
    public void changeLanguage() throws IOException {
        SettingController.changeLanguage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("vue/menu.fxml"), SettingController.language);
        scene.setRoot(fxmlLoader.load());
        ((MenuController) fxmlLoader.getController()).resize();
    }

    /**
     * Resizes font sizes of buttons and labels based on the current window width.
     */
    private void resizeFontSize() {
        GuidedPage.setFont(Font.font(scene.widthProperty().getValue() / 56)); // 1.8% of window width
        FreePage.setFont(Font.font(scene.widthProperty().getValue() / 56));
        ArrayPage.setFont(Font.font(scene.widthProperty().getValue() / 56));
        ListQuantifiersPage.setFont(Font.font(scene.widthProperty().getValue() / 56));
        language.setFont(Font.font(scene.widthProperty().getValue() / 56));
        HelpButton.setFont(Font.font(scene.widthProperty().getValue() / 50)); // 2% of window width
    }

    /**
     * Resizes buttons and circles based on the current window dimensions.
     */
    private void resizeButtons() {
        GuidedPage.setMinWidth(scene.widthProperty().getValue() / 5); // 20% of window width
        FreePage.setMinWidth(scene.widthProperty().getValue() / 5);
        ArrayPage.setMinWidth(scene.widthProperty().getValue() / 5);
        ListQuantifiersPage.setMinWidth(scene.widthProperty().getValue() / 5);
        language.setMinWidth(scene.widthProperty().getValue() / 9);

        ParamPageCircle.setRadius(scene.widthProperty().getValue() / 40); // 2.5% of window width
        HelpPageCircle.setRadius(scene.widthProperty().getValue() / 40);

        GuidedPage.setMinHeight(scene.heightProperty().getValue() / 10); // 10% of window height
        FreePage.setMinHeight(scene.heightProperty().getValue() / 10);
        ArrayPage.setMinHeight(scene.heightProperty().getValue() / 10);
        ListQuantifiersPage.setMinHeight(scene.heightProperty().getValue() / 10);
        language.setMinHeight(scene.heightProperty().getValue() / 12);
    }

    /**
     * Resizes font sizes and buttons whenever the scene dimensions change.
     */
    public void resize() {
        resizeFontSize();
        resizeButtons();

        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            resizeButtons();
            resizeFontSize();
        });
        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            resizeButtons();
        });

    }

    /**
     * Loads the guided interface.
     */
    @FXML
    private void GuidedInterface() {
        loadInterface("vue/syllogism_guided.fxml");
    }

    /**
     * Loads the unguided interface.
     */
    @FXML
    private void UnguidedInterface() {
        loadInterface("vue/syllogism_unguided.fxml");
    }

    /**
     * Loads the list interface.
     */
    @FXML
    private void ListInterface() {
        loadInterface("vue/interface_list.fxml");
    }

    /**
     * Loads a specified interface from an FXML file into the content pane.
     *
     * @param fxmlPath the path to the FXML file to load
     */
    public void loadInterface(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(StartApplication.class.getResource(fxmlPath), SettingController.subMenu);
            Pane paneloaded = loader.load();
            paneloaded.prefWidthProperty().bind(contentPane.widthProperty());
            paneloaded.prefHeightProperty().bind(contentPane.heightProperty());
            // Replace the content of contentPane with the newly loaded content
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
            FXMLLoader loader = new FXMLLoader(StartApplication.class.getResource("vue/Settings.fxml"), SettingController.subMenu);
            Pane root = loader.load();

            SettingsController settingsController = loader.getController();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
