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
		Quality qualityP1 =syllogism.getProposition().get(1).getQuality();
		Quality qualityP2 = syllogism.getProposition().get(2).getQuality();
		Boolean result = false;
		switch(syllogism.getFigure()) {
			case  Figure.UN:
				// code block
				result = modeP1 == Type.A || modeP1 == Type.E || qualityP2 == Quality.Negative;
				break ;
			case Figure.DEUX:
				result = qualityP1 == Quality.Negative || qualityP2 == Quality.Negative ;
				// code block
				break;
			case Figure.TROIS:
				result= modeP1 == Type.A || modeP1 == Type.E || modeP2 == Type.A || modeP2 == Type.E ;
				break;
			case Figure.QUATRE:
				result = 	qualityP1 == Quality.Negative || modeP1 == Type.A || modeP1 == Type.E ;
				break;

			default:
				// code block
				throw new UnsupportedOperationException();
		}
		return result ;

	}

	public void operation() {
		// TODO - implement Rlh.operation
		throw new UnsupportedOperationException();
	}

}