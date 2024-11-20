package app.model;

import app.model.polysyllogismes.Polysyllogisme;

/**
 * The Raa class represents the syllogistic rule stating that two affirmative premises
 * must yield an affirmative conclusion. It inherits from the `Rule` class and contains
 * a descriptive message (`Result`) explaining this rule.
 */
public class Raa extends Rule {

	private String Result;

	/**
	 * Constructor for the `Raa` class.
	 * Initializes the `Result` attribute with a message explaining the rule.
	 */
	public Raa() {
		this.Result = "Two affirmative premises yield an affirmative conclusion.";
	}

	/**
	 * Evaluates the validity of a polysyllogisme based on the quality (affirmative or negative)
	 * of its premises and conclusion.
	 *
	 * @param polysyllogisme The `Syllogism` object to evaluate, containing the qualities of the propositions.
	 * @return `true` if the first two premises are affirmative and the conclusion is not negative,
	 *         `false` otherwise.
	 */
	public Boolean Launch(Polysyllogisme polysyllogisme) {
		return polysyllogisme.getProposition().get(1).getQuality() != Quality.Affirmative ||
				polysyllogisme.getProposition().get(2).getQuality() != Quality.Affirmative ||
				polysyllogisme.getProposition().get(3).getQuality() != Quality.Negative;
	}
}
