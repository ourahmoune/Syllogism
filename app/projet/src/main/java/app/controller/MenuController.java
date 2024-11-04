package app.controller;

import app.StartApplication;
import app.model.Quantificator;
import app.model.QuantificatorList;
import app.model.Quantity;
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
            FXMLLoader loader = new FXMLLoader(StartApplication.class.getResource(fxmlPath), SettingController.subMenu);
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
}
