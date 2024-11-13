package app.model;

/**
 * Abstract class representing a rule for evaluating syllogisms.
 */
public abstract class Rule {

	private String result;

	public abstract Boolean Launch(Syllogism syllogism) ;
	@Override
	public boolean equals(Object obj) {
		return this == obj || (obj != null && getClass() == obj.getClass());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	/**
	 * Launches the evaluation of the rule based on the given syllogism.
	 *
	 * @param syllogism the syllogism to evaluate
	 * @return true if the rule is satisfied, false otherwise
	 */
}