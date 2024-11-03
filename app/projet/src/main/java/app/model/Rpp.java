package app.model;
public class Rpp  extends Rule{

	private String Result;

	/**
	 *
	 * @param syllogism
	 */
	public Boolean Launch(Syllogism syllogism) {
		//=SI(ET(OU(A2="I"; A2="O"); OU(B2="I"; B2="O")); FAUX; VRAI)
		Type modeP1 =syllogism.getProposition().get(1).getType();
		Type modeP2 =syllogism.getProposition().get(2).getType();
        return (modeP1 != Type.I && modeP1 != Type.O) || (modeP2 != Type.I && modeP2 != Type.O);
	}


}