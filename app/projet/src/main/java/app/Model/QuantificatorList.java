package app.Model;

import java.util.ArrayList;
import java.util.List;

public class QuantificatorList {

	// Instance unique de la classe (singleton)
	private static QuantificatorList instance;

	// La liste des quantificateurs (utilise HashMap pour la gestion des éléments)
	private List<Quantificator> quantificators;

	// Constructeur privé pour empêcher la création d'instances
	private QuantificatorList() {
		this.quantificators = new ArrayList<Quantificator>();
	}

	// Méthode pour obtenir l'instance unique (singleton)
	public static QuantificatorList getInstance() {
		if (instance == null) {
			instance = new QuantificatorList();
		}
		return instance;
	}

	// Méthode pour ajouter un quantificateur à la liste
	public void addQuantificator(Quantificator quantificator) {
		quantificators.add(quantificator);
	}

	// Méthode pour retirer un quantificateur de la liste
	public void removeQuantificator(String word, Quantity quantity) {
		for (Quantificator quantificator : quantificators) {
			if (quantificator.getQuantity() == quantity) {
				if (quantificator.getName().equals(word)) {
					System.out.println("Affichage : " + quantificator.getName() + " " + quantificator.getQuantity());
					quantificators.remove(quantificator);
					break;
				}
			}
		}
	}

	// Accesseur pour obtenir la liste des quantificateurs
	public List<Quantificator> getQuantificators() {
		return quantificators;
	}

}