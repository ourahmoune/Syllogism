package app.model;

/**
 * The `Quantificator` class represents a quantifier used in logical propositions.
 * It includes a `Quantity` (universal or existential) and a name, which defines
 * the term associated with the quantifier.
 */
public class Quantificator {

	// The quantity of the quantifier (e.g., Universal or Existential)
	private Quantity quantity;
	// The name or term of the quantifier (e.g., "all", "some")
	private String name;

	/**
	 * Constructor to initialize a `Quantificator` with a specified quantity and name.
	 *
	 * @param quantity The `Quantity` of the quantifier (Universal or Existential).
	 * @param name The name or term of the quantifier.
	 */
	public Quantificator(Quantity quantity, String name) {
		this.quantity = quantity;
		this.name = name;
	}

	/**
	 * Gets the quantity of the quantifier.
	 *
	 * @return The `Quantity` associated with this quantifier.
	 */
	public Quantity getQuantity() {
		return this.quantity;
	}

	/**
	 * Sets the quantity of the quantifier.
	 *
	 * @param quantity The `Quantity` to set (Universal or Existential).
	 */
	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the name of the quantifier.
	 *
	 * @return The name or term of the quantifier.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the quantifier.
	 *
	 * @param name The name or term to assign to this quantifier.
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;  // Vous pouvez aussi retourner d'autres informations si nécessaire
	}
}
