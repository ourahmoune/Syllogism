package app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The `QuantificatorList` class manages a singleton list of `Quantificator` objects.
 * <p>
 * This class ensures there is a single instance of `QuantificatorList` (singleton pattern)
 * and provides methods to add, remove, and retrieve quantifiers from this list.
 * </p>
 */
public class QuantificatorList {

	// The unique instance of the class (singleton)
	private static QuantificatorList instance;

	// List of quantifiers
	private List<Quantificator> quantificators;

	/**
	 * Private constructor to prevent instantiation outside this class.
	 * Initializes the `quantificators` list.
	 */
	private QuantificatorList() {
		this.quantificators = new ArrayList<Quantificator>();
	}

	/**
	 * Retrieves the singleton instance of `QuantificatorList`.
	 * If the instance does not yet exist, it is created.
	 *
	 * @return The singleton instance of `QuantificatorList`.
	 */
	public static QuantificatorList getInstance() {
		if (instance == null) {
			instance = new QuantificatorList();
		}
		return instance;
	}

	/**
	 * Adds a quantificator to the list.
	 *
	 * @param quantificator The `Quantificator` object to add to the list.
	 */
	public void addQuantificator(Quantificator quantificator) {
		quantificators.add(quantificator);
	}

	/**
	 * Removes a quantificator from the list based on its name and quantity.
	 *
	 * @param word The name of the quantificator to remove.
	 * @param quantity The quantity of the quantificator to remove.
	 */
	public void removeQuantificator(String word, Quantity quantity) {
		for (Quantificator quantificator : quantificators) {
			if (quantificator.getQuantity() == quantity) {
				if (quantificator.getName().equals(word)) {
					System.out.println("Removing: " + quantificator.getName() + " " + quantificator.getQuantity());
					quantificators.remove(quantificator);
					break;
				}
			}
		}
	}

	/**
	 * Retrieves the list of all quantificators.
	 *
	 * @return A list of `Quantificator` objects.
	 */
	public List<Quantificator> getQuantificators() {
		return quantificators;
	}

	/**
	 * Searches for a quantificator by its name and returns it if found.
	 *
	 * @param word The name of the quantificator to retrieve.
	 * @return The `Quantificator` object with the specified name, or `null` if not found.
	 */
	public Quantificator getQuantificator(String word) {
		for (Quantificator quantificator : quantificators) {
			if (quantificator.getName().equals(word)) {
				return quantificator;
			}
		}
		return null;
	}
}
