package app.controller;

import app.StartApplication;
import app.model.*;
import app.model.polysyllogismes.Polysyllogisme;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static app.StartApplication.scene;

public class UnGuidedController implements Resize{

    @FXML
    public TextField P3_1, P3_2, V3;
    public Circle threeplus, threeminus, addPremice, removePremice;
    public Label label_conclusion, threeplusLabel, threeminusLabel, addPremiceLabel, removePremiceLabel;
    public Button validate, clear;
    public Map<Integer,String> ql;
    public Pane resultSyllogism;

    @FXML
    VBox vboxPremice;

    @FXML
    ComboBox<String> Q3;

    @FXML
    public void initialize() {
        ql = new HashMap<>();
        createPremice();
        createPremice();
        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            Q3.getItems().add(quantificator.getName());
        }
    }

    public void createPremice(){
        // Création de l'HBox
        HBox hbox = new HBox();
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        VBox.setVgrow(hbox, javafx.scene.layout.Priority.ALWAYS);
        hbox.setSpacing(10);

        // Ajout du ComboBox
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPrefWidth(150);
        comboBox.setMaxWidth(Double.MAX_VALUE);
        comboBox.getStyleClass().add("quantifier");
        HBox.setHgrow(comboBox, javafx.scene.layout.Priority.ALWAYS);

        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            comboBox.getItems().add(quantificator.getName());
        }

        // Ajout des TextField avec les styles
        TextField textField1 = new TextField();
        HBox.setHgrow(textField1, javafx.scene.layout.Priority.ALWAYS);

        TextField textField2 = new TextField();
        HBox.setHgrow(textField2, javafx.scene.layout.Priority.ALWAYS);

        TextField textField3 = new TextField();
        HBox.setHgrow(textField3, javafx.scene.layout.Priority.ALWAYS);
        // Bouton "+"
        StackPane stackPanePlus = new StackPane();
        HBox.setHgrow(stackPanePlus, javafx.scene.layout.Priority.ALWAYS);
        Circle circlePlus = new Circle();
        circlePlus.getStyleClass().add("qualityCircle");
        HBox.setHgrow(circlePlus, javafx.scene.layout.Priority.ALWAYS);
        stackPanePlus.getChildren().add(circlePlus);

        Label labelPlus = new Label("+");
        labelPlus.getStyleClass().add("qualityLabel");
        HBox.setHgrow(labelPlus, javafx.scene.layout.Priority.ALWAYS);
        stackPanePlus.getChildren().add(labelPlus);

        // Bouton "-"
        StackPane stackPaneMinus = new StackPane();
        HBox.setHgrow(stackPaneMinus, javafx.scene.layout.Priority.ALWAYS);
        Circle circleMinus = new Circle();
        circleMinus.getStyleClass().add("qualityCircle");
        HBox.setHgrow(circleMinus, javafx.scene.layout.Priority.ALWAYS);
        stackPaneMinus.getChildren().add(circleMinus);

        Label labelMinus = new Label("-");
        labelMinus.getStyleClass().add("qualityLabel");
        HBox.setHgrow(labelMinus, javafx.scene.layout.Priority.ALWAYS);
        stackPaneMinus.getChildren().add(labelMinus);

        int pos =  vboxPremice.getChildren().size()+1;
        labelPlus.setOnMouseClicked(event -> handleButtonSelection(labelPlus, labelMinus, pos));
        labelMinus.setOnMouseClicked(event -> handleButtonSelection(labelMinus, labelPlus, pos));

        if (SettingController.getLanguage().equals("english")){
            comboBox.setPromptText("quantity");
            textField2.setPromptText("verb");
        }else{
            comboBox.setPromptText("quantité");
            textField2.setPromptText("verbe");
        }
        // Ajout de tous les éléments à l'HBox
        hbox.getChildren().addAll(comboBox, textField1, textField2, textField3, stackPanePlus, stackPaneMinus);

        // Ajout de l'HBox au conteneur VBox dans le ScrollPane
        vboxPremice.getChildren().add(hbox);
        resize();
    }

    @FXML
    public void addPremice(MouseEvent actionEvent) {
        createPremice();
    }
    @FXML
    public void removePremice(MouseEvent actionEvent) {
        if (vboxPremice.getChildren().size() != 2) {
            vboxPremice.getChildren().remove(vboxPremice.getChildren().size() - 1);
        }
    }
    @FXML
    private void affirmatif3() {
        handleButtonSelection(threeplusLabel, threeminusLabel, 0);
    }
    @FXML
    private void negatif3() {
        handleButtonSelection(threeminusLabel, threeplusLabel,0);
    }
    private void handleButtonSelection(Label selectedButton, Label otherButton, int proposition) {
        // Applique la couleur verte au bouton sélectionné
        selectedButton.setStyle("-fx-text-fill: black");

        // Réinitialise le style de l'autre bouton à la couleur grise
        otherButton.setStyle("-fx-text-fill: #9dff8c");

        if (selectedButton.getText().equals("+")){
           ql.put(proposition, "Affirmative");
        }else{
            ql.put(proposition, "Negative");
        }
    }

    @FXML
    public void validate() {
        validate.setStyle("-fx-background-color: #9dff8c");
        Map<Integer, Proposition> resultat = new HashMap<>();
        int taille = 0;
        for (Node node : vboxPremice.getChildren()) {
            if (node instanceof HBox) {
                HBox hbox = (HBox) node;
                taille++;

                // Variables pour stocker les valeurs récupérées
                Quantificator quantificator = null;
                String subject = null;
                String predicat = null;
                Quality quality = null;

                // Compteur pour identifier les TextField
                int textFieldCounter = 0;

                // Itérer sur les enfants de l'HBox pour récupérer les valeurs
                for (Node hboxChild : hbox.getChildren()) {
                    if (hboxChild instanceof ComboBox) {
                        ComboBox<String> comboBox = (ComboBox<String>) hboxChild;
                        if (comboBox.getValue() != null) {
                            quantificator = QuantificatorList.getInstance().getQuantificator(comboBox.getSelectionModel().getSelectedItem());
                        }
                    } else if (hboxChild instanceof TextField) {
                        textFieldCounter++;  // Incrémente le compteur de TextField

                        if (textFieldCounter == 1) {
                            subject = ((TextField) hboxChild).getText();
                        } else if (textFieldCounter == 3) {
                            predicat = ((TextField) hboxChild).getText();
                        }
                    } else if (hboxChild instanceof StackPane && ql.containsKey(taille)) {
                        quality = Quality.valueOf(ql.get(taille));
                    }
                }

                // Création d'une Proposition et ajout à la Map si tous les champs sont présents
                if (quantificator != null && subject != null && predicat != null && quality != null) {
                    Proposition proposition = new Proposition(quantificator, subject, predicat, quality);
                    resultat.put(taille, proposition);  // Utilise l'index comme clé, puis l'incrémente

                    // Afficher ou utiliser les valeurs récupérées pour chaque HBox
                    afficherDonneesDeMap(resultat);
                }else{
                    resultat.put(taille, null);
                }
            }
        }
        try {
            Quantificator quantificator3 = QuantificatorList.getInstance().getQuantificator(Q3.getSelectionModel().getSelectedItem());

            String sujet3 = P3_1.getText();
            String predicat3 = P3_2.getText();

            Proposition p3 = new Proposition(quantificator3, sujet3, predicat3, Quality.valueOf(ql.get(0)));
            resultat.put(resultat.size() + 1, p3);

            System.out.println(resultat.size());
            Polysyllogisme poly = new Polysyllogisme(resultat, resultat.size());
            poly.Reordonne();
            if (poly.solve()) {
                Image gif = new Image(StartApplication.class.getResource("/app/image/feu_artifice.gif").toExternalForm());
                ImageView img = new ImageView(gif);
                img.setFitHeight(resultSyllogism.getHeight());
                img.setFitWidth(resultSyllogism.getWidth());
                resultSyllogism.getChildren().add(img);
                Timer timer = new Timer();
                // Planifie l'exécution de la tâche après 3 secondes
                timer.schedule(new TimerTask() {
                    @Override
                    @FXML
                    public void run() {
                        Platform.runLater(() -> resultSyllogism.getChildren().clear());
                    }
                }, 1500);
            } else {
                sylloInvalideShowRules(poly);
            }

        }catch (NullPointerException e){
            HBox pane = new HBox();
            pane.setMinHeight(resultSyllogism.getHeight());
            pane.setMinWidth(resultSyllogism.getWidth());
            pane.setOnMouseClicked(event -> {
                resultSyllogism.getChildren().clear();
                resultSyllogism.setMouseTransparent(true);
            });
            VBox v1 = new VBox();
            Region r = new Region();
            VBox.setVgrow(r, javafx.scene.layout.Priority.ALWAYS);
            HBox h = new HBox();
            Region r11= new Region();
            HBox.setHgrow(r11, javafx.scene.layout.Priority.ALWAYS);
            Region r12 = new Region();
            HBox.setHgrow(r12, javafx.scene.layout.Priority.ALWAYS);
            Label title = new Label();
            title.setStyle("-fx-background-color: #9dff8c; -fx-padding: 50; -fx-border-radius: 15 15 15 15; -fx-background-radius: 15 15 15 15;");
            if (SettingController.getLanguage().equals("english")) {
                title.setText("Shape not valid");
            } else {
                title.setText("forme invalide");
            }
            title.setTextAlignment(TextAlignment.CENTER);
            title.setFont(Font.font(scene.widthProperty().getValue() / 30));
            Region r2 = new Region();
            VBox.setVgrow(r2, javafx.scene.layout.Priority.ALWAYS);

            h.getChildren().addAll(r11, title, r12);
            v1.getChildren().addAll(r, h, r2);
            pane.getChildren().add(v1);
            resultSyllogism.setMouseTransparent(false);
            resultSyllogism.getChildren().add(pane);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            validate.setStyle("-fx-background-color: red");
        }
    }

    public void afficherDonneesDeMap(Map<Integer, Proposition> map) {
        // Itérer sur les entrées de la Map
        for (Map.Entry<Integer, Proposition> entry : map.entrySet()) {
            Integer index = entry.getKey();  // Clé de la Map
            Proposition proposition = entry.getValue();  // Valeur associée à la clé

            // Afficher les informations contenues dans la Proposition
            System.out.println("Index: " + index);
            System.out.println("Quantificateur: " + proposition.getQuantificator().getName());
            System.out.println("Sujet: " + proposition.getSubject());
            System.out.println("Prédicat: " + proposition.getPredicat());
            System.out.println("Qualité: " + proposition.getQuality().name());
            System.out.println("Type: " + proposition.getType());
            System.out.println("----------------------------");
        }
    }

    public void sylloInvalideShowRules(Polysyllogisme poly) {
        HBox pane = new HBox();
        pane.setMinHeight(resultSyllogism.getHeight());
        pane.setMinWidth(resultSyllogism.getWidth());
        VBox v1 = new VBox();
        Region r = new Region();
        VBox.setVgrow(r, javafx.scene.layout.Priority.ALWAYS);
        v1.getChildren().add(r);
        pane.setOnMouseClicked(event -> {
            resultSyllogism.getChildren().clear();
            resultSyllogism.setMouseTransparent(true);
        });
        pane.setAlignment(Pos.CENTER);
        VBox v2 = new VBox();
        v2.setStyle("-fx-background-color: #9dff8c; -fx-padding: 50; -fx-border-radius: 15 15 15 15; -fx-background-radius: 15 15 15 15;");
        Label title = new Label();
        if (SettingController.getLanguage().equals("english")) {
            title.setText("Rules not valid");
        } else {
            title.setText("Regle invalide");
        }
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font(scene.widthProperty().getValue() / 30));
        v2.getChildren().add(title);
        for (Map.Entry<String, String> rule : poly.getNotValidRule().entrySet()) {

            HBox hbox = new HBox();
            Label labelName = new Label(rule.getKey() + ": ");
            HBox.setHgrow(labelName, javafx.scene.layout.Priority.ALWAYS);
            labelName.setFont(Font.font(scene.widthProperty().getValue() / 40));
            labelName.setStyle("-fx-font-weight: bold");

            Label labelDescription = new Label(rule.getValue());
            HBox.setHgrow(labelDescription, javafx.scene.layout.Priority.ALWAYS);
            labelDescription.setFont(Font.font(scene.widthProperty().getValue() / 40));

            hbox.getChildren().addAll(labelName, labelDescription);
            v2.getChildren().add(hbox);
        }
        ScrollPane scrollPane = new ScrollPane(v2);
        scrollPane.setStyle("-fx-background-color: #9dff8c");
        scrollPane.setMaxWidth(vboxPremice.getWidth());
        scrollPane.setMouseTransparent(false);
        v1.getChildren().add(scrollPane);
        Region r2 = new Region();
        VBox.setVgrow(r2, javafx.scene.layout.Priority.ALWAYS);
        v1.getChildren().add(r2);
        pane.getChildren().add(v1);
        resultSyllogism.setMouseTransparent(false);
        resultSyllogism.getChildren().add(pane);
    }
    /**
     * Resizes font sizes of buttons and labels based on the current window width.
     */
    private void resizeFontSize() {
        threeplusLabel.setFont(Font.font(scene.widthProperty().getValue() / 30));
        threeminusLabel.setFont(Font.font(scene.widthProperty().getValue() / 30));
        addPremiceLabel.setFont(Font.font(scene.widthProperty().getValue() / 30));
        removePremiceLabel.setFont(Font.font(scene.widthProperty().getValue() / 30));
        String style = "-fx-font-size :"+ scene.widthProperty().getValue() / 70;
        P3_1.setFont(Font.font(scene.widthProperty().getValue() / 50));
        P3_2.setFont(Font.font(scene.widthProperty().getValue() / 50));
        V3.setFont(Font.font(scene.widthProperty().getValue() / 50));
        validate.setFont(Font.font(scene.widthProperty().getValue() / 50));
        clear.setFont(Font.font(scene.widthProperty().getValue() / 50));
        label_conclusion.setFont(Font.font(scene.widthProperty().getValue() / 50));
        Q3.setStyle(style);
        for (Node node : vboxPremice.getChildren()) {
            for (Node hboxChild : ((HBox)node).getChildren()) {
                if (hboxChild instanceof ComboBox) {
                    hboxChild.setStyle(style);
                }
                if (hboxChild instanceof StackPane) {
                    ((Label)((StackPane) hboxChild).getChildren().get(1)).setFont(Font.font(scene.widthProperty().getValue() / 30));
                }
                if (hboxChild instanceof TextField) {
                    ((TextField)hboxChild).setFont(Font.font(scene.widthProperty().getValue() / 50));
                }
            }
        }
    }

    private void resizeButtons() {
        P3_1.setMinWidth(scene.widthProperty().getValue() / 6);
        P3_2.setMinWidth(scene.widthProperty().getValue() / 6);
        V3.setMinWidth(scene.widthProperty().getValue() / 7);
        Q3.setMinWidth(scene.widthProperty().getValue() / 6);
        validate.setMinWidth(scene.widthProperty().getValue() / 6);
        clear.setMinWidth(scene.widthProperty().getValue() / 6);
        for (Node node : vboxPremice.getChildren()) {
            for (Node hboxChild : ((HBox)node).getChildren()) {
                if (hboxChild instanceof ComboBox || hboxChild instanceof TextField) {
                    ((Control)hboxChild).setMinWidth(scene.widthProperty().getValue() / 6);
                }
                if (hboxChild instanceof StackPane) {
                    ((Circle)((StackPane) hboxChild).getChildren().get(0)).setRadius(scene.widthProperty().getValue() / 60);
                }
            }
        }


        P3_1.setMinHeight(scene.heightProperty().getValue() / 10);
        P3_2.setMinHeight(scene.heightProperty().getValue() / 10);
        V3.setMinHeight(scene.heightProperty().getValue() / 10);
        Q3.setMinHeight(scene.heightProperty().getValue() / 10);
        for (Node node : vboxPremice.getChildren()) {
            for (Node hboxChild : ((HBox)node).getChildren()) {
                if (hboxChild instanceof ComboBox || hboxChild instanceof TextField) {
                    ((Control)hboxChild).setMinHeight(scene.heightProperty().getValue() / 10);
                }
            }
        }

        addPremice.setRadius(scene.widthProperty().getValue() / 60);
        removePremice.setRadius(scene.widthProperty().getValue() / 60);
        threeminus.setRadius(scene.widthProperty().getValue() / 60);
        threeplus.setRadius(scene.widthProperty().getValue() / 60);

    }
    @Override
    public void resize() {
        resizeButtons();
        resizeFontSize();

        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            resizeButtons();
            resizeFontSize();
        });
        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            resizeButtons();
        });
    }

    private void resetValidate() {
        P3_1.setOnKeyTyped(event -> validate.setStyle("-fx-background-color: #9dff8c"));
        P3_2.setOnKeyTyped(event -> validate.setStyle("-fx-background-color: #9dff8c"));
        Q3.setOnAction(event -> validate.setStyle("-fx-background-color: #9dff8c"));
    }

    public void clear(ActionEvent actionEvent) {
        vboxPremice.getChildren().clear();
        createPremice();
        createPremice();
        P3_1.clear();
        P3_2.clear();
        V3.clear();
        ql.clear();
        addPremiceLabel.setStyle("-fx-text-fill: #9dff8c");
        removePremiceLabel.setStyle("-fx-text-fill: #9dff8c");
        Q3.getSelectionModel().clearSelection();
    }

}
