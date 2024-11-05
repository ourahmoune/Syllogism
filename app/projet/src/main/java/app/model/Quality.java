package app.model;

/**
 * The `Quality` enum represents the quality of a proposition in syllogistic logic.
 * <p>
 * It defines whether a proposition is affirmative or negative, indicating whether it asserts
 * or denies a relationship between categories.
 * </p>
 */
public enum Quality {
	/**
	 * Represents an affirmative quality, indicating that the proposition asserts
	 * a relationship between the subject and predicate (e.g., "All S are P").
	 */
	Affirmative,

	/**
	 * Represents a negative quality, indicating that the proposition denies
	 * a relationship between the subject and predicate (e.g., "No S are P").
	 */
	Negative
}
