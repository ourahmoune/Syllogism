package app;

import app.controller.MenuController;
import app.controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application {
    public static Scene scene;
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("vue/menu.fxml"), SettingController.language);
        scene = new Scene(fxmlLoader.load(), 1280, 720);

        if (fxmlLoader.getController() instanceof MenuController) {
            MenuController controller = fxmlLoader.getController();
            controller.resize();
        }

        StartApplication.stage = stage;
        StartApplication.stage.setTitle("Hello!");
        StartApplication.stage.setScene(scene);
        StartApplication.stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}