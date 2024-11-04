package app.model;
public class Rlh  extends Rule{

	private String Result;

	/**
	 * 
	 * @param syllogism
	 */
	public Boolean Launch(Syllogism syllogism) {
		Boolean result = false;
		Type modeP1 =syllogism.getProposition().get(1).getType();
		Type modeP2 =syllogism.getProposition().get(2).getType();
		Type modec = syllogism.getProposition().get(3).getType();
		switch (syllogism.getFigure()) {

			case UN:
                result = switch (modec) {
                    case A -> modeP2 == Type.A || modeP2 == Type.E;
                    case E -> (modeP2 == Type.A || modeP2 == Type.E) && (modeP1 == Type.E || modeP1 == Type.O);
                    case O -> modeP1 == Type.E || modeP1 == Type.O;
                    case I -> true;
                    default -> throw new UnsupportedOperationException(modec + " : Type not supported");
                };
				break;
			case DEUX:
                result = switch (modec) {
                    case A -> modeP2 == Type.A || modeP2 == Type.E;
                    case E -> (modeP2 == Type.A || modeP2 == Type.E) && (modeP1 == Type.A || modeP1 == Type.E);
                    case O -> modeP1 == Type.E || modeP1 == Type.A;
                    case I -> true;
                    default -> throw new UnsupportedOperationException(modec + " : Type not supported");
                };
				break;
			case TROIS:
                result = switch (modec) {
                    case A -> modeP2 == Type.O || modeP2 == Type.E;
                    case E -> (modeP2 == Type.O || modeP2 == Type.E) && (modeP1 == Type.O || modeP1 == Type.E);
                    case O -> modeP1 == Type.O || modeP1 == Type.E;
                    case I -> true;
                    default -> throw new UnsupportedOperationException(modec + " : Type not supported");
                };
				break;
			case QUATRE:
                result = switch (modec) {
                    case A -> modeP2 == Type.O || modeP2 == Type.E;
                    case E -> (modeP2 == Type.O || modeP2 == Type.E) && (modeP1 == Type.A || modeP1 == Type.E);
                    case O -> modeP1 == Type.A || modeP1 == Type.E;
                    case I -> true;
                    default -> throw new UnsupportedOperationException(modec + " : Type not supported");
                };
				break;
			default:
				throw new UnsupportedOperationException(syllogism.getFigure() +" : Figure not supported");

		}
		return  result;
	}


}