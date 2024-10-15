package app;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.IOException;

public class HelloController {
    @FXML
    Button Guided, Free, Array, Quantifiers;
    @FXML
    Pane contentPane;

    public void initialize(){}

    @FXML
    private void GuidedInterface(){
        loadInterface("interface_list.fxml");
    }

    public void loadInterface(String fxmlPath){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Pane paneloaded = loader.load();
            paneloaded.setStyle("-fx-border-color: black; -fx-border-width: 3;");
            contentPane.setStyle("-fx-border-color: yellow; -fx-border-width: 3;");
            paneloaded.prefWidthProperty().bind(contentPane.widthProperty());
            paneloaded.prefHeightProperty().bind(contentPane.heightProperty());
            // Remplacer le contenu de contentPane par le nouveau contenu chargÃ©
            contentPane.getChildren().clear();
            contentPane.getChildren().add(paneloaded);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}