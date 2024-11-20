package app.controller;

import app.model.*;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.Map;

public class UnGuidedController {

    @FXML
    VBox vboxPremice;

    @FXML
    ComboBox<String> Q1, Q2, Q3;

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
        textField1.setStyle("-fx-prompt-text-fill: #808080;");
        HBox.setHgrow(textField1, javafx.scene.layout.Priority.ALWAYS);

        TextField textField2 = new TextField();
        textField2.setStyle("-fx-prompt-text-fill: #808080;");
        HBox.setHgrow(textField2, javafx.scene.layout.Priority.ALWAYS);

        TextField textField3 = new TextField();
        textField3.setStyle("-fx-prompt-text-fill: #808080;");
        HBox.setHgrow(textField3, javafx.scene.layout.Priority.ALWAYS);
// Bouton "+"
        Button isAddButtonSelected = new Button("+");
        isAddButtonSelected.setStyle("-fx-background-radius: 15; -fx-min-width: 30; -fx-min-height: 30; " +
                "-fx-max-height: 30; -fx-max-width: 30; -fx-font-size: 20; -fx-text-fill: #32b71b; " +
                "-fx-background-color: lightgrey; -fx-alignment: center; -fx-padding: 0;");
        HBox.setHgrow(isAddButtonSelected, javafx.scene.layout.Priority.ALWAYS);

        // Bouton "-"
        Button isMinusButtonSelected = new Button("—");
        isMinusButtonSelected.setStyle("-fx-background-radius: 15; -fx-min-width: 30; -fx-min-height: 30; " +
                "-fx-max-height: 30; -fx-max-width: 30; -fx-font-size: 15; -fx-text-fill: #32b71b; " +
                "-fx-background-color: lightgrey; -fx-alignment: center; -fx-padding: 0; -fx-font-weight: bold;");
        HBox.setHgrow(isMinusButtonSelected, javafx.scene.layout.Priority.ALWAYS);

        isAddButtonSelected.setOnAction(event -> handleButtonSelection(isAddButtonSelected, isMinusButtonSelected));
        isMinusButtonSelected.setOnAction(event -> handleButtonSelection(isMinusButtonSelected, isAddButtonSelected));

        // Attachez les propriétés de sélection aux boutons via l'HBox
        hbox.getProperties().put("isAddButtonSelected", isAddButtonSelected);
        hbox.getProperties().put("isMinusButtonSelected", isMinusButtonSelected);

        // Ajout de tous les éléments à l'HBox
        hbox.getChildren().addAll(comboBox, textField1, textField2, textField3, isAddButtonSelected, isMinusButtonSelected);

        // Ajout de l'HBox au conteneur VBox dans le ScrollPane
        vboxPremice.getChildren().add(hbox);
    }

    @FXML
    public void addPremice(ActionEvent actionEvent) {
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
}
