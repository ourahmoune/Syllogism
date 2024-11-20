package app.model;

import app.model.polysyllogismes.Polysyllogisme;

/**
 * Rpp is a subclass of Rule that evaluates a syllogism based on the types of its propositions.
 */
public class Rpp extends Rule {

	private String Result;

	/**
	 * Launches the rule based on the given syllogism.
	 *
	 * @param polysyllogisme the polysyllogisme to evaluate
	 * @return true if at least one proposition is not of type I or O, false otherwise
	 */
	public Boolean Launch(Polysyllogisme polysyllogisme ) {
		Type modeP1 = polysyllogisme.getProposition().get(1).getType();
		Type modeP2 = polysyllogisme.getProposition().get(2).getType();
		return (modeP1 != Type.I && modeP1 != Type.O) || (modeP2 != Type.I && modeP2 != Type.O);
	}
}
