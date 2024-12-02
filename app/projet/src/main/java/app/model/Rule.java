package app.model;

import app.model.polysyllogismes.Polysyllogisme;

/**
 * Abstract class representing a rule for evaluating syllogisms.
 */
public abstract class Rule {


	/**
	 * Launches the evaluation of the rule based on the given syllogism.
	 *
	 * @param polysyllogisme the polysyllogisme to evaluate
	 * @return true if the rule is satisfied, false otherwise
	 */
	public abstract Boolean Launch(Polysyllogisme polysyllogisme);

	@Override
	public boolean equals(Object obj) {
		return this == obj || (obj != null && getClass() == obj.getClass());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
