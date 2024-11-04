package app.model;

/**
 * The `Quantity` enum represents the quantity of a proposition in syllogistic logic.
 * <p>
 * It defines whether a proposition is universal (applying to all members of a category)
 * or existential (applying to some members of a category).
 * </p>
 */
public enum Quantity {
	/**
	 * Represents a universal quantity, indicating that the proposition applies to all members
	 * of the category (e.g., "All S are P").
	 */
	Universal,

	/**
	 * Represents an existential quantity, indicating that the proposition applies to some members
	 * of the category (e.g., "Some S are P").
	 */
	Exisential
}
