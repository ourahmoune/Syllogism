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
				switch (modec) {
					case A :
						result = modeP2 ==  Type.A || modeP2 ==  Type.E ;
						break;
					case E:
						result =( modeP2 ==  Type.A || modeP2 ==  Type.E)&&(modeP1 == Type.E || modeP1 ==Type.O) ;						break;
					case O:
						result = modeP1 == Type.E || modeP1 == Type.O;
						break;
					case I:
						result  = true ;
						break;
					default:
						throw new UnsupportedOperationException(modec +" : Type not supported");

				}
				break;
			case DEUX:
				switch (modec) {
					case A :
						result = modeP2 ==  Type.A || modeP2 ==  Type.E ;
						break;
					case E:
						result =( modeP2 ==  Type.A || modeP2 ==  Type.E) &&(modeP1 ==  Type.A || modeP1 ==  Type.E);
						break;
					case O:
						result = modeP1 == Type.E || modeP1 ==  Type.A;
						break;
					case I:
						result = true ;
						break;
					default:
						throw new UnsupportedOperationException(modec +" : Type not supported");
				}
				break;
			case TROIS:
				switch (modec) {
					case A:
						result = modeP2 ==  Type.O || modeP2 ==  Type.E ;
						break;
					case E:
						result = (modeP2 ==  Type.O || modeP2 ==  Type.E )&&(modeP1 ==  Type.O || modeP1 ==  Type.E );
						break;
					case O:
						result = modeP1 ==  Type.O || modeP1 ==  Type.E ;
						break ;
					case I:
						result = true ;
						break;
					default:
						throw new UnsupportedOperationException(modec +" : Type not supported");



				}
				break;
			case QUATRE:
				switch (modec) {
					case A:
						result = modeP2 ==  Type.O || modeP2 ==  Type.E ;
						break;
					case E:
						result =(modeP2 ==  Type.O || modeP2 ==  Type.E ) &&(modeP1 ==  Type.A || modeP1 ==  Type.E );
						break ;
					case O:
						result =  modeP1 ==  Type.A || modeP1 ==  Type.E ;
						break;
					case I:
						result = true ;
						break;
					default:
						throw new UnsupportedOperationException(modec +" : Type not supported");

				}
				break;
			default:
				throw new UnsupportedOperationException(syllogism.getFigure() +" : Figure not supported");

		}
		return  result;
	}


}