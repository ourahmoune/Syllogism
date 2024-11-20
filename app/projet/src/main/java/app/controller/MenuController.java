package app.controller;

import app.StartApplication;
import app.model.Quantificator;
import app.model.QuantificatorList;
import app.model.Quantity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    @FXML
    Pane HelpPane; //Pane for the load of the help fxml
    @FXML
    StackPane stackroot;

    /**
     * Initializes the menu controller. This method can be used to set
     * up initial state if needed.
     */
    @FXML
    public void initialize() {
        //GuidedInterface();

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
        loadInterface(contentPane, "vue/syllogism_guided.fxml");
    }

    /**
     * Loads the unguided interface.
     */
    @FXML
    private void UnguidedInterface() {
        loadInterface(contentPane, "vue/syllogism_unguided.fxml");
    }

    /**
     * Loads the list interface.
     */
    @FXML
    private void ListInterface() {
        loadInterface(contentPane, "vue/interface_list.fxml");
    }

    @FXML
    private void HelpInterface() { loadInterface(stackroot, "vue/interface_help.fxml"); }
    /**
     * Loads a specified interface from an FXML file into the content pane.
     *
     * @param fxmlPath the path to the FXML file to load
     */
    public void loadInterface(Pane target, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(StartApplication.class.getResource(fxmlPath), SettingController.subMenu);
            Pane paneloaded = loader.load();
            paneloaded.prefWidthProperty().bind(target.widthProperty());
            paneloaded.prefHeightProperty().bind(target.heightProperty());
            // Replace the content of contentPane with the newly loaded content
            target.getChildren().clear();
            target.getChildren().add(paneloaded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadInterface(StackPane target, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(StartApplication.class.getResource(fxmlPath), SettingController.language);
            Pane paneloaded = loader.load();

            HelpButtonController helpController = loader.getController();
            helpController.setMainController(this);

            target.getChildren().add(paneloaded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
