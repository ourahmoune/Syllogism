package app.model;
public class Rmt  extends Rule{

	private String Result;

	/**
	 *
	 * @param syllogism
	 */
	public Boolean Launch(Syllogism syllogism) {
		// TODO - implement Rlh.Launch
		Type modeP1 =syllogism.getProposition().get(1).getType();
		Type modeP2 =syllogism.getProposition().get(2).getType();
		Boolean result = switch (syllogism.getFigure()) {
            case UN ->
                // code block
                    modeP1 == Type.A || modeP1 == Type.E || modeP2 == Type.E || modeP2 == Type.O;
            case DEUX -> modeP1 == Type.O || modeP1 == Type.E || modeP2 == Type.O || modeP2 == Type.E;
            case TROIS -> modeP1 == Type.A || modeP1 == Type.E || modeP2 == Type.A || modeP2 == Type.E;
            case QUATRE -> modeP1 == Type.O || modeP1 == Type.E || modeP2 == Type.A || modeP2 == Type.E;
            default ->
                // code block
                    throw new UnsupportedOperationException(syllogism.getFigure() + " : Figure not supported");
        };
        return result ;

	}
}