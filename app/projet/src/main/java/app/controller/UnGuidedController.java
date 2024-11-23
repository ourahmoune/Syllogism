package app.controller;

import app.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;

import static app.StartApplication.scene;

public class UnGuidedController implements Resize{

    @FXML
    public TextField P3_1, P3_2, V3;
    public Circle threeplus, threeminus, addPremice;
    public Label label_conclusion, threeplusLabel, threeminusLabel, addPremiceLabel;
    public Button validate, clear;

    @FXML
    VBox vboxPremice;

    @FXML
    ComboBox<String> Q3;

    @FXML
    public void initialize() {

        createPremice();
        createPremice();

    }

    public void createPremice(){
        // Création de l'HBox
        HBox hbox = new HBox();
        hbox.setAlignment(javafx.geometry.Pos.CENTER);
        hbox.setSpacing(10);

        // Ajout du ComboBox
        ComboBox<Quantificator> comboBox = new ComboBox<>();
        //setComboBoxQuanti(comboBox);
        comboBox.getItems().addAll(QuantificatorList.getInstance().getQuantificators());
        comboBox.setPrefWidth(150);
        comboBox.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(comboBox, javafx.scene.layout.Priority.ALWAYS);

        // Personnaliser l'affichage de la ComboBox avec un StringConverter
        comboBox.setConverter(new StringConverter<Quantificator>() {

            @Override
            public String toString(Quantificator quantificator) {
                return quantificator != null ? quantificator.getName() : "";
            }

            @Override
            public Quantificator fromString(String string) {
                // Ici vous pouvez gérer la conversion inverse si nécessaire
                // Par exemple, vous pouvez chercher un Quantificator avec ce nom
                for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
                    if (quantificator.getName().equals(string)) {
                        return quantificator;
                    }
                }
                return null;  // Si pas trouvé
            }
        });

        // Gestion de l'événement de sélection dans la ComboBox
        comboBox.setOnAction(event -> {
            Quantificator selectedQuantificator = comboBox.getValue();  // Récupérer l'objet Quantificator
            if (selectedQuantificator != null) {
                // Récupérer le nom du quantificateur
                System.out.println("ComboBox Value: " + selectedQuantificator.getName());  // Affiche le nom
                // Vous pouvez aussi récupérer d'autres propriétés si nécessaire
                Quantity quantity = selectedQuantificator.getQuantity();  // Exemple d'accès à Quantity
                System.out.println("Quantity Value: " + quantity);
            }
        });

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

        //isAddButtonSelected.setOnAction(event -> handleButtonSelection(isAddButtonSelected, isMinusButtonSelected));
        //isMinusButtonSelected.setOnAction(event -> handleButtonSelection(isMinusButtonSelected, isAddButtonSelected));

        // Attachez les propriétés de sélection aux boutons via l'HBox
        hbox.getProperties().put("isAddButtonSelected", stackPanePlus);
        hbox.getProperties().put("isMinusButtonSelected", labelMinus);

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

    private void handleButtonSelection(Button selectedButton, Button otherButton) {
        // Applique la couleur verte au bouton sélectionné
        selectedButton.setStyle("-fx-background-radius: 15; -fx-min-width: 30; -fx-min-height: 30; " +
                "-fx-max-height: 30; -fx-max-width: 30; -fx-font-size: 20; -fx-text-fill: #32b71b; " +
                "-fx-background-color: #37ff00; -fx-alignment: center; -fx-padding: 0;");

        // Réinitialise le style de l'autre bouton à la couleur grise
        otherButton.setStyle("-fx-background-radius: 15; -fx-min-width: 30; -fx-min-height: 30; " +
                "-fx-max-height: 30; -fx-max-width: 30; -fx-font-size: 20; -fx-text-fill: #32b71b; " +
                "-fx-background-color: lightgrey; -fx-alignment: center; -fx-padding: 0;");
    }

    private void setComboBoxQuanti(ComboBox<String> comboBox) {
        for (Quantificator quantificator : QuantificatorList.getInstance().getQuantificators()) {
            comboBox.getItems().add(quantificator.getName());
        }
    }

    @FXML
    public void iterateOverVBox() {
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
                        ComboBox<?> comboBox = (ComboBox<?>) hboxChild;
                        if (comboBox.getValue() != null) {
                            quantificator = new Quantificator(Quantity.Universal, comboBox.getValue().toString());
                        }
                    } else if (hboxChild instanceof TextField) {
                        textFieldCounter++;  // Incrémente le compteur de TextField

                        if (textFieldCounter == 1) {
                            subject = ((TextField) hboxChild).getText();
                        } else if (textFieldCounter == 3) {
                            predicat = ((TextField) hboxChild).getText();
                        }
                    } else if (hboxChild instanceof Button) {
                        Button button = (Button) hboxChild;

                        // Vérifier la qualité en fonction du bouton sélectionné
                        if (button.getText().equals("+") && button.getStyle().contains("background-color: #37ff00")) {
                            quality = Quality.Affirmative;  // Exemple de valeur pour une qualité affirmative
                        } else if (button.getText().equals("—") && button.getStyle().contains("background-color: #37ff00")) {
                            quality = Quality.Negative;  // Exemple de valeur pour une qualité négative
                        }
                    }
                }

                // Création d'une Proposition et ajout à la Map si tous les champs sont présents
                if (quantificator != null && subject != null && predicat != null && quality != null) {
                    Proposition proposition = new Proposition(quantificator, subject, predicat, quality);
                    resultat.put(taille++, proposition);  // Utilise l'index comme clé, puis l'incrémente

                    // Afficher ou utiliser les valeurs récupérées pour chaque HBox
                    afficherDonneesDeMap(resultat);

                    //Apeller la fonction de Nadir
                }
            }
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


    /**
     * Resizes font sizes of buttons and labels based on the current window width.
     */
    private void resizeFontSize() {
        threeplusLabel.setFont(Font.font(scene.widthProperty().getValue() / 30));
        threeminusLabel.setFont(Font.font(scene.widthProperty().getValue() / 30));
        addPremiceLabel.setFont(Font.font(scene.widthProperty().getValue() / 30));
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
    }

    public void negatif3(MouseEvent mouseEvent) {
    }

    public void affirmatif3(MouseEvent mouseEvent) {
    }
}
