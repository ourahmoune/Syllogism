package app.model;

/**
 * Abstract class representing a rule for evaluating syllogisms.
 */
public abstract class Rule {

	private String result;

	/**
	 * Launches the evaluation of the rule based on the given syllogism.
	 *
	 * @param syllogism the syllogism to evaluate
	 * @return true if the rule is satisfied, false otherwise
	 */
	public abstract Boolean Launch(Syllogism syllogism);
}
