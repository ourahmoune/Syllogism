package app.model;
public class Ruu  extends Rule{

	private String Result;

	/**
	 * 
	 * @param syllogism
	 */
	public Boolean Launch(Syllogism syllogism) {
		//=SI(ET(OU(A2="A"; A2="E"); OU(B2="A"; B2="E")); SI(OU(C2="I"; C2="O"); FAUX; VRAI); VRAI)
		Type modeP1 =syllogism.getProposition().get(1).getType();
		Type modeP2 =syllogism.getProposition().get(2).getType();
		Type modeC =syllogism.getProposition().get(3).getType();
		if((modeP1 ==  Type.A || modeP1 == Type.E) && (modeP2 == Type.A || modeP2 == Type.E) ){
            return modeC != Type.I && modeC != Type.O;
		}else{
			return true;
		}
	}



}