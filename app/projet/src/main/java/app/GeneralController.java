package app;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneralController {
    @FXML
    Button Guided, Free, Array, Quantifiers;
    @FXML
    Pane contentPane;

    public void initialize(){}

    @FXML
    private void GuidedInterface(){
        loadInterface("syllogism_guided.fxml");
    }

    @FXML
    private void UnguidedInterface(){ loadInterface("syllogism_unguided.fxml");}

    @FXML
    private void ListInterface(){ loadInterface("interface_list.fxml");}

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
    @FXML
    private void handleSettings(){
        try {
            // Load the FXML file for the settings pop-up
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/Settings.fxml"));
            Pane root = loader.load();

            // Create a new Stage for the pop-up
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL); // Block interactions with other windows
            stage.setTitle("Settings");
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Display the pop-up and wait for it to close
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}