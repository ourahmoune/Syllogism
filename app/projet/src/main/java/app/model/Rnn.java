package app.model;

/**
 * Rnn is a subclass of Rule that evaluates a syllogism based on the qualities of its propositions.
 */
public class Rnn extends Rule {

	private String Result;

	/**
	 * Launches the rule based on the given syllogism.
	 *
	 * @param syllogism the syllogism to evaluate
	 * @return true if at least one proposition is not negative, false otherwise
	 */
	public Boolean Launch(Syllogism syllogism) {
		return syllogism.getProposition().get(1).getQuality() != Quality.Negative ||
				syllogism.getProposition().get(2).getQuality() != Quality.Negative;
	}
}
