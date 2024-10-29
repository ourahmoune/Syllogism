package app.Model;
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
		Boolean result = false;
		switch(syllogism.getFigure()) {
			case  Figure.UN:
				// code block
				result = modeP1 == Type.A || modeP1 == Type.E || modeP2 == Type.E || modeP2==Type.O;
				break ;
			case Figure.DEUX:
				result = modeP1 == Type.O || modeP1 == Type.E|| modeP2 == Type.O || modeP2 == Type.E  ;
				break;
			case Figure.TROIS:
				result= modeP1 == Type.A || modeP1 == Type.E|| modeP2 == Type.A || modeP2 == Type.E ;
				break;
			case Figure.QUATRE:
				result = modeP1 == Type.O || modeP1 == Type.E || modeP2 == Type.A || modeP2 == Type.E ;
				break;

			default:
				// code block
				throw new UnsupportedOperationException(syllogism.getFigure() +" : Figure not supported");
		}
		return result ;

	}
}