package app.model;

import app.model.polysyllogismes.Polysyllogisme;

/**
 * Rp is a subclass of Rule that evaluates a syllogism based on the types of its propositions.
 */
public class Rp extends Rule {

	private String Result;

	/**
	 * Launches the rule based on the given polysyllogisme.
	 *
	 * @param polysyllogisme the polysyllogisme to evaluate
	 * @return true if the rule is satisfied, false otherwise
	 */
	public Boolean Launch(Polysyllogisme polysyllogisme) {
		Type modeP1 = polysyllogisme.getProposition().get(1).getType();
		Type modeP2 = polysyllogisme.getProposition().get(2).getType();
		Type modeC = polysyllogisme.getProposition().get(3).getType();
		if (modeP1 == Type.I || modeP1 == Type.O || modeP2 == Type.I || modeP2 == Type.O) {
			return modeC == Type.I || modeC == Type.O;
		} else {
			return true;
		}
	}
}
