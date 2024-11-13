package app.model;

/**
 * The Rmt class represents the Middle Term Rule.
 * Rmt: the quantity of M must be universal in at least one of the premises.
 * The main method `Launch` checks if the types of the premises (A, E, I, O) of a given syllogism
 * comply with the specific rules associated with each figure.
 */
public class Rmt extends Rule {

	private String Result;

	/**
	 * Evaluates the validity of a syllogism based on its premise types and figure.
	 *
	 * @param syllogism The `Syllogism` object to evaluate, containing the types of propositions and the figure.
	 * @return A boolean indicating whether the syllogism is valid according to the defined rules.
	 * @throws UnsupportedOperationException if the figure of the syllogism is not supported.
	 */
	public Boolean Launch(Syllogism syllogism) {
		Type modeP1 = syllogism.getProposition().get(1).getType();
		Type modeP2 = syllogism.getProposition().get(2).getType();

		Boolean result = switch (syllogism.getFigure()) {
			case UN -> modeP1 == Type.A || modeP1 == Type.E || modeP2 == Type.E || modeP2 == Type.O;
			case DEUX -> modeP1 == Type.O || modeP1 == Type.E || modeP2 == Type.O || modeP2 == Type.E;
			case TROIS -> modeP1 == Type.A || modeP1 == Type.E || modeP2 == Type.A || modeP2 == Type.E;
			case QUATRE -> modeP1 == Type.O || modeP1 == Type.E || modeP2 == Type.A || modeP2 == Type.E;
			default -> throw new UnsupportedOperationException(syllogism.getFigure() + " : Figure not supported");
		};

		return result;
	}
}
