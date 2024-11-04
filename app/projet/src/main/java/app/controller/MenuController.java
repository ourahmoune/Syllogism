package app.controller;

import app.StartApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    public VBox left;
    @FXML
    Pane contentPane;

    @FXML
    public void initialize(){
        //GuidedInterface();

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
            paneloaded.prefWidthProperty().bind(contentPane.widthProperty());
            paneloaded.prefHeightProperty().bind(contentPane.heightProperty());
            // Remplacer le contenu de contentPane par le nouveau contenu charge
            contentPane.getChildren().clear();
            contentPane.getChildren().add(paneloaded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
