package app.model;
public class Rp  extends Rule{

	private String Result;

	/**
	 *
	 * @param syllogism
	 */
	public Boolean Launch(Syllogism syllogism) {
		Type modeP1 =syllogism.getProposition().get(1).getType();
		Type modeP2 =syllogism.getProposition().get(2).getType();
		Type modeC =syllogism.getProposition().get(3).getType();
		if(modeP1 == Type.I || modeP1 == Type.O || modeP2 == Type.I || modeP2 == Type.O){
			return modeC == Type.I || modeC == Type.O;
		}else{
			return true;
		}
    }


}