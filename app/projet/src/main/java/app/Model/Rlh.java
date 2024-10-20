package app.Model;
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
			/*



SI(C2="O";OU(A2="E";A2="A");
SI(C2="I";VRAI;FAUX))));
SI(D2=3;
SI(C2="A";OU(B2="O";B2="E");
SI(C2="E";ET(OU(B2="O";B2="E");OU(A2="E";A2="O"));
SI(C2="O";OU(A2="E";A2="O");
SI(C2="I";VRAI;FAUX))));
SI(D2=4;
SI(C2="A";OU(B2="O";B2="E");
SI(C2="E";ET(OU(B2="O";B2="E");OU(A2="E";A2="A"));
SI(C2="O";OU(A2="E";A2="A");
SI(C2="I";VRAI;FAUX))))))))

*/
			case Figure.UN:
				switch (modec) {
					case Type.A :
						result = modeP2 ==  Type.A || modeP2 ==  Type.E ;
						break;
					case Type.E:
						result =( modeP2 ==  Type.A || modeP2 ==  Type.E)&&(modeP1 == Type.E || modeP1 ==Type.O) ;						break;
					case Type.O:
						result = modeP1 == Type.E || modeP1 == Type.O;
						break;
					case Type.I:
						result  = true ;
						break;
					default:
						throw new UnsupportedOperationException();
				}
				break;
			case Figure.DEUX:
				switch (modec) {
					case Type.A :
						result = modeP2 ==  Type.A || modeP2 ==  Type.E ;
						break;
					case Type.E:
						result =( modeP2 ==  Type.A || modeP2 ==  Type.E) &&(modeP1 ==  Type.A || modeP1 ==  Type.E);
						break;
					case Type.O:
						result = modeP1 == Type.E || modeP1 ==  Type.A;
						break;
					case Type.I:
						result = true ;
						break;
					default:
						throw new UnsupportedOperationException();
				}
				break;
			case Figure.TROIS:
				switch (modec) {
					case Type.A:
						result = modeP2 ==  Type.O || modeP2 ==  Type.E ;
						break;
					case Type.E:
						result = (modeP2 ==  Type.O || modeP2 ==  Type.E )&&(modeP1 ==  Type.O || modeP1 ==  Type.E );
						break;
					case Type.O:
						result = modeP1 ==  Type.O || modeP1 ==  Type.E ;
						break ;
					case Type.I:
						result = true ;
						break;
					default:
						throw new UnsupportedOperationException();


				}
				break;
			case Figure.QUATRE:
				switch (modec) {
					case Type.A:
						result = modeP2 ==  Type.O || modeP2 ==  Type.E ;
						break;
					case Type.E:
						result =(modeP2 ==  Type.O || modeP2 ==  Type.E ) &&(modeP1 ==  Type.A || modeP1 ==  Type.E );
						break ;
					case Type.O:
						result =  modeP1 ==  Type.A || modeP1 ==  Type.E ;
						break;
					case Type.I:
						result = true ;
						break;
					default:
						throw new UnsupportedOperationException();
				}
				break;
			default:
				throw new UnsupportedOperationException();

		}
		return  result;
	}


}