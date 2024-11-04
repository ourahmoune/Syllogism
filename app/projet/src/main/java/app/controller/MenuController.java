package app.controller;

import app.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

import static app.StartApplication.scene;

/**
 * MenuController manages the main menu interface of the application.
 * It handles navigation between different pages and the language settings.
 */
public class MenuController {
    @FXML
    public Label language; // Label to display the current language setting
    @FXML
    public Button GuidedPage; // Button to navigate to the guided interface
    @FXML
    public Button ArrayPage; // Button to navigate to the array interface
    @FXML
    public Button FreePage; // Button to navigate to the free interface
    @FXML
    public Button ListQuantifiersPage; // Button to navigate to the quantifiers list interface
    @FXML
    public StackPane ParamPage; // StackPane for parameters page
    @FXML
    public StackPane HelpPage; // StackPane for help page
    @FXML
    public Circle ParamPageCircle; // Circle indicator for parameters page
    @FXML
    public Circle HelpPageCircle; // Circle indicator for help page
    @FXML
    public Text HelpButton; // Text element for the help button
    @FXML
    Pane contentPane; // Pane to load different interfaces

    /**
     * Initializes the menu controller. This method can be used to set
     * up initial state if needed.
     */
    @FXML
    public void initialize() {
        //GuidedInterface();
    }

    /**
     * Changes the application language based on user preference.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
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
            FXMLLoader loader = new FXMLLoader(StartApplication.class.getResource(fxmlPath));
            Pane paneloaded = loader.load();
            paneloaded.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            contentPane.setStyle("-fx-border-color: yellow; -fx-border-width: 3;");
            paneloaded.prefWidthProperty().bind(contentPane.widthProperty());
            paneloaded.prefHeightProperty().bind(contentPane.heightProperty());
            // Replace the content of contentPane with the newly loaded content
            contentPane.getChildren().clear();
            contentPane.getChildren().add(paneloaded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
