package app.model;

import app.model.polysyllogismes.Polysyllogisme;

/**
 * Rnn is a subclass of Rule that evaluates a syllogism based on the qualities of its propositions.
 */
public class Rnn extends Rule {

	private String Result;

	/**
	 * Launches the rule based on the given polysyllogisme.
	 *
	 * @param polysyllogisme the polysyllogisme to evaluate
	 * @return true if at least one proposition is not negative, false otherwise
	 */
	public Boolean Launch(Polysyllogisme polysyllogisme) {
		return polysyllogisme.getProposition().get(1).getQuality() != Quality.Negative ||
				polysyllogisme.getProposition().get(2).getQuality() != Quality.Negative;
	}
}
