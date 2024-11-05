package app.model;

/**
 * Rn is a subclass of Rule that evaluates a syllogism based on specific types of propositions.
 */
public class Rn extends Rule {

	private String Result;

	/**
	 * Launches the rule based on the given syllogism.
	 *
	 * @param syllogism the syllogism to evaluate
	 * @return true if the rule is satisfied, false otherwise
	 */
	public Boolean Launch(Syllogism syllogism) {
		Type modeP1 = syllogism.getProposition().get(1).getType();
		Type modeP2 = syllogism.getProposition().get(2).getType();
		Type modeC = syllogism.getProposition().get(3).getType();
		if (modeP1 == Type.E || modeP1 == Type.O || modeP2 == Type.O || modeP2 == Type.E) {
			return modeC == Type.E || modeC == Type.O;
		} else {
			return true;
		}
	}
}
